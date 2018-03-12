package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.HotelsHomePage;
import pageObjects.HotelsSearchPage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.text.ParseException;

/**
 * Created by mohammadmuntakim on 6/9/17.
 */
public class HotelsDatePickerSD {

    private HotelsHomePage hotels = new HotelsHomePage();
    private HotelsSearchPage searchPage = new HotelsSearchPage();
    private By popup = By.xpath("//button[@class='cta widget-overlay-close']");

    @Given("^I am on hotels home page$")
    public void hotelsHomePage(){
//        if (BasePage.getDriver().findElement(popup).isEnabled() ==true){
//            BasePage.getDriver().findElement(popup).click();
//        }
        Assert.assertEquals(SharedSD.getDriver().getTitle(),"Hotels.com - Cheap Hotels, Discount Rates & Hotel Deals", "Invalid Home Page");
    }

    @When("^I click on (Check in|Check out|Destination|search button) field on home page$")
    public void clickOnFields(String fields) throws InterruptedException {

        switch (fields) {
            case "Check in":
                hotels.clickOnCheckInField();
                break;
            case "Check out":
                hotels.clickOnCheckOutField();
                break;
            case "Destination":
                hotels.clickOnDestField();
                break;
            case "search button":
                hotels.clickOnSearch();
                break;
        }
    }

    @When("^I click on (occupancy|rooms|adult|children|child1|child2) drop down menu and select (More options…|1|2|2|<1|3)$")
    public void dropDowns(String field, String value) throws InterruptedException {

        switch (field) {
            case "occupancy":
                hotels.selectFromoccupancyOption(value);
                break;
            case "rooms":
                hotels.selectFromRoomsOption(value);
                break;
            case "adult":
                hotels.selectFromAdultOption(value);
                break;
            case "children":
                hotels.selectFromchildrenOption(value);
                break;
            case "child1":
                hotels.selectFromChild1Option(value);
                break;
            case "child2":
                hotels.selectFromchild2Option(value);
                break;
        }
    }

    @When("^I select tomorrow date from date picker$")
    public void pickTomorrowDate() throws InterruptedException {
        hotels.selectDate(1);
    }

    @When("^I select current date from date picker$")
    public void pickCurrentDate() throws InterruptedException {
        hotels.selectDate(0);
    }

    @When("^I select (2-23-2019) date from date picker$")
    public void pickCustomDate(String date) throws InterruptedException, ParseException {
        hotels.selectCustomDateFromCheckOutField(date);
    }

    @When("^I add (6) days with current date and click on that date$")
    public void selectCheckOutDate(int numberOfDays) throws InterruptedException, ParseException {
        hotels.selectDate(numberOfDays+1);
    }

    @When("^I verify (6) nights have been selected$")
    public void verifyNights(int nights) throws InterruptedException, ParseException {
        hotels.verifyValidNights(nights);
    }

    @Then("^I verify number of nights displayed correctly for the date (.+)$")
    public void verifySelectedAirport(String date) {
        hotels.verifyValidNightsDisplayed(date);
    }

    @When("^I insert (Dallas) into Destination field on home page$")
    public void insertCity(String destination) throws InterruptedException {
        hotels.insertIntoDestField(destination);
    }

    @When("^I click on (.+) from auto suggestion$")
    public void clickOnAutoSuggestedItem(String city) throws InterruptedException {
        hotels.clickOnAutoSuggestResult(city);
    }

    @Then("^I veryfy list of hotels are displayed$")
    public void verifyHotels(){
        hotels.verifyHotelsSelected();
    }

    @Then("^I verify city info (.+) display correctly$")
    public void verifyCity(String cityName){
        searchPage.getCityInfo(cityName);
    }

    @Then("^I verify (6) day range displayed correctly on result page$")
    public void verifyDateRange(int days){
        searchPage.verifyDateRange(days);
    }


    @Then("^I also verify (.+) have been selected$")
    public void verifyOccupancy(String occupancy){
        searchPage.verifyOccupancyInformation(occupancy);
    }
}
