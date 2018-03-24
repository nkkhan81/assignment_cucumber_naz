package stepdefinition;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.HotelsHelpPage;
import pageObjects.HotelsHomePage;
import pageObjects.HotelsSigninPage;

/**
 * Created by mohammadmuntakim on 6/9/17.
 */
public class HotelsHelpSD {

    private HotelsHelpPage helpPage = new HotelsHelpPage();
    private HotelsHomePage homePage = new HotelsHomePage();

    @When("^I switch the the webdriver to new help window$")
    public void switchWindow() {
        helpPage.switchToWindow(1);
    }

    @When("^I expand Your Settings at the left pannel and click on Newsletter subscription$")
    public void insertPassword() {
        helpPage.clickOnNewsLetter();
    }

    @When("^I click signUp now link$")
    public void clickOnSignUpNow() {
        helpPage.clickOnSignUpNow();
    }

    @When("^I provide an email address in email field$")
    public void insertEmail(){
        helpPage.insertEmail();
    }

    @When("^I provide the same emil address in confirm email$")
    public void retypeEmail(){
        helpPage.retypeEmail();
    }
    @When("^I provide first name as (.+) and last name as (.+)$")
    public void insertFirstAndLastName(String firstName, String lastName){
        helpPage.insertfirstName(firstName);
        helpPage.insertLastName(lastName);
    }

    @When("^I click the check box of terms and condition$")
    public void selectCheckBox(){
        helpPage.clickCheckBox();
    }

    @When("^I click Register button$")
    public void clickRegisterButton(){
        helpPage.clickRegButton();
    }

    @Then("^I verify you subscribe successfully$")
    public void verifySubscription(){
        helpPage.verifySubscription();
    }

}