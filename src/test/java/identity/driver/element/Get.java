package identity.driver.element;

import identity.utility.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext
public class Get {

    private final static Logger LOGGER = LoggerFactory.getLogger(Act.class);
    private static final Get INSTANCE = new Get();

    @Autowired
    private Find find;

    @Autowired
    private WebDriver webDriver;

    public String elementText(By locator) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
                return find.element(locator).getText();
            } catch (StaleElementReferenceException e) {
                LOGGER.warn("Caught StaleElementReferenceException : " + i + " times");
            }
        }
        throw new RuntimeException("Could not find: " + locator.toString());
    }

    public String pageTitle() {

        return webDriver.getTitle();
    }

}
