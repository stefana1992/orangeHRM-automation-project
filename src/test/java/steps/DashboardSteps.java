package steps;

import hooks.Hooks;
import io.cucumber.java.en.And;
import pages.DashboardPage;

public class DashboardSteps {

    private final DashboardPage dashboardPage;

    // Constructor initializes DashboardPage using WebDriver from Hooks
    public DashboardSteps() {
        dashboardPage = new DashboardPage(Hooks.driver);
    }

    // Cucumber step: Navigates to PIM section and opens the Add Employee page
    @And("User navigates to PIM and opens Add Employee page")
    public void userNavigatesToPIMAndOpensAddEmployeePage() {
        dashboardPage.goToAddEmployeePage();
    }

    // Cucumber step: Navigates to Employee List page from the dashboard
    @And("User navigates to Employee List")
    public void userNavigatesToEmployeeList() {
        dashboardPage.goToEmployeeListPage();
    }
}
