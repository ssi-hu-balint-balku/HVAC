package com.training;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * User: Balint_Balku
 * Date: 2015-04-14
 * <p>
 * (c) Survey Sampling International
 */
public interface IEnvironmentController {

  void setTemperatureBoundaryHigh(int highTemp);
  void setTemperatureBoundaryLow(int lowTemp);
  void tick();

  HVAC getHvac();

}
