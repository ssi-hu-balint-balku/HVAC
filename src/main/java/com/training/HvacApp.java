package com.training;

public class HvacApp {

    public static void main(String[] args) {

        IEnvironmentController environmentController = EnvironmentControllerFactory.getController(new NonWorkingHVAC());
        IEnvironmentRunner socketEnvironmentRunner = EnvironmentRunnerFactory.getRunner(environmentController);

        try {
            socketEnvironmentRunner.start();

            while (true) {
                Thread.sleep(1000);
                System.out.println(".");
            }
        } catch (Exception ignore) {

        } finally {
            socketEnvironmentRunner.stop();
        }
    }

}
