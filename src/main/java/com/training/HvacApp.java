package com.training;

public class HvacApp {

    public static void main(String[] args) {

        SocketWrapper socket;

        IEnvironmentRunner socketEnvironmentRunner;

        int high = Integer.valueOf(System.getProperties().getProperty("high"));
        int low = Integer.valueOf(System.getProperties().getProperty("low"));
        IEnvironmentController environmentController = getEnvironmentController();
        environmentController.setTemperatureBoundaryHigh(high);
        environmentController.setTemperatureBoundaryLow(low);

        try {
            int port = Integer.valueOf(System.getProperties().getProperty("server"));
            socket = new SocketWrapper(port);
            socketEnvironmentRunner = new SocketEnvironmentRunner(environmentController, socket);
        } catch (Exception e) {
            socketEnvironmentRunner = new SimpleEnvironmentalRunner(environmentController);
        }

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

    private static IEnvironmentController getEnvironmentController() {
        return new EnvironmentController(new RealHVAC());
    }

}
