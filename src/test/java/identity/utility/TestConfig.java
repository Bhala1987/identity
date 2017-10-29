package identity.utility;

import identity.driver.element.Act;
import identity.driver.element.Find;
import identity.driver.element.Get;
import identity.pages.UKGovVehicleInfoPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "identity")
public class TestConfig {

    @Value("${driver}")
    private String driver;

    @Bean
    public WebDriver getWebDriver() {

        switch (driver) {
            case "chrome":
                ChromeDriverManager.getInstance().setup();
                return new ChromeDriver();
            case "firefox":
                FirefoxDriverManager.getInstance().setup();
                return new FirefoxDriver();
            case "ie":
                InternetExplorerDriverManager.getInstance().setup();
                return new InternetExplorerDriver();
            case "safari":
                return new SafariDriver();
            default:
                throw new IllegalArgumentException("Unknown driver " + driver);
        }
    }

    @Bean
    public Act getAct() {

        return new Act();
    }

    @Bean
    public Find getFind() {

        return new Find();
    }

    @Bean
    public Get getInstance() {

        return new Get();
    }

    @Bean
    UKGovVehicleInfoPage getUKGovVehicleInfoPage() {

        return new UKGovVehicleInfoPage();
    }

}
