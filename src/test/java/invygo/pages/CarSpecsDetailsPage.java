package invygo.pages;

import org.openqa.selenium.WebDriver;

public class CarSpecsDetailsPage {

    private WebDriver driver;

    public CarSpecsDetailsPage(WebDriver driver) {
        this.driver = driver;

        if (!this.driver.getCurrentUrl().contains("/car-specs")) {
            throw new IllegalStateException("wrong url: " + this.driver.getCurrentUrl());
        }
    }    

}
