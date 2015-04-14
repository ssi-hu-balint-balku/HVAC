package com.training;

import com.training.fakes.DummyEnvironmentController;
import com.training.fakes.ExecutorSpy;
import com.training.fakes.SocketSpy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Future;
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
public class SocketIEnvironmentRunnerTest {
    private ExecutorSpy executorSpy;
    private SocketSpy socket;
    private SocketEnvironmentRunner socketEnvironmentRunner;

    @Before
    public void setup() {
        this.executorSpy = new ExecutorSpy();
        this.socket = new SocketSpy();
        socketEnvironmentRunner = new SocketEnvironmentRunner(new DummyEnvironmentController(), socket, executorSpy);
    }

    @Test
    public void should_schedule_at_fix_rate_on_start() {
        socketEnvironmentRunner.start();
        Assert.assertTrue(executorSpy.scheduleAtFixRateCalled);
        Assert.assertTrue(socket.startCalled);
    }

    @Test
    public void should_shutdown_executor_on_stop() {
        socketEnvironmentRunner.stop();
        Assert.assertTrue(executorSpy.shutdownCalled);
        Assert.assertTrue(socket.closeCalled);
    }

}
