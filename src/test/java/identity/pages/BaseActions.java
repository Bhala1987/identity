package identity.pages;

import identity.driver.element.Act;
import identity.driver.element.Find;
import identity.driver.element.Get;
import identity.utility.TestConfig;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext
public class BaseActions {

    @Autowired
    protected WebDriver driver;

    @Autowired
    protected Act action;

    @Autowired
    protected Find find;

    @Autowired
    protected Get get;

}
