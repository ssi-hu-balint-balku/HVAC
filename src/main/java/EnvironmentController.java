/**
 * heating & cooling elements are across the room from the
 *   thermostat, so increasing the temperature requires heat
 *   + fan, decreasing it requires cool + fan, etc.
 *   • rules:
 *   • best effort: keep the temperature between 65 and 75
 *   • hard rule: the fan can't run for 5 minutes after the
 *   heater is turned off
 *   • hard rule: the fan can't run for 3 minutes after the cooler
 *   is turned off
 */
public class EnvironmentController {

    /**
     * called 1x per minute by the *rest* of the
     *   application. Your production code doesn’t call tick() - it
     *   gets called by another part of the system [though the tests
     *   should call it, of course].
     */
    void tick() {
        // TODO
    }
}
