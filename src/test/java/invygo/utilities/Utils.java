package invygo.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


public class Utils {

  public static final String BASE_URL = "https://www.invygo.com";


  private Utils() {
    throw new AssertionError();
  }

  @SuppressWarnings("deprecation")
  public static WebDriver createWebDriver() {
    System.setProperty("webdriver.http.factory", "jdk-http-client");
    var githubActions = Boolean.parseBoolean(System.getenv("GITHUB_ACTIONS"));
    var options = new ChromeOptions();
    if (githubActions) {
      options.addArguments("--headless");
    }
    var driver = new ChromeDriver(options);

    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
    return driver;
  }

  public static String getNewWindowHandle(Collection<String> handlesBeforeOpen, Collection<String> handlesAfterOpen) {
    var handles = new ArrayList<>(handlesAfterOpen);
    handles.removeAll(handlesBeforeOpen);
    
    if (handles.isEmpty()) {
      throw new RuntimeException("Cannot find new window");
    } else if (handles.size() > 1) {
      throw new RuntimeException("There are multiple windows");
    } else {
      return handles.get(0);
    }
  }

  /**
   * Initialize wait
   * @param driver
   * @param duration
   * @return
   */
  public static WebDriverWait smartWait(WebDriver driver, Duration duration) {
    return new WebDriverWait(driver, duration==null?Duration.ofSeconds(10):duration);
  }

  /**
   * Wait until element is visible
   * @param driver
   * @param by
   */
  public static void waitUntilElementIsVisible(WebDriver driver, By by) {
    smartWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  /**
   * Wait until element is clickable
   * @param driver
   * @param by
   */
  public static void waitUntilElementIsClickable(WebDriver driver, By by) {
    waitUntilElementIsVisible(driver, by);
    smartWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(by));
  }

  /**
   * sleep
   * @param driver
   * @param by
   */
  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ign) {
      LogUtils.info(ign.getMessage());
    }
  }

  /**
   * Wait until JavaScript is executed
   * @param driver
   */
  public static void waitUntilJsIsExecuted(WebDriver driver) {
    while (true) {
      var js = ((JavascriptExecutor)driver).executeScript("return document.readyState");
      if (js.equals("complete")) {
        break;
      }
      sleep(100);
    }
  }

  /**
   * Element click action
   * @param driver
   * @param by
   */
  public static void click(WebDriver driver, By by) {
    waitUntilElementIsVisible(driver, by);

    var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(by)).click();
  }

}
