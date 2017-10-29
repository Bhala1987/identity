package identity.driver.element;

import identity.utility.TestConfig;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext
public class Act {

    private final static Logger LOGGER = LoggerFactory.getLogger(Act.class);

    @Autowired
    protected WebDriver webDriver;

    /**
     * navigate to URL
     *
     * @param url
     */

    public void goTo(String url) {

        LOGGER.info("Navigate to URL : " + url);
        webDriver.get(url);
    }
}

