package com.training;

import com.training.fakes.ExecutorSpy;
import com.training.fakes.FakeHVAC;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * <p>
 * User: Balint_Balku
 * Date: 2015-04-14
 * <p>
 * (c) Survey Sampling International
 */
public class SocketEnvironmentRunnerTest {

  private class SocketWrapperSpy extends SocketWrapper {

    private SocketWrapperSpy(int port) {
      super(port);
    }

    @Override
    public void start() {
      socketStarted = true;
    }

    @Override
    public void close() {
      socketStopped = true;
    }
  }

  private SocketEnvironmentRunner   runner;
  private IEnvironmentController    environmentController;
  private ExecutorSpy               executorService;

  private boolean socketStarted;
  private boolean socketStopped;


  @Before
  public void setup() {
    environmentController = new EnvironmentController(new FakeHVAC());
    executorService = new ExecutorSpy();
  }

  @Test
  public void shouldScheduleEnvironmentControllerTickForEachMinute() throws IOException {
    try (SocketWrapper socket = new SocketWrapperSpy(5000)) {
      runner = new SocketEnvironmentRunner(environmentController, socket, executorService);
      runner.start();

      Assert.assertTrue(executorService.scheduleAtFixRateCalled);
      Assert.assertTrue(socketStarted);
    }
  }

  @Test
  public void shouldShutDownExecutorService() {
    try (SocketWrapper socket = new SocketWrapperSpy(5000)) {
      runner = new SocketEnvironmentRunner(environmentController, socket, executorService);

      runner.stop();
      Assert.assertTrue(executorService.shutdownCalled);
      Assert.assertTrue(socketStopped);
    }
  }

  @Test
  public void shouldSetLowTempBasedOnSocketMessage() {
    final int[] tempLow = new int[1];
    new SocketEnvironmentRunner(new EnvironmentController(null) {

      @Override
      public void setTemperatureBoundaryLow(int lowTemp) {
        tempLow[0] = lowTemp;
      }
    }, null, null).lineRead("low=55");

    Assert.assertEquals(55, tempLow[0]);
  }

  @Test
  public void shouldSetHighTempBasedOnSocketMessage() {
    final int[] tempHigh = new int[1];
    new SocketEnvironmentRunner(new EnvironmentController(null) {

      @Override
      public void setTemperatureBoundaryHigh(int highTemp) {
        tempHigh[0] = highTemp;
      }
    }, null, null).lineRead("high=88");

    Assert.assertEquals(88, tempHigh[0]);
  }

  @Test
  public void shouldLeaveTempsOnInvalidSocketMessage() {
    final int[] temps = { 15, 25 };
    new SocketEnvironmentRunner(new EnvironmentController(null) {

      @Override
      public void setTemperatureBoundaryLow(int lowTemp) {
        temps[1] = lowTemp;
      }

      @Override
      public void setTemperatureBoundaryHigh(int highTemp) {
        temps[0] = highTemp;
      }
    }, null, null).lineRead("trash message");

    Assert.assertEquals(15, temps[0]);
    Assert.assertEquals(25, temps[1]);
  }

}
