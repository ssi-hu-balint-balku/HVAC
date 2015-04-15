package com.training;

import com.training.fakes.DummyEnvironmentController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Properties;

public class EnvironmentRunnerFactoryTest {

    private Properties sysPropBackup;

    @Before
    public void setup() {
        sysPropBackup = System.getProperties();
    }

    @After
    public void cleanup() {
        System.setProperties(sysPropBackup);
    }

    @Test
    public void should_create_SimpleEnvironmentalRunner_if_server_system_property_is_not_integer() {
        Properties properties = new Properties();
        properties.setProperty("server", "foo");
        System.setProperties(properties);
        IEnvironmentRunner runner = EnvironmentRunnerFactory.getRunner(new DummyEnvironmentController());
        assertEquals(SimpleEnvironmentalRunner.class, runner.getClass());
    }

    @Test
    public void should_create_SimpleEnvironmentalRunner_if_server_system_not_set() {
        System.setProperties(new Properties());
        IEnvironmentRunner runner = EnvironmentRunnerFactory.getRunner(new DummyEnvironmentController());
        assertEquals(SimpleEnvironmentalRunner.class, runner.getClass());    }

    @Test
    public void should_create_SocketEnvironmentRunner_if_server_system_property_is_integer() {
        Properties properties = new Properties();
        properties.setProperty("server", "8000");
        System.setProperties(properties);
        IEnvironmentRunner runner = EnvironmentRunnerFactory.getRunner(new DummyEnvironmentController());
        assertEquals(SocketEnvironmentRunner.class, runner.getClass());
    }

}
