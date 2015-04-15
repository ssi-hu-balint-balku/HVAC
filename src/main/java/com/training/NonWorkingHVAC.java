package com.training;

public class NonWorkingHVAC implements HVAC {
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
}