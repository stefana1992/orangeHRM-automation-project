package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EmployeeListPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Input fields for first and last name in profile page
    @FindBy(name = "firstName")
    private WebElement firstNameInput;

    @FindBy(name = "lastName")
    private WebElement lastNameInput;

    // Search input on employee list page with autocomplete
    @FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
    private WebElement searchNameInput;

    // Autocomplete suggestions container
    @FindBy(css = "div[role='listbox']")
    private WebElement listBox;

    // Search button on employee list page
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    // List of rows representing employees in search results
    @FindBy(xpath = "//div[contains(@class, 'oxd-table-row--clickable')]")
    private List<WebElement> employeeRows;

    // Header showing employee's full name on profile page
    @FindBy(css = "h6.oxd-text.oxd-text--h6.--strong")
    private WebElement profileNameHeader;

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Search employee by full name using autocomplete and clicking search button
    public void searchEmployeeByName(String fullName) {
        fillEmployeeName(fullName);
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(employeeRows));
    }

    // Check if any search results are visible
    public boolean isSearchResultVisible() {
        return employeeRows != null && !employeeRows.isEmpty();
    }

    // Verify if employee with exact full name exists in search results
    public boolean isEmployeePresent(String fullName) {
        for (WebElement row : employeeRows) {
            // Assume 3rd and 4th columns contain first and last names respectively
            List<WebElement> cells = row.findElements(By.cssSelector("div.oxd-table-cell"));
            if (cells.size() >= 4) {
                String firstName = cells.get(2).getText().trim();
                String lastName = cells.get(3).getText().trim();
                String combinedName = firstName + " " + lastName;
                if (combinedName.equalsIgnoreCase(fullName.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper method to fill employee name in autocomplete input and select suggestion
    private void fillEmployeeName(String employeeName) {
        wait.until(ExpectedConditions.visibilityOf(searchNameInput));
        searchNameInput.clear();
        searchNameInput.sendKeys(employeeName);

        wait.until(ExpectedConditions.visibilityOf(listBox));

        // Wait until "Searching..." text disappears from autocomplete list
        By searchingTextLocator = By.xpath("//div[@role='listbox' and contains(., 'Searching...')]");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(searchingTextLocator));

        // Try clicking autocomplete option with exact employee name
        By firstMatchLocator = By.xpath("(//div[@role='listbox']//div[@role='option'][normalize-space(.//span)='" + employeeName + "'])[1]");
        try {
            WebElement firstMatch = wait.until(ExpectedConditions.elementToBeClickable(firstMatchLocator));
            firstMatch.click();
        } catch (Exception e) {
            // If exact match not found, click first autocomplete option
            By firstOptionLocator = By.xpath("(//div[@role='listbox']//div[@role='option'])[1]");
            WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(firstOptionLocator));
            firstOption.click();
        }
    }

    // Open employee profile by clicking on the corresponding row
    public void openEmployeeProfile(String fullName) {
        String normalizedFullName = fullName.replaceAll("\\s+", " ").toLowerCase();

        for (WebElement row : employeeRows) {
            String rowText = row.getText().replaceAll("\\s+", " ").toLowerCase();
            if (rowText.contains(normalizedFullName)) {
                wait.until(ExpectedConditions.elementToBeClickable(row));
                row.click();

                // Wait for profile page URL to load (contains "viewPersonalDetails")
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.urlContains("viewPersonalDetails"));
                return;
            }
        }
        throw new RuntimeException("Employee with name '" + fullName + "' not found in results.");
    }

    // Verify employee full name is displayed in profile header
    public boolean isProfileNameVisible(String expectedFullName) {
        wait.until(ExpectedConditions.visibilityOf(profileNameHeader));
        wait.until(ExpectedConditions.textToBePresentInElement(profileNameHeader, expectedFullName));
        String actualName = profileNameHeader.getText().trim();
        return actualName.equalsIgnoreCase(expectedFullName.trim());
    }

    // Verify first and last name input fields in profile match expected full name
    public boolean isProfileFirstAndLastNameCorrect(String fullName) {
        String[] parts = fullName.trim().split("\\s+");
        String expectedFirstName = parts[0];
        String expectedLastName = parts.length > 1 ? parts[1] : "";

        String actualFirstName = wait.until(ExpectedConditions.visibilityOf(firstNameInput)).getAttribute("value").trim();
        String actualLastName = wait.until(ExpectedConditions.visibilityOf(lastNameInput)).getAttribute("value").trim();

        return actualFirstName.equalsIgnoreCase(expectedFirstName)
                && actualLastName.equalsIgnoreCase(expectedLastName);
    }
}