package packagename.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", strict = true, plugin = {"json:target/cucumber/example.json", "json:target/cucumber/example.xml"}, tags = {
    "@Example"}, glue = "classpath:packagename.cucumber")
public class RunCucumberExampleTest {

}
