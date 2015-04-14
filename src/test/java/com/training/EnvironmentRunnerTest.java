package com.training;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created with IntelliJ IDEA.
 * <p>
 * User: Balint_Balku
 * Date: 2015-04-14
 * <p>
 * (c) Survey Sampling International
 */
public class EnvironmentRunnerTest {

  private class ExecutorSpy extends ScheduledThreadPoolExecutor {

    private boolean scheduleAtFixRateCalled = false;
    private boolean shutdownCalled = false;


    private ExecutorSpy() {
      super(1);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
      this.scheduleAtFixRateCalled = true;
      return null;
    }

    @Override
    public List<Runnable> shutdownNow() {
      this.shutdownCalled = true;
      return null;
    }
  }

  private class DummyEnvironmentController implements IEnvironmentController {
    @Override
    public void setTemperatureBoundaryHigh(int highTemp) {
      throw new UnsupportedOperationException("Not implemented yet.");
    }
    @Override
    public void setTemperatureBoundaryLow(int lowTemp) {
      throw new UnsupportedOperationException("Not implemented yet.");
    }
    @Override
    public void tick() {
      throw new UnsupportedOperationException("Not implemented yet.");
    }
  }

  @Test
  public void should_schedule_at_fix_rate_on_start() {
    ExecutorSpy executorSpy = new ExecutorSpy();
    EnvironmentRunner environmentRunner = new EnvironmentRunner(new DummyEnvironmentController(), executorSpy);

    environmentRunner.start();

    Assert.assertTrue(executorSpy.scheduleAtFixRateCalled);
  }

  @Test
  public void should_shutdown_executor_on_stop() {
    ExecutorSpy executorSpy = new ExecutorSpy();
    EnvironmentRunner environmentRunner = new EnvironmentRunner(new DummyEnvironmentController(), executorSpy);

    environmentRunner.stop();

    Assert.assertTrue(executorSpy.shutdownCalled);
  }

}
