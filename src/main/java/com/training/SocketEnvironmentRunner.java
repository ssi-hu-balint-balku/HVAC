package com.training;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SocketEnvironmentRunner extends SimpleEnvironmentalRunner implements SocketWrapper.ISocketCallback {

    private final Pattern lowPattern  = Pattern.compile("min=(\\d*)");
    private final Pattern highPattern = Pattern.compile("max=(\\d*)");
    private final Pattern ambientPattern = Pattern.compile("out=(\\d*)");

    private final SocketWrapper socket;

    public SocketEnvironmentRunner(
        IEnvironmentController environmentController,
        SocketWrapper socket) {
        this(environmentController, socket, Executors.newScheduledThreadPool(2));
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
        System.out.println("starting " + this.getClass().getSimpleName());
        this.socket.setCallback(this);
        this.executorService.submit(socket::start);
        System.out.println("starting...");
        super.start();
    }

    @Override
    public void stop() {
        System.out.println("stopping " + this.getClass().getSimpleName());
        this.executorService.submit(socket::close);
        super.stop();
    }

    @Override
    public void lineRead(String line) {
        Matcher lowMatcher = this.lowPattern.matcher(line);
        Matcher highMatcher = this.highPattern.matcher(line);
        Matcher ambientMatcher = this.ambientPattern.matcher(line);

        if (lowMatcher.matches()) {
            try {
                int lowTemp = Integer.parseInt(lowMatcher.group(1));
                this.environmentController.setTemperatureBoundaryLow(lowTemp);
            } catch (NumberFormatException ignore) {
            }
        }

        if (highMatcher.matches()) {
            try {
                int highTemp = Integer.parseInt(highMatcher.group(1));
                this.environmentController.setTemperatureBoundaryHigh(highTemp);
            } catch (NumberFormatException ignore) {
            }
        }

        if (ambientMatcher.matches()) {
            try {
                int ambientTemp = Integer.parseInt(ambientMatcher.group(1));
                HVAC hvac = this.environmentController.getHvac();
                if (hvac instanceof RealHVAC) {
                    ((RealHVAC) hvac).setAmbientTemperature(ambientTemp);
                }
            } catch (NumberFormatException ignore) {
            }
        }
    }

}
