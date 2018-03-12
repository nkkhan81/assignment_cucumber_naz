package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.DarkSkyDetailsPage;
import pageObjects.DarkSkyHomePage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nkkhan on 2/10/18.
 */
public class DarkSkyTimelineSD {
    private DarkSkyHomePage darkSkyHomePage = new DarkSkyHomePage();
    private DarkSkyDetailsPage detailsPage = new DarkSkyDetailsPage();

    @Given("^I am on home page of darksky$")
    public void iAmOnHomePage(){
        darkSkyHomePage.verifyHomePage();
    }

    @When("^I create a List of timeline time values$")
    public void listTimeLineValues(){
        darkSkyHomePage.timeLineValues();
    }

    @When("^I create a List of website weekdays including today$")
    public void listForWebWeekdays(){
        List weekDays = new ArrayList();
        weekDays = darkSkyHomePage.webWeekdaysList();
    }

    @When("^I create a List of local weekdays including today$")
    public void listForLocalWeekdays(){

        List localWeekDays = new ArrayList();
        localWeekDays = darkSkyHomePage.localWeekdaysList();
    }

    @When("^I create a List of (minimum temperature|maximum temperature) value$")
    public void ListsForTemp(String value){
        switch (value){
            case "minimum temperature":
                darkSkyHomePage.minTemps();
                break;
            case "maximum temperature":
                darkSkyHomePage.maxTemps();
                break;
        }

    }

    @When("^I click on (Time Machine button|Tomorrow date|todays bar)$")
    public void clickOnElement(String value){
        switch (value){
            case "Time Machine button":
                darkSkyHomePage.clickOnTimeMachine();
                break;
            case "Tomorrow date":
                darkSkyHomePage.clickOnTomorrowsDate();
                break;
            case "todays bar":
                darkSkyHomePage.clickOnTodayBar();
                break;
        }

    }

    @Then("^I compare both list values and verify$")
    public void printTimelineAndLocaTimelValues(){
        darkSkyHomePage.compareTimeLineWithLocalTime();
    }

    @Then("^I compare both list and verify that values are equal$")
    public void compareWeekDays(){
        darkSkyHomePage.compareWebWeekdaysWithLocal();
    }

    //compare each minimum value with maximum (minimum < maximum)
    @Then("^I verify each maximum value is grater than it's minimum value$")
    public void verifyTemp(){
        darkSkyHomePage.verifyMinAndMaxTemp();
    }

    //I verify that tomorrow date displayed on the details page
    @Then("^I verify that tomorrow date displayed on the details page$")
    public void verifyTomorrow(){
        detailsPage.verifyTomorrowsDateIsClickable();
    }

    @Then("^I verify days of the week is displayed in correct order$")
    public void daysOfTheWeek(){
        darkSkyHomePage.verifyDaysOfTheWeek();
    }

    @Then("^I verify low and high temp displayed correctly on parent bar$")
    public void verifyTempWithParentBar(){
        darkSkyHomePage.verifyTodayWithParentBar();
    }
}
