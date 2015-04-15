package com.training;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * User: Balint_Balku
 * Date: 2015-04-15
 * <p>
 * (c) Survey Sampling International
 */
public class RealHVAC implements HVAC {

  private int ambientTemperature = 70;
  private boolean heatingOn;
  private boolean coolingOn;
  private boolean fanOn;
  private double temperature = 70.0;


  @Override
  public void heat(boolean on) {
    this.heatingOn = on;
  }

  @Override
  public void cool(boolean on) {
    this.coolingOn = on;
  }

  @Override
  public void fan(boolean on) {
    this.fanOn = on;
  }

  @Override
  public int temp() {
    // temp will be called on every tick, alter the actual temperature here
    // when heating and fan is on it will increase the temperature by 2 degrees on every tick
    if (this.fanOn && this.heatingOn) {
      this.temperature += 2;
    } else if (this.fanOn && this.coolingOn) {
      // when cooling and fan is on it will decrease the temperature by 2 degrees on every tick
      this.temperature -= 2;
    } else {
      // when there is no heating or cooling is in progress temperature will be increased or decreased by .25 degrees
      // based on the ambient temperature
      if (this.temperature < getAmbientTemperature()) {
        this.temperature += .25;
      } else if (this.temperature > getAmbientTemperature()) {
        this.temperature -= .25;
      }
    }

    return (int) this.temperature;
  }

  public int getAmbientTemperature() {
    return this.ambientTemperature;
  }

  public void setAmbientTemperature(int ambientTemperature) {
    this.ambientTemperature = ambientTemperature;
  }

}
