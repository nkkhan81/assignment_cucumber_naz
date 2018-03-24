package stepdefinition;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.HotelsHomePage;
import pageObjects.HotelsPackagesPage;

import java.text.ParseException;

/**
 * Created by mohammadmuntakim on 6/9/17.
 */
public class HotelsPackagesSD {

    private HotelsPackagesPage packagesPage = new HotelsPackagesPage();
    private HotelsHomePage homePage = new HotelsHomePage();

    @When("^I insert (.+) at destination and select (.+) from list$")
    public void insertDestination(String destination, String selectDestination) {
        packagesPage.insertDestination(destination, selectDestination);
    }


    @When("^I switch the driver to new window$")
    public void switchDriverToWindow() throws InterruptedException, ParseException {
        packagesPage.switchDriver(1);
    }

    @When("^I click (Check Availability|Book|Continue booking) button$")
    public void clickOnButtons(String field) {

        switch (field) {
            case "Check Availability":
                packagesPage.clickOnAvailability();
                break;
            case "Book":
                packagesPage.clickOnBook();
                break;
            case "Continue booking":
                packagesPage.clickOnContinueBooking();
                break;
        }
    }

    @When("^I scroll down and insert invalid credit card number as (.+)$")
    public void insertCardNumber(String cardNumber) {
        packagesPage.scrollAndInsertCardNumber(cardNumber);
    }

    @Then("^I verify the error message (Please enter a valid card number.)$")
    public void verifyInvalidCardMessage(String text) {
        packagesPage.verifyErrorMessage(text);
    }

}