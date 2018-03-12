package pageObjects;

import framework.BasePage;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by nkkhan on 2/13/18.
 */
public class HotelsHomePage extends BasePage {

    //Locators
    private By whereToDestination = By.xpath("//input[@name='q-destination']");
    private By checkInField = By.cssSelector("input.query-localised-check-in");
    private By checkOutField = By.cssSelector("input.query-localised-check-out");
    private By DatePickerCheckInAndOut = By.xpath("//div[@class='widget-datepicker-bd']/descendant::a");
    private By calendarHeader = By.xpath("//div[@class='widget-daterange-cont']/div[1]/div/div");
    private By nextButton = By.xpath("//html//div[2]/div[1]/button[2]");
    private By nextButtonForList = By.xpath("//div[@class='widget-datepicker-hd'][1]/button[2]");
    private By noOfNight = By.xpath("//span[@class='widget-query-num-nights']");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By numberOfHotels = By.xpath("//div[@class='filters-summary']/p");
    private By occupancy = By.cssSelector("#qf-0q-compact-occupancy");
    private By roomsDropDown = By.xpath("//select[@class='query-rooms']");
    private By adultDropDown = By.xpath("//select[@id='qf-0q-room-0-adults']");
    private By childrenDropDown = By.xpath("//select[@id='qf-0q-room-0-children']");
    private By child1DropDown = By.xpath("//select[@id='qf-0q-room-0-child-0-age']");
    private By child2DropDown = By.xpath("//select[@id='qf-0q-room-0-child-1-age']");
    private By autosuggestResults = By.xpath("//div[@class='autosuggest-category-result']");
    private String dayPattern = "MM-dd-yyyy";


    //methods
    public void clickOnCheckInField() throws InterruptedException {
        clickOn(checkInField);
    }
    public void selectCurrentDateFromCheckInField() throws InterruptedException {
        selectCurrentDateFromDatePicker(dayPattern, DatePickerCheckInAndOut);
    }


    public void selectDate(int addNumberOfDay) throws InterruptedException {
        LocalDate date = LocalDate.now();
        String dateToBeSelected = date.plusDays(addNumberOfDay).format(DateTimeFormatter.ofPattern(dayPattern));

        try {
            selectCustomDateFromDatePicker(dateToBeSelected,calendarHeader,nextButton,DatePickerCheckInAndOut);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void clickOnCheckOutField() throws InterruptedException {
        clickOn(checkOutField);
    }
    public void selectCustomDateFromCheckOutField(String dateMMddyyyy) throws ParseException {
        selectCustomDateFromDatePicker(dateMMddyyyy,calendarHeader,nextButton, DatePickerCheckInAndOut);
    }
    public void verifyValidNights(int expectedNumberOfNights){
        String actualNumberOfNights = getTextFromElement(noOfNight);
        String expectedNights = String.valueOf(expectedNumberOfNights);
        System.out.println("Night selected = "+actualNumberOfNights);
        System.out.println("Night expected = "+expectedNights);
        Assert.assertEquals(actualNumberOfNights,expectedNights);
    }

    public void verifyValidNightsDisplayed(String date){
        String actualNumberOfNights = getTextFromElement(noOfNight);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d-yyyy");
        LocalDate todaysDate = LocalDate.now();
        String currentDate = todaysDate.format(formatter);

        LocalDate today = LocalDate.parse(currentDate,formatter);
        LocalDate customDate = LocalDate.parse(date,formatter);

        long days = ChronoUnit.DAYS.between(today, customDate);
        String expectedNights = String.valueOf(days);

        System.out.println("Night selected = "+actualNumberOfNights);
        System.out.println("Night expected = "+expectedNights);
        Assert.assertEquals(actualNumberOfNights,expectedNights);

    }

    public void clickOnDestField(){
        clickOn(whereToDestination);
    }

    public void insertIntoDestField(String destination) throws InterruptedException {
        sendText(whereToDestination,destination);
        Thread.sleep(2000);
    }

    public void clickOnSearch(){
        clickOn(searchButton);
    }
    public void verifyHotelsSelected(){
        String noOfHotels = getDriver().findElement(numberOfHotels).getText().substring(0,2);
        int numberOfHotels = Integer.parseInt(noOfHotels);
        boolean check=false;
        if (numberOfHotels > 0){
            check = true;
        }
        Assert.assertTrue(check);
    }

    public void selectFromoccupancyOption(String value){
        selectDropDownMenuByVisibleText(occupancy,value);
    }

    public void selectFromRoomsOption(String value){
        selectDropDownMenuByVisibleText(roomsDropDown,value);
    }

    public void selectFromAdultOption(String value){
        selectDropDownMenuByVisibleText(adultDropDown,value);
    }

    public void selectFromchildrenOption(String value){
        selectDropDownMenuByVisibleText(childrenDropDown,value);
    }

    public void selectFromChild1Option(String value){
        selectDropDownMenuByVisibleText(child1DropDown,value);
    }

    public void selectFromchild2Option(String value){
        selectDropDownMenuByVisibleText(child2DropDown,value);
    }

    public void clickOnAutoSuggestResult(String city){

        try {
            selectFromAutoCompleteMenu(autosuggestResults,city);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
