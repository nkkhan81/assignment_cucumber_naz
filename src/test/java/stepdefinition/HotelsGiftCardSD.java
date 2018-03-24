package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.HotelsGiftCardPage;
import pageObjects.HotelsHomePage;
import pageObjects.HotelsSearchPage;

import java.text.ParseException;

/**
 * Created by mohammadmuntakim on 6/9/17.
 */
public class HotelsGiftCardSD {

    private HotelsGiftCardPage giftCardPage = new HotelsGiftCardPage();
    private HotelsHomePage homePage = new HotelsHomePage();

    @When("^I switch to the new window, scroll down and click on Buy now button$")
    public void scrollAndClickBuyNow() throws InterruptedException {
        giftCardPage.scrollAndClickBuyNow();
    }

    @Then("^I verify (.+) at the right panel of the gift card section$")
    public void verifyCart(String text) {
        giftCardPage.verifyEmptyCart(text);
    }

    @When("^I insert (.+) into (.+) field$")
    public void insertText(String value, String field) throws InterruptedException {

        switch (field) {
            case "message input":
                giftCardPage.insertMessage(value);
                break;
            case "Recipient Name":
                giftCardPage.insertRecipientName(value);
                break;
            case "Recipient email":
                giftCardPage.insertEmail(value);
                break;
            case "Purchaser Name":
                giftCardPage.insertPurchaserName(value);
                break;
        }
    }

    @When("^I click on (.+)$")
    public void clickOnOptions(String field) throws InterruptedException {

        switch (field) {
            case "Birthday tab":
                giftCardPage.clickOnBirthday();
                break;
            case "second birthday design":
                giftCardPage.clickOnSecondDesign();
                break;
            case "email tab for delivery option":
                giftCardPage.clickOnEmailTab();
                break;
            case "calendar view icon":
                giftCardPage.clickOnCalenderViewIcon();
                break;
            case "Remove at shopping cart":
                giftCardPage.clickOnremove();
                break;
            case "Yes button at popup menu":
                giftCardPage.clickOnYes();
                break;
        }
    }


    @When("^I select 250 as gift card value$")
    public void selectGiftCardAmount() throws InterruptedException {
        giftCardPage.clickOnAmount();
    }

    @When("^I pick (2-23-2019) from date picker for card delivery date$")
    public void pickDateFromDatePicker(String date) throws InterruptedException, ParseException {
        giftCardPage.pickDate(date);
    }

    @When("^I scroll down and click on Add to cart button$")
    public void addToCart() throws InterruptedException{
        giftCardPage.scrollAndClickAddToCart();
        Thread.sleep(3000);
    }


    @Then("^I verify the cart that item Qty is (1), card amount is dollar (250.00)$")
    public void verifySelectedAirport(String qty, String amount) {
        giftCardPage.verifyQtyAndAmount(qty, amount);
    }

    @Then("^I also verify that item can be removed from cart and (.+)$")
    public void verifyItemRemoval(String text){
        giftCardPage.verifyEmptyCart(text);
    }
}