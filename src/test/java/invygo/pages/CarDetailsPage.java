package invygo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static invygo.utilities.Utils.click;
import static invygo.utilities.Utils.waitUntilElementIsVisible;

import org.openqa.selenium.By;

import invygo.utilities.DataUtils;

public class CarDetailsPage {

    private WebDriver driver;
    private String type;

    private final By subscribeButton = By.xpath("//button[contains(text(),'Subscribe now')]");


    public CarDetailsPage(WebDriver driver, String type) throws InterruptedException {
        this.driver = driver;
        this.type = type;
        PageFactory.initElements(driver, this);
        
        waitUntilElementIsVisible(driver, By.xpath("//h2[contains(text(),'Contract length')]"));

        if (!this.driver.getCurrentUrl().contains(type+"-subscription")) {
            throw new IllegalStateException("wrong url: " + this.driver.getCurrentUrl());
        }
    }

    @FindBy(xpath = "//p[contains(text(),'Seats')]/following-sibling::p")
    public WebElement carSeat;

    @FindBy(xpath = "//h1[contains(text(),'Let’s get started')]")
    public WebElement popupHeader;

    @FindBy(xpath = "//button[@disabled][contains(text(),'Continue')]")
    public WebElement disabledContinueButton;
    

    public Integer getSeat() {
        String seat = carSeat.getText();
        String[] seatArray = seat.split(" ");
        return Integer.parseInt(seatArray[0]);
    }

    public void subscribe() {
        if (type.equals("monthly")) {
            click(driver, By.xpath("//div[contains(text(),'"+ DataUtils.getJsonData("model", type+"-contract-length") +"')]/parent::*/preceding-sibling::div"));
        }
        
        click(driver, subscribeButton);
    }
    
}
