package stepdefinition;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.HotelsHomePage;
import pageObjects.HotelsPackagesPage;
import pageObjects.HotelsSigninPage;

import java.text.ParseException;

/**
 * Created by mohammadmuntakim on 6/9/17.
 */
public class HotelsSignInSD {

    private HotelsSigninPage signinPage = new HotelsSigninPage();
    private HotelsHomePage homePage = new HotelsHomePage();

    @When("^I provide my email address$")
    public void insertEmail() {
        signinPage.insertEmail("nkkhan81@yahoo.com");
    }

    @When("^I provide my password$")
    public void insertPassword() {
        signinPage.insertpassword("Dhaka120");
    }



    @When("^I click (Sign in button|My Account tab|Sign Out Button)$")
    public void clickOnButtons(String field) {

        switch (field) {
            case "Sign in button":
                signinPage.clickOnSignInButton();
                break;
            case "My Account tab":
                signinPage.clickOnSigninTabAfterSignIn();
                break;
            case "Sign Out Button":
                signinPage.clickOnSignout();
        }
    }


    @Then("^I verify the account name (.+) for successful login$")
    public void verifyAccountName(String text) {
        signinPage.verifyAccountName(text);
    }

    @Then("^I verify that I logged out successfully and Sign in button is available$")
    public void verifyLogOut() {
        signinPage.verifyLogout();
    }

    @Then("^I verify the message (.+) which confirm human activity$")
    public void loginSecurityMsgVerification(String text) {
        signinPage.loginSecurityMsgVerification(text);
    }
}