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

public class InitWebDriver {
    private WebDriver driver;

    public WebDriver getWebDriver(String browser, String isHeadless) {
        if (driver == null) {
            switch (browser) {
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless.equals("true")) {
                        firefoxOptions.addArguments("--headless");
                    }
                    firefoxOptions.addArguments("--disable-gpu");
                    firefoxOptions.addArguments("--no-sandbox");
                    firefoxOptions.addArguments("--disable-dev-shm-usage");
                    firefoxOptions.addArguments("--allow-insecure-localhost");
                    driver = new FirefoxDriver(firefoxOptions);
                }
                case "edge" -> {
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (isHeadless.equals("true")) {
                        edgeOptions.addArguments("--headless");
                    }
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--no-sandbox");
                    edgeOptions.addArguments("--disable-dev-shm-usage");
                    edgeOptions.addArguments("--allow-insecure-localhost");
                    driver = new EdgeDriver(edgeOptions);
                }
                case "safari" -> {
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                }
                default -> {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (isHeadless.equals("true")) {
                        chromeOptions.addArguments("--headless");
                    }
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--allow-insecure-localhost");
                    driver = new ChromeDriver(chromeOptions);
                }
            }
            driver.manage().window().maximize();
        }

        return driver;
    }
}
