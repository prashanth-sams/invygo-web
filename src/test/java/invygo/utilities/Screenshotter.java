package invygo.utilities;

import java.io.File;
import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshotter implements AfterTestExecutionCallback {
    
    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        Screenshotter.driver = driver;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        
        /**
         * If the test execution has failed, take a screenshot
         */
        if (context.getExecutionException().isPresent()) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String currentDir = System.getProperty("user.dir");
            FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
        }
    }
}
