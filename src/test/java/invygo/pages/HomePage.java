package invygo.pages;

import static invygo.utilities.Utils.click;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {

  private WebDriver driver;

  private final By monthlyPlansLink = By.xpath("//a[contains(@href,'monthly-subscription')]//*[contains(text(),'Monthly plans')]");
  private final By weeklyPlansLink = By.xpath("//a[contains(@href,'weekly-subscription')]//*[contains(text(),'Weekly plans')]");
  private final By carListings = By.xpath("(//div[@rows]//a[contains(@href,'ly-subscription')])[1]");


  public HomePage(WebDriver driver) {
    this.driver = driver;
    if (!this.driver.getTitle().startsWith("Invygo Rent-A-Car:")) {
      throw new IllegalStateException("wrong page: " + this.driver.getTitle());
    }
  }

  public MonthlySubscriptionPage goToMonthlySubscriptionPage() {
    click(driver, monthlyPlansLink);
    return new MonthlySubscriptionPage(driver);
  }

  public CarDetailsPage goToCarDetailsPage(String type) throws InterruptedException {
    click(driver, carListings);

    return new CarDetailsPage(driver, type);
  }

  public WeeklySubscriptionPage goToWeeklySubscriptionPage() {
    click(driver, weeklyPlansLink);
    return new WeeklySubscriptionPage(driver);
  }
  
}
