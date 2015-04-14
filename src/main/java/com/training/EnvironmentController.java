package com.training;

/**
 * heating & cooling elements are across the room from the thermostat, so
 * increasing the temperature requires heat + fan,
 * decreasing it requires cool + fan, etc.
 *   rules:
 *   • best effort: keep the temperature between 65 and 75
 *   • hard rule: the fan can't run for 5 minutes after the heater is turned off
 *   • hard rule: the fan can't run for 3 minutes after the cooler is turned off
 */
public class EnvironmentController implements IEnvironmentController {

    private final HVAC hvac;

    private int fanTimeout = 0;
    private int tempHigh = 75;
    private int tempLow = 65;


    public EnvironmentController(HVAC hvac) {
        this.hvac = hvac;
    }

    @Override
    public void setTemperatureBoundaryHigh(int highTemp) {
        this.tempHigh = highTemp;
    }

    @Override
    public void setTemperatureBoundaryLow(int lowTemp) {
        this.tempLow = lowTemp;
    }

    /**
     * called 1x per minute by the *rest* of the
     *   application. Your production code doesn’t call tick() - it
     *   gets called by another part of the system [though the tests
     *   should call it, of course].
     */
    @Override
    public void tick() {
        System.out.println("tick");
        int temp = this.hvac.temp();
        if ((temp < this.tempLow) && (this.fanTimeout == 0)) {
            heatRoom();
        } else if ((temp > this.tempHigh) && (this.fanTimeout == 0)) {
            coolRoom();
        } else {
            turnEverythingOff();
            decrementFanTimeout();
        }
    }

    private void heatRoom() {
        this.hvac.heat(true);
        this.hvac.fan(true);
        this.fanTimeout = 5;
    }

    private void coolRoom() {
        this.hvac.cool(true);
        this.hvac.fan(true);
        this.fanTimeout = 3;
    }

    private void turnEverythingOff() {
        this.hvac.heat(false);
        this.hvac.cool(false);
        this.hvac.fan(false);
    }

    private void decrementFanTimeout() {
        this.fanTimeout = Math.max(0, --this.fanTimeout);
    }

}
