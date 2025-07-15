package steps;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginSteps {

    private final LoginPage loginPage;
    private final DashboardPage dashboardPage;

    // Initialize page objects using shared WebDriver
    public LoginSteps() {
        loginPage = new LoginPage(Hooks.driver);
        dashboardPage = new DashboardPage(Hooks.driver);
    }

    // Open login page using base URL from config
    @Given("User is on login page")
    public void userIsOnLoginPage() {
        loginPage.openLoginPage(ConfigReader.getProperty("baseUrl"));
    }

    // Log in using credentials from config, then check if dashboard is visible
    @When("User logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        dashboardPage.waitForDashboard();
        Assert.assertTrue(dashboardPage.isDashboardVisible());
    }
}
