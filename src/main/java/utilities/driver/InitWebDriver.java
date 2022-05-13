package utilities.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Objects;

import static utilities.config.WebdriverCapabilities.CONFIG_HEADLESS;
import static utilities.config.WebdriverCapabilities.CONFIG_NO_HEADLESS;

public class InitWebDriver {
    private WebDriver driver;

    public WebDriver getWebDriver(String browser, String isHeadless) {
        if (driver == null) {

            switch (browser) {
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (Objects.equals(isHeadless, "false")) {
                        firefoxOptions.addArguments(CONFIG_NO_HEADLESS);
                    } else {
                        firefoxOptions.addArguments(CONFIG_HEADLESS);
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                }
                case "edge" -> {
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (Objects.equals(isHeadless, "false")) {
                        edgeOptions.addArguments(CONFIG_NO_HEADLESS);
                    } else {
                        edgeOptions.addArguments(CONFIG_HEADLESS);
                    }
                    driver = new EdgeDriver(edgeOptions);
                }
                case "safari" -> {
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                }
                default -> {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (Objects.equals(isHeadless, "false")) {
                        chromeOptions.addArguments(CONFIG_NO_HEADLESS);
                    } else {
                        chromeOptions.addArguments(CONFIG_HEADLESS);
                    }
                    driver = new ChromeDriver(chromeOptions);
                }
            }
            driver.manage().window().maximize();
        }

        return driver;
    }
}
