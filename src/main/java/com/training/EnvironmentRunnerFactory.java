package com.training;

public class EnvironmentRunnerFactory {

    public static IEnvironmentRunner getRunner(IEnvironmentController environmentController) {
        try {
            int port = Integer.valueOf(System.getProperties().getProperty("server"));
            SocketWrapper socket = new SocketWrapper(port);
            return new SocketEnvironmentRunner(environmentController, socket);
        } catch (Exception e) {
            return new SimpleEnvironmentalRunner(environmentController);
        }
    }

}
