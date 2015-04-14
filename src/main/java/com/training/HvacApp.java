package com.training;

public class HvacApp {

    public static void main(String[] args) {

        boolean isServer = System.getProperties().getProperty("server").equals("true");
        int high = Integer.valueOf(System.getProperties().getProperty("high"));
        int low = Integer.valueOf(System.getProperties().getProperty("low"));

        System.out.println("isServer: " + isServer);
        System.out.println("high: " + high);
        System.out.println("low: " + low);

        IEnvironmentController environmentController = getEnvironmentController();
        environmentController.setTemperatureBoundaryHigh(high);
        environmentController.setTemperatureBoundaryLow(low);
        EnvironmentRunner environmentRunner = new EnvironmentRunner(environmentController, new SocketWrapper(8080));

        try {
            environmentRunner.start();

            while(true) {
                Thread.sleep(1000);
                System.out.println(".");
            }
        }
        catch (Exception ignore) {

        }
        finally {
            environmentRunner.stop();
        }
    }

    private static IEnvironmentController getEnvironmentController() {
        return new EnvironmentController(new HVAC() {
            @Override
            public void heat(boolean on) {

            }

            @Override
            public void cool(boolean on) {

            }

            @Override
            public void fan(boolean on) {

            }

            @Override
            public int temp() {
                return 0;
            }
        });
    }

}
