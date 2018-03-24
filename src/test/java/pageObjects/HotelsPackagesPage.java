package pageObjects;

import framework.BasePage;
import framework.BasePaseJS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by nkkhan on 2/13/18.
 */
public class HotelsPackagesPage extends BasePage {

    //Locators
    private By creditCard = By.id("creditCardInput");
    private By destinationField = By.id("activity-destination");
    private By thingsToDo = By.id("tab-activity-tab");
    private By searchButton = By.id("search-button");
    private By activities = By.xpath("//div[@id='activityResultsCtr']/div/div/a");

    private By availability = By.id("offerAddButtonAtf");
    private By bookPackage = By.xpath("//button[@id='offerAddButton490760']");
    private By continueBooking = By.id("createTrip");
    private By errorMessageList = By.xpath("//p[@class='uitk-validation-error']");


// aria-label


//  send text methods
    public void scrollAndInsertCardNumber(String cardNumber){
        sendText(creditCard,cardNumber);
        useKey(creditCard,Keys.TAB);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insertDestination(String destination, String select) {
        sendText(destinationField,destination);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0; i<5; i++){
            useKey(destinationField, Keys.ARROW_DOWN);
        }
        useKey(destinationField, Keys.ENTER);

    }


//    select methods



//    click methods

    public void clickOnThingsToDo() {
        clickOn(thingsToDo);
    }

    public void clickOnSearch()  {
        clickOn(searchButton);
    }

    public void clickOnPubTour(String tourName) {

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            BasePaseJS.ScrollDownPage(0,700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> chicagoActivities = list(activities);
        System.out.println("size = "+chicagoActivities.size());
        System.out.println(chicagoActivities.get(3).getAttribute("aria-label"));
//        chicagoActivities.get(3).click();

        for (WebElement element : chicagoActivities) {
            if (isAttribtuePresent(element,"aria-label") == true){
                if (element.getAttribute("aria-label").contains(tourName)){
                    element.click();
                    break;
                }
            }else {
                continue;
            }

        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnAvailability()  {
        clickOn(availability);
    }

    public void clickOnBook(){
        try {
            BasePaseJS.ClickOnJS(bookPackage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        clickOn(bookPackage);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnContinueBooking(){
        clickOn(continueBooking);
    }

//RAHILRAFATNITUNAZMUL


    //    mixed methods
    public void switchDriver(int number)  {
        switchToWindow(1);
    }



//    verification/Assertion methods

    public void verifyErrorMessage(String value){
        List<WebElement> lists  = list(errorMessageList);
        String errorMsg = lists.get(1).getText();
        Assert.assertEquals(errorMsg,value);
    }
}
