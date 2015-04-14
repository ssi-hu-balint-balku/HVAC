package com.training;

import com.training.fakes.ExecutorSpy;
import com.training.fakes.FakeHVAC;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class SimpleEnvironmentalRunnerTest {

    private SimpleEnvironmentalRunner runner;
    private IEnvironmentController environmentController;
    private ExecutorSpy executorService;

    @Before
    public void setup() {
        environmentController = new EnvironmentController(new FakeHVAC());
        executorService = new ExecutorSpy();
        runner = new SimpleEnvironmentalRunner(environmentController, executorService);
    }

    @Test
    public void shouldScheduleEnvironmentControllerTickForEachMinute() {
        runner.start();
        assertTrue(executorService.scheduleAtFixRateCalled);
    }

    @Test
    public void shouldShutDownExecutorService() {
        runner.stop();
        assertTrue(executorService.shutdownCalled);
    }

}