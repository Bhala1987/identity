package identity.utility;

import com.cucumber.listener.ExtentCucumberFormatter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "identity",
        features = "src/test/resources/features/automation",
        tags = "@test",
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter", "pretty", "html:target/cucumber-html-report/foo"}
)
public class RunnerClass {

    @BeforeClass
    public static void setUp() throws Throwable {
        Map<String, String> systemInfo = new HashMap<String, String>();
        systemInfo.put("Cucumber version", "v1.2.3");
        systemInfo.put("Extent Cucumber Reporter version", "v1.1.0");
        ExtentCucumberFormatter.addSystemInfo(systemInfo);
        String reportFilePath = "report" + File.separator + "extend_reports_" + System.currentTimeMillis() + File.separator + "extent-report.html";
        ExtentCucumberFormatter.initiateExtentCucumberFormatter(new File(reportFilePath));
    }

}
