package pageObjects;

import framework.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by nkkhan on 2/13/18.
 */
public class HotelsSearchPage extends BasePage {

    //Locators
    private By cityStateCountry = By.xpath("//div[@class='summary']/h1");
    private By nightInfo = By.xpath("//span[@class='search-nights']");
    private By roomAndPersonInfo = By.xpath("//span[@class='search-rooms']");
    private By searchedDateRange = By.xpath("//span[@class='search-dates']");



    //methods
    public void getCityInfo(String expectedCityInfo){
        String actualCityInfo = getTextFromElement(cityStateCountry);
        System.out.println("\nActual city information = "+actualCityInfo);
        System.out.println("Expected = "+expectedCityInfo);
        Assert.assertEquals(actualCityInfo,expectedCityInfo);
    }

    public void dateRange(){

    }

    public void verifyOccupancyInformation(String ExpectedInfo){
        String actualnights = getTextFromElement(nightInfo);
        String actualRoomAndPerson = getTextFromElement(roomAndPersonInfo);
        String actualInfo = actualnights+", "+actualRoomAndPerson;

        System.out.println("\nActual info = "+actualInfo);
        System.out.println("Expected info = "+ExpectedInfo);

        Assert.assertEquals(actualInfo, ExpectedInfo);

    }

    public void verifyDateRange(int days){
        LocalDate date = LocalDate.now();
        String tomorrow = date.plusDays(1).format(DateTimeFormatter.ofPattern("EEE d"));
        String selectedCheckoutDay = date.plusDays(days+1).format(DateTimeFormatter.ofPattern("EEE d MMMM yyyy"));
        String expectedDateRange = tomorrow+" - "+selectedCheckoutDay;

        String actualDateRange = BasePage.getDriver().findElement(searchedDateRange).getText();
        Assert.assertEquals(actualDateRange,expectedDateRange);
        System.out.println("\nActual range = "+actualDateRange);
        System.out.println("Expected range = "+expectedDateRange);
    }

}
