package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    // Singleton instance of WebDriver
    private static WebDriver driver;

    // Returns a WebDriver instance (creates one if it doesn't exist)
    public static WebDriver getDriver() {
        if (driver == null) {
            // Automatically sets up the correct version of ChromeDriver
            WebDriverManager.chromedriver().setup();

            // Creates a new ChromeDriver instance
            driver = new ChromeDriver();

            // Maximizes the browser window
            driver.manage().window().maximize();
        }
        return driver;
    }

    // Quits the WebDriver and sets it to null (used in @After hook)
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();     // Closes all browser windows and safely ends the session
            driver = null;     // Resets the driver reference
        }
    }
}
