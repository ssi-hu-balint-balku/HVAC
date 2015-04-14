package com.training;

import com.training.fakes.FakeHVAC;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EnvironmentControllerTest {

    private FakeHVAC hvac;
    private EnvironmentController controller;

    @Before
    public void setup() {
        hvac = new FakeHVAC();
        controller = new EnvironmentController(hvac);
    }

    @Test
    public void should_turn_on_heat_and_fan_if_its_too_cold() {
        hvac.temp = 60;
        controller.tick();
        assertTrue(hvac.heatIsTurnedOn);
        assertTrue(hvac.fanIsTurnedOn);
    }

    @Test
    public void heat_and_fan_should_turn_off_if_its_a_good_temp() {
        hvac.heatIsTurnedOn = true;
        hvac.fanIsTurnedOn = true;
        hvac.temp = 70;
        controller.tick();
        assertFalse(hvac.heatIsTurnedOn);
        assertFalse(hvac.fanIsTurnedOn);
    }

    @Test
    public void cool_and_fan_should_turn_off_if_its_a_good_temp() {
        hvac.coolIsTurnedOn = true;
        hvac.fanIsTurnedOn = true;
        controller.setTemperatureBoundaryHigh(71);
        controller.setTemperatureBoundaryLow(69);
        hvac.temp = 70;
        controller.tick();
        assertFalse(hvac.coolIsTurnedOn);
        assertFalse(hvac.fanIsTurnedOn);
    }

    @Test
    public void should_turn_on_cool_and_fan_if_its_too_hot() {
        hvac.temp = 80;
        controller.tick();
        assertTrue(hvac.coolIsTurnedOn);
        assertTrue(hvac.fanIsTurnedOn);
    }

    @Test
    public void should_not_turn_on_fan_for_5_mins_after_heater_is_turned_off() {
        // turning heater on
        hvac.temp = 64;
        controller.tick();

        // turning heater off
        hvac.temp = 66;
        controller.tick();

        // trying to turn heater back on
        hvac.temp = 64;

        for(int i = 0; i < 4; i++) {
            controller.tick();
            assertFalse(hvac.fanIsTurnedOn);
        }

        controller.tick();
        assertTrue(hvac.fanIsTurnedOn);
    }

    @Test
    public void should_not_turn_on_fan_for_3_mins_after_cooler_is_turned_off() {
        // turning cooler on
        hvac.temp = 76;
        controller.tick();

        // turning cooler off
        hvac.temp = 74;
        controller.tick();

        // trying to turn cooler back on
        hvac.temp = 76;

        for(int i = 0; i < 2; i++) {
            controller.tick();
            assertFalse(hvac.fanIsTurnedOn);
        }

        controller.tick();
        assertTrue(hvac.fanIsTurnedOn);
    }

}
