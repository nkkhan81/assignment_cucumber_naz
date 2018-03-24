package pageObjects;

import framework.BasePage;
import framework.BasePaseJS;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Created by nkkhan on 2/13/18.
 */
public class HotelsGiftCardPage extends BasePage {

    //Locators
    private By buyNow = By.xpath("//a[@href='https://hotels.cashstar.com/gift-card/buy/?locale=en_us' and @class='cta']");
    private By message = By.id("message");
    private By birthdayTab = By.xpath("//div[@class='design-pane-subhead']//li[@id='design-cat4']");
    private By selectDesign = By.xpath("//div[@class='faceplate-group']/span[2]/img");
    private By selectAmount = By.id("amount-250");
    private By emailDelevery = By.id("email-delivery");
    private By recipientName = By.id("name");
    private By recipientEmail = By.id("email");
    private By purchaserName = By.id("from_name");
    private By calendarViewButton = By.xpath("//button[@class='ui-datepicker-trigger']");
    private By calendarHeaderMonth = By.cssSelector("span[class='ui-datepicker-month']");
    private By calendarHeaderYear = By.cssSelector("span[class='ui-datepicker-year']");
    private By calendarNextButton = By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']");
    private By dates = By.cssSelector("a[class='ui-state-default']");
    private By addToCart = By.id("order-add-another");
    private By cartQty = By.xpath("//div[@class='item-qty']/span");
    private By cartAmount = By.xpath("//div[contains(@class,'cart-item clearfix')]//td[2]");
    private By removeCartItem = By.xpath("//a[@class='cart-action-remove']");
    private By removeConfirmationPopup = By.xpath("//div[@id='overlay-remove-item']");
    private By confirmRemoveYesButton = By.xpath("//div[@id='overlay-remove-item']/div/a[2]");
    private By emptyCart = By.xpath("//p[@id='cart-empty']");





//    send text methods
    public void insertMessage(String value){
        sendText(message,value);
    }

    public void insertRecipientName(String value) throws InterruptedException {
        BasePaseJS.ScrollDownPage(0,250);
        sendText(recipientName,value);
    }

    public void insertEmail(String value) throws InterruptedException {
        sendText(recipientEmail,value);
        BasePaseJS.ScrollDownPage(0,450);
    }

    public void insertPurchaserName(String value) {
        sendText(purchaserName,value);
    }



//    click methods

    public void scrollAndClickBuyNow() throws InterruptedException {
        switchToWindow(1);
        BasePaseJS.ScrollDownPage(0,350);
        clickOn(buyNow);
    }

    public void scrollAndClickAddToCart() throws InterruptedException {
        BasePaseJS.ScrollDownPage(0,550);
        clickOn(addToCart);
    }

    public void clickOnAmount() throws InterruptedException {
//        BasePaseJS.ScrollDownPage(0,200);
        clickOn(selectAmount);
    }

    public void clickOnBirthday() throws InterruptedException {
        BasePaseJS.ScrollDownPage(0,200);
        clickOn(birthdayTab);
    }

    public void clickOnSecondDesign() throws InterruptedException {
        clickOn(selectDesign);
        BasePaseJS.ScrollDownPage(0,450);
    }

    public void clickOnEmailTab(){
        clickOn(emailDelevery);
    }

    public void clickOnCalenderViewIcon() throws InterruptedException {
        clickOn(calendarViewButton);
    }

    public void clickOnremove() throws InterruptedException {
        clickOn(removeCartItem);
        Thread.sleep(1000);
    }

    private boolean isPopupDisplayed(){
        boolean isDisplayed = isElementDisplayed(removeConfirmationPopup);
        return isDisplayed;
    }
    public void clickOnYes() throws InterruptedException {
        if (isPopupDisplayed()==true){
            System.out.println("displayed");
            clickOn(confirmRemoveYesButton);
        }
        Thread.sleep(2000);
    }

//    mixed methods
    public void pickDate(String date) throws ParseException {
        selectCustomDateFromDatePicker(date,calendarHeaderMonth,calendarHeaderYear,calendarNextButton,dates);
    }

//    verification/Assertion methods

    public void verifyEmptyCart(String value){
        String actualCartDescription = getTextFromElement(emptyCart);
        Assert.assertEquals(actualCartDescription,value);
    }

    public void verifyQtyAndAmount(String qty, String amount){
        String actualQuantity = getTextFromElement(cartQty);
        Assert.assertEquals(actualQuantity,qty);

        String actualAmount = getTextFromElement(cartAmount).substring(1);
        Assert.assertEquals(actualAmount,amount);
    }
}
