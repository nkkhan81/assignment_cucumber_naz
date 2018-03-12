package framework;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import stepdefinition.SharedSD;

import java.util.concurrent.TimeUnit;

/**
 * Created by nkkhan on 2/5/18.
 */
public class BasePaseJS {

    /**
     * JavaScriptExecutor is an interface which provides mechanism to execute javascript
     * through selenium driver. It provides "executeScript" and
     * "executeAsyncScript" methods, to run javascript in the context of the
     * currently selected frame or windows
     */


    /**
     *  1) Hover over to products
     *  2) Click on suits
     *  3) Scroll down on the page
     *  4) verify all link at the bottom of the page. If any links are missing please fail the test
     *  @throws InterruptedException
     */


    //bellow methods can be generalize as BasePage method


    public static void setBrowserWindowSize(int width, int height, String url){
        System.setProperty("webdriver.chrome.driver","/Users/nkkhan/Projects/technoSoft/webDrivers/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size="+width+","+height);

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY,options);

        //this will open chrome with set size
        WebDriver driver = new ChromeDriver(cap);
        driver.get(url);
        driver.quit();
    }


    public static void ClickOnJS(By locator) throws InterruptedException {
        WebElement element = getDriver().findElement(locator);
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].click();",element);
        Thread.sleep(3000);
    }


    public static void ScrollDownPage(int xAxis, int yAxis) throws InterruptedException {
        Thread.sleep(4000);
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();

        //vertical scroll down by 150 pixel
        jse.executeScript("window.scrollBy("+xAxis+","+yAxis+")");
        Thread.sleep(10000);
    }



    //  private method for fluentWait
    private static WebElement elementWithWait(By locator){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(15,TimeUnit.SECONDS)
                .pollingEvery(1,TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotFoundException.class)
                .withMessage("WebDriver waited for 15 seconds but still  couldn't find the element, therefore timeout exception has been thrown"+ locator.toString());
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return element;
    }

    //  13) simplified the driver instance
    public static WebDriver getDriver(){
        return SharedSD.getDriver();
    }
}
