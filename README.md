# OrangeHRM UI Automation Project

This is a UI test automation project for the [OrangeHRM](https://opensource-demo.orangehrmlive.com) demo application.

The project uses:
- **Java 17**
- **Selenium WebDriver**
- **JUnit 4**
- **Cucumber (Gherkin syntax)**
- **WebDriverManager**
- **Maven**
- **Page Object Model (POM)**
- **Hooks** (for setup/teardown)
- **DriverFactory** (for driver management)
- **ConfigReader** (to manage test configuration)

---

## Feature Scenario

The current feature covers the following end-to-end scenario:

```gherkin
Feature: Employee Management

   Scenario: Login, create employee, search and validate
      Given User is on login page
      When User logs in with valid credentials
      And User navigates to PIM and opens Add Employee page
      And User creates a new employee with first name "Linda" and last name "Smith"
      And User navigates to Employee List
      And User searches for the created employee
      And User opens the employee profile from the search results
      Then Employee data should be visible on the dashboard
```

## How to Clone and Set Up the Project Locally

1. Open terminal and navigate to the directory where you want the project
2. Clone the repository: git clone https://github.com/stefana1992/orangeHRM-automation-project.git
3. Navigate into the project folder
4. Open the project in IntelliJ IDEA and make sure it is imported as a Maven project (the pom.xml file should be automatically detected).
5. Optionally, run:
   `mvn clean install`
   which runs the tests and builds the project artifact.


## How to Run the Tests
Make sure you have Java 17 and Maven installed and configured in your system's PATH.

To execute all tests, run the following command from the project root:
`mvn clean test`


## HTML Report
After test execution, open the following file in a browser:

**target/cucumber-report.html**
