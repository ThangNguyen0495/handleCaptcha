import org.testng.annotations.Test;

import java.awt.*;

public class captchaTest extends BaseTest {
    @Test
    public void demo() throws InterruptedException, AWTException {
        captchaPage.navigate()
                .getCaptchaAudio();
    }
}
