package invygo.pages;

import static invygo.utilities.Utils.waitUntilElementIsVisible;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CarSpecsListingsPage {

    private WebDriver driver;
    
    private final By allCars = By.xpath("//h3/following-sibling::a/span");

    
    public CarSpecsListingsPage(WebDriver driver) {
        this.driver = driver;
        
        if (!this.driver.getTitle().startsWith("Car Models")) {
            throw new IllegalStateException("wrong page: " + this.driver.getTitle());
        }

        if (!this.driver.getCurrentUrl().contains("/car-specs")) {
            throw new IllegalStateException("wrong url: " + this.driver.getCurrentUrl());
        }
    }

    public void navigateAndValidateAllCars() {

        for (int i = 0; i < driver.findElements(allCars).size(); i++) {

            goToCarSpecsDetailsPage(i+1);
            waitUntilElementIsVisible(driver, By.cssSelector("[title='Monthly plans']"));
            
            driver.navigate().back();
        }

    }

    public CarSpecsDetailsPage goToCarSpecsDetailsPage(Integer index) {
        driver.findElements(allCars).get(index).click();

        return new CarSpecsDetailsPage(driver);
      }

}
