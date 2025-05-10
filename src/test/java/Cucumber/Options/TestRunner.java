package Cucumber.Options;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/test/java/Features", plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json"
}
        , glue = {"Stepdefination"})//tags = "@addUser and @getUser"

public class TestRunner extends AbstractTestNGCucumberTests {

}


//mvn test '-Dcucumber.filter.tags="@addUser"'
//mvn test '-Dcucumber.plugin=html:reports/cucumber-report-html'

