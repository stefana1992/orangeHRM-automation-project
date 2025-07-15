package steps;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.EmployeeListPage;

public class EmployeeListSteps {
    private EmployeeListPage employeeListPage;

    // Static variable to store full name globally for reuse across steps
    private static String fullName;

    public EmployeeListSteps() {
        this.employeeListPage = new EmployeeListPage(Hooks.driver);
    }

    // Setter for fullName to be used from other step classes
    public static void setFullName(String name) {
        fullName = name;
    }

    @When("I navigate to the Employee List page")
    public void i_navigate_to_the_employee_list_page() {
        // Initialize or navigate to Employee List page
        employeeListPage = new EmployeeListPage(Hooks.driver);
    }

    @And("User searches for the created employee")
    public void userSearchesForTheCreatedEmployee() {
        // Search employee by stored fullName
        employeeListPage.searchEmployeeByName(fullName);

        // Verify search results are visible
        Assert.assertTrue("No search results found!", employeeListPage.isSearchResultVisible());

        // Verify created employee is present in results
        Assert.assertTrue("Created employee not found in results!", employeeListPage.isEmployeePresent(fullName));
    }

    @And("User opens the employee profile from the search results")
    public void userOpensEmployeeProfile() {
        // Open employee profile by fullName
        employeeListPage.openEmployeeProfile(fullName);
    }

    @Then("Employee data should be visible on the dashboard")
    public void employeeDataShouldBeVisibleOnDashboard() {
        // Verify employee name is visible on the profile/dashboard page
        Assert.assertTrue("Employee name not visible on dashboard!", employeeListPage.isProfileNameVisible(fullName));

        // Verify first and last name fields match expected fullName
        Assert.assertTrue("First and Last name in profile inputs do not match!", employeeListPage.isProfileFirstAndLastNameCorrect(fullName));
    }
}
