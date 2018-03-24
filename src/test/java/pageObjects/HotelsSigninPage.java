package pageObjects;

import framework.BasePage;
import framework.BasePaseJS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by nkkhan on 2/13/18.
 */
public class HotelsSigninPage extends BasePage {

    //Locators
    private By signInEmail = By.id("sign-in-email");
    private By password = By.id("sign-in-password");
    private By signinButton = By.xpath("//button[@type='submit'][contains(text(),'Sign in')]");
    private By signinTabAfterSignin = By.id("hdr-account");
    private By yourAccountMenu = By.id("search-button");
    private By accountTitle = By.xpath("//div[@id='activityResultsCtr']/div/div/a");

    private By signoutButton = By.id("offerAddButtonAtf");
    private By signInMenuList = By.xpath("//ul[@class='nav nav-group-2']/li[1]/span/ul/li/a");
    private By verificationMessage = By.xpath("//label[@for='nucaptcha-answer']");
    //Hotels.com - Cheap Hotels, Discount Rates & Hotel Deals


// aria-label


//  send text methods
    public void insertEmail(String email){
        sendTextSlowly(signInEmail,email);
    }

    public void insertpassword(String pass){
        sendTextSlowly(password,pass);
    }

    public void clickOnSignInButton(){
        clickOn(signinButton);
    }

    public void clickOnSigninTabAfterSignIn(){
        clickOn(signinTabAfterSignin);
    }

    public void clickOnYourAccount(){
        clickOn(yourAccountMenu);
    }

    public void verifyAccountName(String expecterName){
        String actualName = getTextFromElement(accountTitle);
        Assert.assertEquals(actualName,expecterName);
    }

    public void clickOnSignout(){
        clickOn(signoutButton);
    }

    public void verifyLogout(){
        clickOn(yourAccountMenu);
        Assert.assertTrue(isSignInOptionPresent());
    }

    private boolean isSignInOptionPresent(){
        List<WebElement> menuList = list(signInMenuList);
        for (WebElement element : menuList){
            if (element.getText().contains("Sign in")){
                return true;
            }
        }
        return false;
    }

    public void loginSecurityMsgVerification(String msg){
        String actualMsg = getTextFromElement(verificationMessage);
        Assert.assertEquals(actualMsg,msg);
    }
}
