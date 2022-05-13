package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Element {
    private WebDriver driver;

    public Element(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[title='Women']")
    WebElement HEADER_WOMEN;

    @FindBy(css = "li > ul > li > a[title='T-shirts']")
    WebElement WOMEN_TSHIRT;

    @FindBy(css = "a.product_img_link")
    List<WebElement> PRODUCT_IMG;

    @FindBy(css = "a[title='Add to cart']")
    List<WebElement> ADD_TO_CART;

    @FindBy(css = "a[title='Proceed to checkout']")
    WebElement PROCEED_TO_CHECKOUT_CART_POPUP;

    @FindBy(css = "p > a[title='Proceed to checkout']")
    WebElement PROCEED_TO_CHECKOUT_CART_SUMMARY;
}
