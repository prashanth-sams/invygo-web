package invygo;

import static invygo.utilities.Utils.BASE_URL;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import invygo.pages.CarSpecsListingsPage;
import invygo.utilities.BaseController;
import invygo.utilities.LogUtils;
import invygo.utilities.Screenshotter;
import io.github.artsok.RepeatedIfExceptionsTest;

@ExtendWith(Screenshotter.class)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Subscription")
public class CarSpecsTest extends BaseController {

  public static final Logger LOG = LogManager.getLogger();

  @RepeatedIfExceptionsTest(repeats = 2)
  @DisplayName("It should navigate to all the cars and validate their specs")
  public void testAllTheCarsSpecs() throws InterruptedException {
    LogUtils.info("Opening URL...");
    driver.get(BASE_URL + "/en-ae/car-specs");

    var carSpecsListingsPage = new CarSpecsListingsPage(driver);
    carSpecsListingsPage.navigateAndValidateAllCars();
    
  }

}
