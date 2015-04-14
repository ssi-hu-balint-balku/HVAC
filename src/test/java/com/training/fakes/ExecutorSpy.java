package com.training.fakes;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorSpy extends ScheduledThreadPoolExecutor {

    public boolean scheduleAtFixRateCalled = false;
    public boolean shutdownCalled = false;

    public ExecutorSpy() {
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