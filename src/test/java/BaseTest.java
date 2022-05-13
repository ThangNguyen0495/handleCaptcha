import captcha.CaptchaPage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import page.Page;
import utilities.driver.InitWebDriver;
import utilities.screenshot.TakeScreenshot;

import java.io.IOException;

public class BaseTest {
    Page page;
    CaptchaPage captchaPage;
    WebDriver driver;
    InitWebDriver initWebDriver;
    TakeScreenshot takeScreenshot;

    @BeforeMethod
    @Parameters({"browser", "isHeadless"})
    public void setUp(String browser, String isHeadless) {
        initWebDriver = new InitWebDriver();
        driver = initWebDriver.getWebDriver(browser, isHeadless);
        page = new Page(driver);
        captchaPage = new CaptchaPage(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        takeScreenshot = new TakeScreenshot();
        takeScreenshot.takeScreenshotWhenTestFail(driver, result, "Demo");
        if (driver != null) {
            driver.quit();
        }
    }
}
