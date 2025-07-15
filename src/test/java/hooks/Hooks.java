package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class Hooks {

    // Shared WebDriver instance used across tests
    public static WebDriver driver;

    // This method runs before each scenario
    @Before
    public void setUp() {
        // Initialize the WebDriver using DriverFactory
        driver = DriverFactory.getDriver();
    }

    // This method runs after each scenario
    @After
    public void tearDown() {
        // Quit and clean up the WebDriver instance
        DriverFactory.quitDriver();
    }
}
