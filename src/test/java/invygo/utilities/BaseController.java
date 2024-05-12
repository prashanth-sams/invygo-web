package invygo.utilities;

import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

public abstract class BaseController {

	public static WebDriver driver;

    @BeforeAll
    static void initAll() {
        driver = Utils.createWebDriver();
        Screenshotter.setDriver(driver);
    }

    @AfterEach
    void tearDown() throws IOException {
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    static void tearDownAll() {
        driver.quit();
    }

}