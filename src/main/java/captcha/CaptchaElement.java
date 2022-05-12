package captcha;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CaptchaElement {
    WebDriver driver;

    public CaptchaElement(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "iframe[src^='https://www.recaptcha.net/recaptcha/'][name ^='a-']")
    WebElement CAPTCHA_IFRAME;

    @FindBy(css = "#recaptcha-anchor")
    WebElement CAPTCHA_CHECKBOX;

    @FindBy(css = "iframe[src^='https://www.recaptcha.net/recaptcha'][name ^='c-']")
    WebElement CAPTCHA_CHALLENGE_IFRAME;

    @FindBy(css = "#recaptcha-audio-button")
    WebElement CAPTCHA_AUDIO;

    @FindBy(css = "div.rc-audiochallenge-tdownload > a")
    WebElement DOWNLOAD_MP3_BTN;

    @FindBy(css = "button.base--button:nth-child(2)")
    WebElement UPLOAD_MP3;

    @FindBy(css = "div.tab-panels > div > div > div > span")
    WebElement GET_TEXT_FROM_AUDIO;

    @FindBy(css = "#audio-response")
    WebElement INPUT_TEXT_FROM_VOICE;

    @FindBy(css = "#recaptcha-verify-button")
    WebElement VERIFY_CAPTCHA_BTN;
}
