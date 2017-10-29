package identity.pages;

import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;

public class UKGovVehicleInfoPage extends BaseActions {
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UKGovVehicleInfoPage.class);


    private static final By REGISTRATION = By.id("Vrm");
    private static final By CONTINUE = By.cssSelector(".button[name='Continue']");
    private static final By NUMBER = By.cssSelector(".reg-mark");
    private static final By MAKE = By.xpath("//span[text()='Make']/ancestor::li/span[2]/strong");
    private static final By COLOUR = By.xpath("//span[text()='Colour']/ancestor::li/span[2]/strong");

    public String openPage(String URL) {

        action.goTo(URL);
        driver.manage().window().maximize();
        LOGGER.info("The title of the page opened is " + get.pageTitle() + "\n");
        return get.pageTitle();
    }

    public void enterRegistrationNumber(String value) {
        find.element(REGISTRATION).sendKeys(value);
    }

    public void clickContinue() {
        find.element(CONTINUE).click();
    }

    public String getRegistrationNumber() throws InterruptedException {
        return get.elementText(NUMBER);
    }

    public String getMake() throws InterruptedException {
        return get.elementText(MAKE);
    }

    public String getColour() throws InterruptedException {
        return get.elementText(COLOUR);
    }
}
