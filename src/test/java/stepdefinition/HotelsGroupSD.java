package stepdefinition;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.HotelsGroupPage;
import pageObjects.HotelsHomePage;

/**
 * Created by mohammadmuntakim on 6/9/17.
 */
public class HotelsGroupSD {

    private HotelsGroupPage groupPage = new HotelsGroupPage();
    private HotelsHomePage homePage = new HotelsHomePage();

    @When("^I insert (.+) rooms needed per night$")
    public void insertRoomNumbers(String rooms) throws InterruptedException {
        groupPage.insertRoomNumbers(rooms);
    }

    @When("^I select (.+) as (room type|Group type)$")
    public void selectFromDropDown(String value, String field) throws InterruptedException {

        switch (field) {
            case "room type":
                groupPage.selectRoomType(value);
                break;
            case "Group type":
                groupPage.selectGroupType(value);
                break;
        }
    }

    @When("^I select 2-4 star for rating field$")
    public void selectRating(){
        groupPage.selectRating();
    }

    @When("^I insert (.+) at Destination field$")
    public void insertDestination(String city){
        groupPage.insertCity(city);
    }

    @When("^I clicked on (.+) from the list$")
    public void selectDestination(String city) throws InterruptedException {
        groupPage.selectFromDestinationList(city);
    }

    @When("^I clicked Check-in Date and I add ([1-9][0-9]*) days with today and select$")
    public void pickCheckInDate(int day){
        groupPage.selectCheckInDate(day);
    }

    @When("^I clicked Check-out Date and I add ([1-9][0-9]*) days with Check-in Date and select$")
    public void pickCheckOutDate(int day)  {
        groupPage.selectCheckOutDate(day);
    }

    @When("^I adjust Ideal nightly budget on slider$")
    public void handleSlider() throws Exception {
        groupPage.adjustSliderRange();
    }

    @When("^I insert (.+) as email and (.+) as full name$")
    public void insertNameAndEmail(String email, String name){
        groupPage.insertNameEmail(email,name);
    }

    @When("^I clicked on (.+) button$")
    public void clickOnButtons(String field) throws InterruptedException {
        switch (field) {
            case "Continue":
                groupPage.clickOnContinueStep3();
                break;
            case "Next: View Hotels":
                groupPage.clickOnViewHotel();
                break;
            case "(first) Continue":
                groupPage.clickContinueButton1();
                break;
        }
    }

    @When("^I uncheck newsletter check box and click on continue button again$")
    public void uncheckAndContinue(){
        groupPage.clickContinueButton2();
    }

    @When("^I insert (.+) as nearby place$")
    public void insertNearByPlace(String place){
        groupPage.insertNearByPlace(place);
    }

    @When("^I insert (.+) at additional request field$")
    public void insertComments(String comments){
        groupPage.insertComments(comments);
    }

    @Then("^I verify (.+) displayed$")
    public void verifyDetails(String details) {
        groupPage.verifyDetails(details);
    }


    @Then("^I also verify hotels are displayed$")
    public void verifyHotels() {
        groupPage.verifyHotels();
    }
}