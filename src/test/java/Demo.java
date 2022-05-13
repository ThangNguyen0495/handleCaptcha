import org.testng.annotations.Test;

public class Demo extends BaseTest {

    @Test
    public void demoTest() {
        page.Navigate()
                .hoverWomenBtnOnHeader()
                .clickTShirt()
                .addToCart()
                .atCartPopupClickProceedToCheckout()
                .atCartSummaryPageClickProceedToCheckout();
    }
}
