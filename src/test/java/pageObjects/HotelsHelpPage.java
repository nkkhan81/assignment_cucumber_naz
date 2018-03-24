package pageObjects;

import framework.BasePage;
import framework.BasePaseJS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by nkkhan on 2/13/18.
 */
public class HotelsHelpPage extends BasePage {

//  Locators
    private By settings = By.xpath("//div[@class='info-summary col k']");
    private By newsLetter = By.xpath("//a[@href='#/articles/408/390']");
    private By signUpNow = By.xpath("//div[@class='article-detail-container col']/p/a");
    private By emailField = By.id("txt_email_address");
    private By confirmEmail = By.id("txt_retype_email_address");
    private By firstName = By.id("txt_first_name");
    private By lastName = By.id("txt_last_name");
    private By checkBox = By.id("createchkbx");
    private By registerButton = By.id("btn_billing_submit");
    private By confirmationMsg = By.xpath("//div[@class='widget_content']/p");

    private String mdifiedEmail = generateString(4)+"example@gmail.com";




//  methods

    public void clickOnNewsLetter(){
        try {
            BasePaseJS.ScrollDownPage(0,300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> elements = list(settings);
        elements.get(5).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickOn(newsLetter);
    }

    public void clickOnSignUpNow(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickOn(signUpNow);
    }

    public void insertEmail(){
        sendText(emailField,mdifiedEmail);
    }

    public void retypeEmail(){
        sendText(confirmEmail,mdifiedEmail);
    }

    public void insertfirstName(String name){
        sendText(firstName,name);
    }

    public void insertLastName(String name){
        sendText(lastName,name);
    }

    public void clickCheckBox(){
        clickOn(checkBox);
    }
    public void clickRegButton(){
        clickOn(registerButton);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifySubscription(){
        String actualConfirmationMsg = getTextFromElement(confirmationMsg);
        String expectedMsg = "The email address "+mdifiedEmail+" is now registered to receive our newsletter";
        Assert.assertEquals(actualConfirmationMsg,expectedMsg);
    }


}
