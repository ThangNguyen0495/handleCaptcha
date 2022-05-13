package utilities.screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TakeScreenshot {
    public String getCurrentTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return dateTimeFormatter.format(dateTime);
    }

    public void takeScreenshotWhenTestFail(WebDriver driver, ITestResult result, String resultPath) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);

        if ((result.getStatus() == ITestResult.FAILURE) || (result.getStatus() == ITestResult.SKIP)) {
            File scrShot = screenshot.getScreenshotAs(OutputType.FILE);
            String path = Paths.get(System.getProperty("user.dir") + "/img/" + resultPath + "/" + result.getName() + "_" + getCurrentTime() + ".jpg").toString();
            File destination = new File(path);
            FileUtils.copyFile(scrShot, destination);
        }
    }
}
