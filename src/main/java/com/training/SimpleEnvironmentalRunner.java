package com.training;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleEnvironmentalRunner implements IEnvironmentRunner {

    private final IEnvironmentController environmentController;

    private final ScheduledExecutorService executorService;

    public SimpleEnvironmentalRunner(IEnvironmentController environmentController) {
        this(environmentController, Executors.newSingleThreadScheduledExecutor());
    }

    public SimpleEnvironmentalRunner(
            IEnvironmentController environmentController,
            ScheduledExecutorService executorService) {
        this.environmentController = environmentController;
        this.executorService = executorService;
    }

    @Override
    public void start() {
        this.executorService.scheduleAtFixedRate(environmentController::tick, 0, 1, TimeUnit.MINUTES);
    }

    @Override
    public void stop() {
        this.executorService.shutdownNow();
    }
}
