package Cucumber.Options;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features ="src/test/java/Features",plugin = "json:target/jsonReports/cucumber-report.json",glue = {"Stepdefination"}) //tags = "@deleteplace"

public class TestRunner extends AbstractTestNGCucumberTests {

}
