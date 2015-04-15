package com.training;

import com.training.fakes.FakeHVAC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

public class EnvironmentControllerFactoryTest {

    private Properties sysPropBackup;

    @Before
    public void setup() {
        sysPropBackup = System.getProperties();
    }

    @After
    public void cleanup() {
        System.setProperties(sysPropBackup);
    }

    @Test(expected = NumberFormatException.class)
    public void should_throw_if_high_system_property_not_set() {
        Properties properties = new Properties();
        properties.setProperty("low", "24");
        System.setProperties(properties);
        EnvironmentControllerFactory.getController(new FakeHVAC());
    }

    @Test(expected = NumberFormatException.class)
    public void should_throw_if_low_system_property_is_not_an_integer() {
        Properties properties = new Properties();
        properties.setProperty("low", "foo");
        System.setProperties(properties);
        EnvironmentControllerFactory.getController(new FakeHVAC());
    }

    @Test(expected = NumberFormatException.class)
    public void should_throw_if_high_system_property_is_not_an_integer() {
        Properties properties = new Properties();
        properties.setProperty("high", "12.11");
        System.setProperties(properties);
        EnvironmentControllerFactory.getController(new FakeHVAC());
    }

    @Test(expected = NumberFormatException.class)
    public void should_throw_if_low_system_property_not_set() {
        Properties properties = new Properties();
        properties.setProperty("high", "88");
        System.setProperties(properties);
        EnvironmentControllerFactory.getController(new FakeHVAC());
    }

    @Test
    public void should_not_throw_if_low_and_high_system_properties_are_set() {
        Properties properties = new Properties();
        properties.setProperty("low", "24");
        properties.setProperty("high", "88");
        System.setProperties(properties);
        EnvironmentControllerFactory.getController(new FakeHVAC());
    }

}
