package com.training.fakes;

import com.training.HVAC;
import com.training.IEnvironmentController;

public class DummyEnvironmentController implements IEnvironmentController {
    @Override
    public void setTemperatureBoundaryHigh(int highTemp) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void setTemperatureBoundaryLow(int lowTemp) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public HVAC getHvac() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
