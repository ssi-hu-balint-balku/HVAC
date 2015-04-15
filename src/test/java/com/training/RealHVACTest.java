package com.training;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created with IntelliJ IDEA.
 * <p>
 * User: Balint_Balku
 * Date: 2015-04-15
 * <p>
 * (c) Survey Sampling International
 */
public class RealHVACTest {

  @Test
  public void shouldIncreaseTempByTwoWhenHeatAndFanIsOn() {
    RealHVAC hvac = new RealHVAC();

    int originalTemp = hvac.temp();

    hvac.heat(true);
    hvac.fan(true);

    int hvacTemp = hvac.temp();
    Assert.assertEquals(2, hvacTemp - originalTemp);
  }

  @Test
  public void shouldDecreaseTempByTwoWhenCoolAndFanIsOn() {
    RealHVAC hvac = new RealHVAC();

    int originalTemp = hvac.temp();

    hvac.cool(true);
    hvac.fan(true);

    int hvacTemp = hvac.temp();

    Assert.assertEquals(-2, hvacTemp - originalTemp);
  }

  @Test
  public void shouldIncreaseTempByQuarterWhenAmbientIsHighAndNothingIsTurnedOn() {
    RealHVAC hvac = new RealHVAC();
    int hvacTemp = hvac.temp();
    hvac.setAmbientTemperature(hvacTemp + 1);

    hvac.fan(false);
    hvac.cool(false);
    hvac.heat(false);

    for (int i = 0; i < 3; ++i) {
      Assert.assertTrue(hvac.temp() < hvac.getAmbientTemperature());
    }

    Assert.assertTrue(hvac.temp() == hvac.getAmbientTemperature());
  }

  @Test
  public void shouldDecreaseTempByQuarterWhenAmbientIsLowAndFanIsOff() {
    RealHVAC hvac = new RealHVAC();
    int hvacTemp = hvac.temp();
    hvac.setAmbientTemperature(hvacTemp - 2);

    hvac.fan(false);
    hvac.cool(true);
    hvac.heat(true);

    for (int i = 0; i < 4; ++i) {
      Assert.assertTrue((hvac.getAmbientTemperature() + 1) == hvac.temp());
    }

    Assert.assertTrue(hvac.getAmbientTemperature() == hvac.temp());
  }

  @Test
  public void temperatureShouldNotChangeWhenEqualsToAmbientAndFanIsOff() {
    RealHVAC hvac = new RealHVAC();

    int hvacTemp = hvac.temp();

  }

}
