package identity.driver.element;

import identity.utility.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext
public class Find {

    @Autowired
    protected WebDriver webDriver;


    public WebElement element(By locator) {

        for (int i = 0; i < 5; i++) {
            try {
                return webDriver.findElement(locator);
            } catch (UnhandledAlertException unhandledAlertException) {
                Times.waitForMilliSeconds(Times.SECOND_PAUSE);
            }
        }
        throw new RuntimeException("Common.findElement -> Tried 5 times to overcome unhandled alert exception before failing test");
    }

}
