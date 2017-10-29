package identity;

import identity.pages.UKGovVehicleInfoPage;
import identity.utility.TestConfig;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext
public class PageRepository extends Service {
    protected @Autowired
    UKGovVehicleInfoPage ukGovVehicleInfoPage;

    protected @Autowired
    WebDriver driver;

}
