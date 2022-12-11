package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/functionalTests",
        glue = "stepDefination",
        monochrome = true,
        strict = true
        , plugin = {"pretty", "html:target/cucumber-reports.html"}

)
    public class CucumberTest {
    }

