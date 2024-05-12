package invygo;

import static invygo.utilities.Utils.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import invygo.pages.HomePage;
import invygo.utilities.BaseController;
import invygo.utilities.LogUtils;
import invygo.utilities.Screenshotter;
import io.github.artsok.RepeatedIfExceptionsTest;

@ExtendWith(Screenshotter.class)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Subscription")
public class SubscriptionTest extends BaseController {

  public static final Logger LOG = LogManager.getLogger();

  
  @Order(1)
  @RepeatedIfExceptionsTest(repeats = 2)
  @DisplayName("It should be successfully subscribed to monthly plan")
  public void testMonthlySubscription() throws InterruptedException {
    LogUtils.info("Opening URL...");
    driver.get(BASE_URL);

    var homePage = new HomePage(driver);
    
    var monthlySubscriptionPage = homePage.goToMonthlySubscriptionPage();

    monthlySubscriptionPage.setCarSpecs();
    monthlySubscriptionPage.selectCar();

    var carDetailsPage = homePage.goToCarDetailsPage("monthly");
    assertEquals(5, carDetailsPage.getSeat());

    carDetailsPage.subscribe();

    assertAll("Validate popup before subscription",
      () -> assertEquals(carDetailsPage.popupHeader.isDisplayed(), true),
      () -> assertEquals(carDetailsPage.disabledContinueButton.isDisplayed(), true)
    );
    
  }


  @Order(2)
  @RepeatedIfExceptionsTest(repeats = 2)
  @DisplayName("It should be successfully subscribed to weekly plan")
  void testWeeklySubscription() throws InterruptedException {
    LogUtils.info("Opening URL...");
    driver.get(BASE_URL);

    var homePage = new HomePage(driver);
    
    var weeklySubscriptionPage = homePage.goToWeeklySubscriptionPage();

    weeklySubscriptionPage.setCarSpecs();
    
    var carDetailsPage = homePage.goToCarDetailsPage("weekly");
    assertEquals(5, carDetailsPage.getSeat());
    
    carDetailsPage.subscribe();

    assertAll("Validate popup before subscription",
      () -> assertEquals(carDetailsPage.popupHeader.isDisplayed(), true),
      () -> assertEquals(carDetailsPage.disabledContinueButton.isDisplayed(), true)
    );
  }

}
