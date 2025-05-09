package Cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features ="src/test/java/Features/addUser.feature"
        ,plugin = "json:target/jsonReports/cucumber-report.json"
        ,glue = {"Stepdefination"},dryRun = false)
public class TestRunnerAddUser extends AbstractTestNGCucumberTests {
}
