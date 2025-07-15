package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    // Locates the username input field by its 'name' attribute
    @FindBy(name = "username")
    private WebElement usernameInput;

    // Locates the password input field by its 'name' attribute
    @FindBy(name = "password")
    private WebElement passwordInput;

    // Locates the login button using CSS selector
    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    // Constructor initializes WebElements using PageFactory
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Opens the login page using the provided URL
    public void openLoginPage(String url) {
        driver.get(url);
    }

    // Waits until the username input is visible (ensures page is loaded)
    public void waitForLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
    }

    // Performs the login action using given credentials
    public void login(String username, String password) {
        waitForLoginPage();                  // Waits until the login page is fully loaded
        usernameInput.sendKeys(username);    // Enters the username
        passwordInput.sendKeys(password);    // Enters the password
        loginButton.click();                 // Clicks the login button
    }
}
