///*package runner;
//
//public class TestRunner {
//}*/


package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith( Cucumber.class)
@CucumberOptions (
        plugin = {
                "pretty", "html:target/cucumberHtmlReport",
                "html:target/cucumberHtmlReport.html",     //  for html result
                "pretty:target/cucumber-json-report.json"   // for json result
        },
        features = "src/test/resources/features"
        ,glue = "stepDefinition"
)
public class TestRunner {

}
