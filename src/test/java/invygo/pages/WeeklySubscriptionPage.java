package invygo.pages;

import static invygo.utilities.Utils.click;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import invygo.utilities.DataUtils;
import invygo.utilities.LogUtils;

public class WeeklySubscriptionPage {

    private WebDriver driver;

    public WeeklySubscriptionPage(WebDriver driver) {
        this.driver = driver;
        
        if (!this.driver.getTitle().startsWith("Weekly Car Rental in Dubai")) {
            LogUtils.info("wrong page: " + this.driver.getTitle());
            throw new IllegalStateException("wrong page: " + this.driver.getTitle());
        }
    }
    
    public void setCarSpecs() throws InterruptedException {
        var carFilter = By.xpath("//div[contains(text(),'More options')]/following-sibling::img");
        var carSortBy = By.xpath("(//div[contains(text(),'Sort by')])[2]");
        var carCity = By.xpath("//div[contains(text(),'City')]");
        var carType = By.xpath("//div[contains(text(),'"+DataUtils.getJsonData("model", "type")+"')]/div");
        var carColor = By.xpath("//div[contains(text(),'"+ DataUtils.getJsonData("model", "color")+"')]/div");
        var carSeat = By.xpath("//div[contains(text(),'"+DataUtils.getJsonData("model", "seat")+"')]/div");
        var carSortByInput = By.xpath("//div[contains(text(),'"+ DataUtils.getJsonData("model", "sortby") +"')]/div");
        var carCityInput = By.xpath("//div[contains(text(),'"+ DataUtils.getJsonData("model", "city") +"')]/img");

        click(driver, carFilter);
        click(driver, carType);
        click(driver, carColor);
        click(driver, carSeat);
        
        click(driver, carSortBy);
        click(driver, carSortByInput);
        
        click(driver, carCity);
        click(driver, carCityInput);
    }


    public void selectCar() throws InterruptedException {
        var car = By.xpath("(//a[contains(@href,'vehicleId')])[1]");

        click(driver, car);
    }

}
