package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;
import static utilities.path.Paths.DOMAIN;

public class Page {
    WebDriver driver;
    Actions actions;
    Element element;
    WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        element = new Element(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public Page Navigate() {
        driver.get(DOMAIN);
        return this;
    }

    public Page hoverWomenBtnOnHeader() {
        wait.until(ExpectedConditions.visibilityOf(element.HEADER_WOMEN));
        actions.moveToElement(element.HEADER_WOMEN).perform();
        return this;
    }

    public Page clickTShirt() {
        wait.until(ExpectedConditions.elementToBeClickable(element.WOMEN_TSHIRT));
        element.WOMEN_TSHIRT.click();
        return this;
    }

    public Page addToCart() throws InterruptedException {
        sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 750)");
        try {
            wait.until(ExpectedConditions.visibilityOf(element.PRODUCT_IMG.get(0)));
        } catch (TimeoutException ex) {
            //Nothing
        }
        actions.moveToElement(element.PRODUCT_IMG.get(0)).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element.ADD_TO_CART.get(0)));
        element.ADD_TO_CART.get(0).click();
        return this;
    }

    public Page atCartPopupClickProceedToCheckout() {
        wait.until(ExpectedConditions.visibilityOf(element.PROCEED_TO_CHECKOUT_CART_POPUP));
        element.PROCEED_TO_CHECKOUT_CART_POPUP.click();
        return this;
    }

    public void atCartSummaryPageClickProceedToCheckout() {
        wait.until(ExpectedConditions.visibilityOf(element.PROCEED_TO_CHECKOUT_CART_SUMMARY));
        element.PROCEED_TO_CHECKOUT_CART_SUMMARY.click();
    }
}
