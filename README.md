# Hybrid QA Framework
[![.github/workflows/main.yml](https://github.com/rehmanuet/hybrid-test-framework-selenium-restassured/actions/workflows/main.yml/badge.svg)](https://github.com/rehmanuet/hybrid-test-framework-selenium-restassured/actions/workflows/main.yml)
## Java | Selenium | RestAssured | TestNG | ExtentReports | POM | GitHub Actions

### Introduction

---------------
This Test Automation Framework is designed to test PetClinic application. It validates both UI and REST API layers using Java, Selenium, and RestAssured. The framework is built following the Page Object Model (POM) to promote scalability and maintainability, with a strong emphasis on S.O.L.I.D principles and object-oriented programming.

It is also integrated with GitHub Actions for Continuous Integration and Continuous Deployment (CI/CD).

### Stack

---------------
<p float="left"> <img src="https://www.citypng.com/public/uploads/preview/hd-java-logo-transparent-background-701751694771845zainlxmlfo.png" width="90" height="80" /> <img src="https://rest-assured.io/img/logo-transparent.png" width="100" height="80" /> <img src="https://upload.wikimedia.org/wikipedia/commons/d/d5/Selenium_Logo.png" width="100" height="80" /> <img src="https://www.extentreports.com/wp-content/uploads/2018/09/Extent_logomark_transparentbg.png" width="80" height="70" /> <img src="https://www.svgrepo.com/show/306098/githubactions.svg" width="80" height="70" /> </p>


### Why These Tools?

---------------
- **TestNG**: For flexible test execution and suite management.
- **ExtentReports**: For rich test reporting with graphs and logs.
- **RestAssured**: For fluent and readable REST API test validations.
- **Selenium**: For automated browser-based UI testing.
- **GitHub Actions**: For scalable CI/CD automation.
### Prerequisites

---------------

Ensure the following tools are installed on your local machine:

- [Docker](https://www.docker.com/)
- Java JDK 11 or higher
- Apache Maven 3 or higher

### Execution

---------------
Follow these steps to set up and execute the tests:

```bash
1. git clone git@github.com:rehmanuet/hybrid-test-framework-selenium-restassured.git
2. cd hybrid-test-framework-selenium-restassured
3. docker-compose up
4. mvn clean test -Dsurefire.suiteXmlFiles=testng-ci.xml -DbaseURI=http://localhost:8080 -Dheadless=true -Dbrowser=chrome
```
### Optional Parameters

---------------
You can customize the Maven test execution using the following parameters:

| Parameter                   | Description                                                                                                 |
|-----------------------------|-------------------------------------------------------------------------------------------------------------|
| `-Dsurefire.suiteXmlFiles=` | Specify the TestNG suite to run:<br>`testng-all.xml`, `testng-api.xml`, `testng-ui.xml`, or `testng-ci.xml` |
| `-DbaseURI=`                | Set the base URI for API/UI testing. Example: `http://localhost:8080`                                       |
| `-Dheadless=`               | Run tests in headless mode. Set `true` to enable, `false` (default) to disable.                             |
| `-Dbrowser=`                | Run tests in different browser. Set `chrome/firefox/edge`, `chrome` (default) select browser.               |

### Folder Structure

---------------

```
.
├── extentreport              # Test reports
├── .github                   # GitHub Actions workflows
├── src
│   └── test
│       ├── api
│       │   ├── base         # Base setup for API tests
│       │   ├── endpoints    # API endpoint handlers
│       │   ├── model        # POJO models
│       │   └── tests        # API testcases
│       ├── common
│       │   ├── reporting    # ExtentReport utilities
│       │   └── utils        # General utilities
│       └── ui
│           ├── pages        # Page Object classes
│           └── tests        # UI test classes
├── resources
├── pom.xml                   # Maven build file
├── testng-all.xml            # Main TestNG suite
├── testng-ui.xml             # UI TestNG suite
├── testng-api.xml            # API TestNG suite
├── testng-ci.xml             # CI-specific TestNG suite
├── docker-compose.yml        # Docker services configuration
└── README.md
```



### Test Coverage

---------------
- Owner & Pet Functionality (UI + API)
- Positive & Negative Scenarios
- Total Testcases: 27 (Two Functionality)





### Test Report

---------------
After test execution, a detailed HTML report is generated using **ExtentReports**.

#### Report Location:
`/extentreport/ExtentReportResults.html`

#### Report Details:
- Overview of all executed test cases
- Pass/Fail status
- Execution time
- Graphs and percentage metrics
- Logs

To view the report, simply open the above file path in your browser.

```bash
# Example (from project root)
open extentreport/ExtentReportResults.html  # macOS
start extentreport\ExtentReportResults.html # Windows
```
### Sample Result

---------------
<img src="https://github.com/rehmanuet/hybrid-test-framework-selenium-restassured/blob/main/instructions/testresults.png?raw=true" width="500" height="300" />
<img src="https://github.com/rehmanuet/hybrid-test-framework-selenium-restassured/blob/main/instructions/testreport.png?raw=true" width="500" height="300" />
