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