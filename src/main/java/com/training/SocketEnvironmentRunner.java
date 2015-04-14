package com.training;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SocketEnvironmentRunner extends SimpleEnvironmentalRunner {

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
        super(environmentController, executorService);
        this.socket = socket;
    }

    @Override
    public void start() {
        this.executorService.submit(socket::start);
        super.start();
    }

    @Override
    public void stop() {
        this.executorService.submit(socket::close);
        super.stop();
    }

}
