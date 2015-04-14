package com.training.fakes;

import com.training.HVAC;

public class FakeHVAC implements HVAC {

    public int temp;
    public boolean heatIsTurnedOn;
    public boolean coolIsTurnedOn;
    public boolean fanIsTurnedOn;

    public FakeHVAC() { }

    public void heat(boolean on) {
        heatIsTurnedOn = on;
    }

    public void cool(boolean on) {
        coolIsTurnedOn = on;
    }

    public void fan(boolean on) {
        fanIsTurnedOn = on;
    }

    public int temp() {
        return temp;
    }
}
