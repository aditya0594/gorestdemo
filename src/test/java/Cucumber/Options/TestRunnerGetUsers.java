package Cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(features ="src/test/java/Features/getUsers.feature"
        ,plugin = "json:target/jsonReports/cucumber-report.json"
        ,glue = {"Stepdefination"},dryRun = false)
public class TestRunnerGetUsers extends AbstractTestNGCucumberTests {

}
