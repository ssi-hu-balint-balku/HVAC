package com.training;

public class EnvironmentControllerFactory {

    public static IEnvironmentController getController(HVAC hvac) {
        int high = Integer.valueOf(System.getProperties().getProperty("high"));
        int low = Integer.valueOf(System.getProperties().getProperty("low"));
        IEnvironmentController environmentController = new EnvironmentController(hvac);
        environmentController.setTemperatureBoundaryHigh(high);
        environmentController.setTemperatureBoundaryLow(low);
        System.out.println(environmentController);
        return environmentController;
    }

}
