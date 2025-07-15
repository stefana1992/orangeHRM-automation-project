package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private WebDriver driver;

    // Top navigation header on the dashboard
    @FindBy(css = "header.oxd-topbar")
    private WebElement header;

    // Link to PIM module in the top menu
    @FindBy(xpath = "//a[@href='/web/index.php/pim/viewPimModule']")
    private WebElement pimMenu;

    // Link to "Add Employee" under PIM
    @FindBy(xpath = "//div[@class='oxd-topbar-body']//nav//ul/li[3]/a")
    private WebElement addEmployeeLink;

    // Link to "Employee List" under PIM
    @FindBy(xpath = "//div[@class='oxd-topbar-body']//nav//ul/li[2]/a")
    private WebElement employeeListLink;

    // Header on the Employee List page
    @FindBy(css = "h5.oxd-text.oxd-text--h5.oxd-table-filter-title")
    private WebElement employeeInformationHeader;

    // Constructor initializes WebElements
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Waits until dashboard header is visible
    public void waitForDashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(header));
    }

    // Checks if the dashboard header is displayed
    public boolean isDashboardVisible() {
        return header.isDisplayed();
    }

}
