package TestRunner;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@io.cucumber.junit.CucumberOptions(
        features = "Features",
        glue = "Steps",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class OnlineFoodDeliveryTestRunner {
}

