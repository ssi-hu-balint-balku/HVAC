import org.junit.Test;

import static org.junit.Assert.*;

public class EnvironmentControllerTest {

    int testTemp;
    boolean heatIsTurnedOn;
    boolean coolIsTurnedOn;
    boolean fanIsTurnedOn;

    @Test
    public void should_turn_on_heat_and_fan_if_its_too_cold() {
        EnvironmentController controller = new EnvironmentController(new FakeHVAC());
        testTemp = 60;
        controller.tick();
        assertTrue(heatIsTurnedOn);
        assertTrue(fanIsTurnedOn);
    }

    @Test
    public void heat_and_fan_should_turn_off_if_its_a_good_temp() {
        heatIsTurnedOn = true;
        fanIsTurnedOn = true;
        EnvironmentController controller = new EnvironmentController(new FakeHVAC());
        testTemp = 70;
        controller.tick();
        assertFalse(heatIsTurnedOn);
        assertFalse(fanIsTurnedOn);
    }


    @Test
    public void cool_and_fan_should_turn_off_if_its_a_good_temp() {
        coolIsTurnedOn = true;
        fanIsTurnedOn = true;
        EnvironmentController controller = new EnvironmentController(new FakeHVAC());
        testTemp = 70;
        controller.tick();
        assertFalse(coolIsTurnedOn);
        assertFalse(fanIsTurnedOn);
    }

    @Test
    public void should_turn_on_cool_and_fan_if_its_too_hot() {
        EnvironmentController controller = new EnvironmentController(new FakeHVAC());
        testTemp = 80;
        controller.tick();
        assertTrue(coolIsTurnedOn);
        assertTrue(fanIsTurnedOn);
    }

    @Test
    public void should_not_turn_on_fan_for_5_mins_after_heater_is_turned_off() {
        EnvironmentController controller = new EnvironmentController(new FakeHVAC());

        // turning heater on
        testTemp = 64;
        controller.tick();

        // turning heater off
        testTemp = 66;
        controller.tick();

        // trying to turn heater back on
        testTemp = 64;

        for(int i = 0; i < 4; i++) {
            controller.tick();
            assertFalse(heatIsTurnedOn);
        }

        controller.tick();
        assertTrue(heatIsTurnedOn);
    }

    private class FakeHVAC implements HVAC {
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
            return testTemp;
        }
    }
}
