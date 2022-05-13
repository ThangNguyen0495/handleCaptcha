package captcha;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static utilities.path.Paths.DOMAIN_CAPTCHA;

public class CaptchaPage {
    CaptchaElement captchaElement;
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    public CaptchaPage (WebDriver driver) {
        this.driver = driver;
        captchaElement = new CaptchaElement(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    public CaptchaPage navigate() {
        driver.get(DOMAIN_CAPTCHA);
        return this;
    }

    public void getCaptchaAudio() throws InterruptedException, AWTException {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(captchaElement.CAPTCHA_IFRAME));
        captchaElement.CAPTCHA_CHECKBOX.click();

        driver.switchTo().defaultContent();
        sleep(1000);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(captchaElement.CAPTCHA_CHALLENGE_IFRAME));
        sleep(5000);
        captchaElement.CAPTCHA_AUDIO.click();

        driver.switchTo().defaultContent();
        sleep(1000);
        driver.switchTo().frame(captchaElement.CAPTCHA_CHALLENGE_IFRAME);
        String Url = captchaElement.DOWNLOAD_MP3_BTN.getAttribute("href");
        System.out.println(Url);

        String path = Paths.get(System.getProperty("user.dir") + "/audio/audioForTest.mp3").toString();
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(Url).openStream());
             FileOutputStream fileOS = new FileOutputStream(path)) {
            byte[] data = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) fileOS.write(data, 0, byteContent);
        } catch (IOException e) {
            // handles IO exceptions
        }

        sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
        var tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://speech-to-text-demo.ng.bluemix.net/");
        sleep(3000);
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        actions.moveToElement(captchaElement.UPLOAD_MP3).click().build().perform();

        StringSelection stringSelection = new StringSelection(path);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        sleep(10000);
        String text = captchaElement.GET_TEXT_FROM_AUDIO.getText();
        System.out.println(text);


        driver.switchTo().window(tabs.get(0));
        driver.switchTo().frame(captchaElement.CAPTCHA_CHALLENGE_IFRAME);
        captchaElement.INPUT_TEXT_FROM_VOICE.sendKeys(text);
        captchaElement.VERIFY_CAPTCHA_BTN.click();
    }
}
