package com.training;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SocketEnvironmentRunner implements IEnvironmentRunner {

    private final IEnvironmentController environmentController;

    private final ScheduledExecutorService executorService;

    private final SocketWrapper socket;

    public SocketEnvironmentRunner(
            IEnvironmentController environmentController,
            SocketWrapper socket) {
        this(environmentController, socket, Executors.newSingleThreadScheduledExecutor());
    }

    public SocketEnvironmentRunner(
            IEnvironmentController environmentController,
            SocketWrapper socket,
            ScheduledExecutorService executorService) {
        this.environmentController = environmentController;
        this.socket = socket;
        this.executorService = executorService;
    }

    @Override
    public void start() {
        this.executorService.submit(socket::start);
        this.executorService.scheduleAtFixedRate(environmentController::tick, 0, 1, TimeUnit.MINUTES);
    }

    @Override
    public void stop() {
        this.executorService.submit(socket::close);
        this.executorService.shutdownNow();
    }

}
