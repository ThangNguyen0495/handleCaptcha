package utilities.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InitChromeDriver {
    private WebDriver driver;

    public WebDriver getChromeDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("-headless");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }

        return driver;
    }
}
