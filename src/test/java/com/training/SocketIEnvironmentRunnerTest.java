package com.training;

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

    private class ExecutorSpy extends ScheduledThreadPoolExecutor {

        private boolean scheduleAtFixRateCalled = false;
        private boolean shutdownCalled = false;


        private ExecutorSpy() {
            super(1);
        }

        @Override
        public Future<?> submit(Runnable task) {
            task.run();
            return null;
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

    private class SocketSpy extends SocketWrapper {
        private boolean startCalled;
        private boolean closeCalled;

        public SocketSpy() {
            super(8080);
        }

        @Override
        public void start() {
            this.startCalled = true;
        }

        @Override
        public void close() {
            this.closeCalled = true;
        }
    }

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
