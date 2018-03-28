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
public class HotelsGroupPage extends BasePage {

    //Locators
    private By rooms = By.id("rooms");
    private By roomType = By.xpath("//select[@name='RoomTypeCode']");
    private By groupType = By.id("groupType");

    private By destination = By.id("txtCity1");
    private String jsScriptForDestination = "return document.getElementById(\"txtCity1\").value;";
    private By plainText = By.id("phone-chat");

    private String dayPattern = "MM-dd-yyyy";
    private By checkInDate = By.id("CheckInDate1_disp");
    private By checkOutDate = By.id("CheckOutDate1_disp");
    private By calendarHeaderMonth = By.xpath("//span[@class='ui-datepicker-month']");
    private By calendarHeaderYear = By.xpath("//span[@class='ui-datepicker-year']");
    private By nextButton = By.xpath("//a[@title='Next']");
    private By dates = By.xpath("//a[@class='ui-state-default']");
    private By closeDate = By.xpath("//a[@class='closeDatepicker']");

    private By starRating = By.xpath("//button[@class='star-rating']");
    private By starRatingOptions = By.xpath("//ul[@class='star-rating active']/li");
    private By sliderTolTip = By.xpath("//span[@class='ui-slider-tip']");

    private By fullName = By.id("FullName");
    private By emailLocator = By.xpath("//input[@id='input-email']");
    private By continueStep1 = By.xpath("//button[@name='step1']");

    private By newsletterCheckBox = By.xpath("//input[@id='ChkSendOffers']");
    private By continueStep2 = By.xpath("//a[@name='step2']");

    private By noButton = By.xpath("//label[@for='meeting2']");
    private By additionalComents = By.xpath("//textarea[@id='Comments']");
    private By getStartedButton = By.xpath("//button[@id='btnSubmit']");

    private By nearByPlace = By.xpath("//input[@id='eventPlace']");
    private By continueStep3 = By.xpath("//div[@class='rd-fields-group']/button[1]");

    private By viewHotels = By.xpath("//div[@class='rfp-modal-content']/button");
    private By groupDetaisContainer = By.xpath("//div[@id='group-details-container']");
    private By noOfHotels = By.xpath("//span[@id='xofxhotels']");



//    send text methods
    public void insertRoomNumbers(String value){
        sendText(rooms,value);
    }

    public void insertCity(String value) {
        sendText(destination,value);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insertComments(String value) {
        try {
            BasePaseJS.ScrollDownPage(0,250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendText(additionalComents,value);
    }

    public void insertNameEmail(String email, String name) {
        String randomEmail = generateString(4)+email+generateString(3);
        System.out.println(randomEmail);
        sendText(emailLocator,randomEmail);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendText(fullName,name);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insertNearByPlace(String place){
        sendText(nearByPlace,place);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




//    select methods
    public void selectRoomType(String value){
        selectDropDownMenuByVisibleText(roomType,value);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectGroupType(String value) {
        selectDropDownMenuByVisibleText(groupType,value);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectRating() {
        clickOn(starRating);
        List<WebElement> options = list(starRatingOptions);
        System.out.println("options size = "+options.size());
        options.get(3).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //    click methods

    public void clickOnNo() {
        clickOn(noButton);
    }

    public void clickOnGetStarted()  {
        clickOn(getStartedButton);
    }

    public void clickOnContinueStep3() {

//        sendText(nearByPlace,"downtown");
        try {
            BasePaseJS.ClickOnJS(continueStep3);//clickOn(continueStep3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickOn(continueStep3);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnViewHotel() throws InterruptedException {
        clickOn(viewHotels);
    }

    public void clickContinueButton1(){
        clickOn(continueStep1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
//RAHILRAFATNITUNAZMUL
    public void clickContinueButton2(){
        boolean isNewsLetterBoxSelected = isElementSelected(newsletterCheckBox);
        if (isNewsLetterBoxSelected==true){
            clickOn(newsletterCheckBox);
        }
        clickOn(continueStep2);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //    mixed methods
    public void selectFromDestinationList(String city) throws InterruptedException {
        selectFromAutoSuggestJS(destination,jsScriptForDestination,city);
        useKey(destination, Keys.TAB);
    }



    public void selectCheckInDate(int day){
        LocalDate date = LocalDate.now();
        String dateToBeSelected = date.plusDays(day).format(DateTimeFormatter.ofPattern(dayPattern));
        try {
            selectCustomDateFromDatePicker(dateToBeSelected,calendarHeaderMonth,calendarHeaderYear,nextButton,dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void selectCheckOutDate(int day) {
        clickOn(checkOutDate);
        LocalDate date = LocalDate.now();
        String dateToBeSelected = date.plusDays(day+3).format(DateTimeFormatter.ofPattern(dayPattern));
        try {
            selectCustomDateFromDatePicker(dateToBeSelected,calendarHeaderMonth,calendarHeaderYear,nextButton,dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        clickOn(closeDate);
    }

    public void adjustSliderRange() throws Exception {
        List<WebElement> sliderButtons = getDriver().findElements(sliderTolTip);
        WebElement firstButton = sliderButtons.get(0);
        WebElement secondButton = sliderButtons.get(1);

        moveSlider(firstButton,-25,0);
        moveSlider(secondButton,-30,0);
    }

//    verification/Assertion methods

    public void verifyDetails(String value){
        try {
            BasePaseJS.ScrollDownPage(0,-450);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isDetailsDisplayed = isElementDisplayed(groupDetaisContainer);
        System.out.println(isDetailsDisplayed);
        Assert.assertTrue(isDetailsDisplayed);
    }

    public void verifyHotels(){
        try {
            BasePaseJS.ScrollDownPage(0,450);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String totalHotels = getTextFromElement(noOfHotels).substring(0,2);
        int hotels = Integer.parseInt(totalHotels);
        System.out.println(hotels);

        boolean hotelsFound = false;
        if (hotels > 1){
            hotelsFound = true;
        }
        Assert.assertTrue(hotelsFound);
    }
}
