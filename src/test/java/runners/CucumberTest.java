package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/functionalTests",
        glue = {"src/test/java/stepDefinitions"},
        monochrome = true,
        strict = true
        , plugin = {"pretty", "html:target/cucumber-reports"}
        //, plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}

)
    public class CucumberTest {
    }

