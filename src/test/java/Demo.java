import org.testng.annotations.Test;

public class Demo extends BaseTest {

    @Test
    public void demoTest() throws InterruptedException {
        page.Navigate()
                .hoverWomenBtnOnHeader()
                .clickTShirt()
                .addToCart()
                .atCartPopupClickProceedToCheckout()
                .atCartSummaryPageClickProceedToCheckout();
    }
}
