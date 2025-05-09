package Cucumber.Options;



import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;


@CucumberOptions(features ="src/test/java/Features/ConsumerSignup.feature",plugin = "json:target/jsonReports/cucumber-report.json",glue = {"Stepdefination"},dryRun = false) //tags = "@deleteplace"

/*public class TestRunnerConsumerSignup {

}*/
public class TestRunnerConsumerSignup extends AbstractTestNGCucumberTests {

    // Enables parallel execution
}
