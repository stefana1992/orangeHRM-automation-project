package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Input field for employee first name
    @FindBy(name = "firstName")
    private WebElement firstNameInput;

    // Input field for employee last name
    @FindBy(name = "lastName")
    private WebElement lastNameInput;

    // Save button to submit the new employee form
    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    // Header element on Personal Details page after employee creation
    @FindBy(css = "h6.orangehrm-main-title")
    private WebElement personalDetailsHeader;

    // Employee ID input field (optional to clear before save)
    @FindBy(xpath = "(//div[contains(@class,'oxd-input-group') and .//label]//input)[4]")
    private WebElement employeeIdInput;

    // Clears the Employee ID field
    public void clearEmployeeId() {
        employeeIdInput.clear();
    }

    // Constructor initializes WebDriver, WebDriverWait and page elements
    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Fills in first name and last name, then clicks Save using Actions class
    public void addNewEmployee(String firstName, String lastName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput)).sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        clearEmployeeId();
        Actions actions = new Actions(driver);
        actions.moveToElement(saveButton).click().perform();
    }

    // Checks if Personal Details header is visible to confirm successful employee creation
    public boolean isProfilePageVisible() {
        return wait.until(ExpectedConditions.visibilityOf(personalDetailsHeader)).isDisplayed();
    }
}
