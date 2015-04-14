package com.training;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created with IntelliJ IDEA.
 * <p>
 * User: Balint_Balku
 * Date: 2015-04-14
 * <p>
 * (c) Survey Sampling International
 */
public class EnvironmentRunner {

    private final IEnvironmentController environmentController;

    private final ScheduledExecutorService executorService;

    private final SocketWrapper socket;

    public EnvironmentRunner(
            IEnvironmentController environmentController,
            SocketWrapper socket) {
        this(environmentController, socket, Executors.newSingleThreadScheduledExecutor());
    }

    public EnvironmentRunner(
            IEnvironmentController environmentController,
            SocketWrapper socket,
            ScheduledExecutorService executorService) {
        this.environmentController = environmentController;
        this.socket = socket;
        this.executorService = executorService;
    }

    public void start() {
        this.executorService.submit(socket::start);
        this.executorService.scheduleAtFixedRate(
                EnvironmentRunner.this.environmentController::tick, 0, 1, TimeUnit.MINUTES);
    }

    public void stop() {
        this.executorService.submit(socket::close);
        this.executorService.shutdownNow();
    }

}
