package steps;

import hooks.Hooks;
import io.cucumber.java.en.And;
import pages.AddEmployeePage;
import org.junit.Assert;

public class AddEmployeeSteps {

    // Page object for Add Employee page
    private final AddEmployeePage addEmployeePage;

    // Constructor initializes the page object with the shared WebDriver
    public AddEmployeeSteps() {
        addEmployeePage = new AddEmployeePage(Hooks.driver);
    }

    // Step definition for creating a new employee with given first and last name
    @And("User creates a new employee with first name {string} and last name {string}")
    public void userCreatesNewEmployee(String firstName, String lastName) {
        // Compose full name and store it for later validation
        String fullName = firstName + " " + lastName;
        EmployeeListSteps.setFullName(fullName);

        // Perform employee creation on the Add Employee page
        addEmployeePage.addNewEmployee(firstName, lastName);

        // Assert that the Personal Details profile page is displayed after creation
        boolean isProfileVisible = addEmployeePage.isProfilePageVisible();
        Assert.assertTrue("Personal Details page should be visible after creating employee", isProfileVisible);
    }
}
