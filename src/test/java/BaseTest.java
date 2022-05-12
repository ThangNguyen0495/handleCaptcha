import captcha.CaptchaPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.Page;
import utilities.driver.InitChromeDriver;

public class BaseTest {
    Page page;
    CaptchaPage captchaPage;
    WebDriver driver;
    InitChromeDriver initChromeDriver;

    @BeforeMethod
    public void setUp() {
        initChromeDriver = new InitChromeDriver();
        driver = initChromeDriver.getChromeDriver();
        page = new Page(driver);
        captchaPage = new CaptchaPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
//            driver.quit();
        }
    }
}
