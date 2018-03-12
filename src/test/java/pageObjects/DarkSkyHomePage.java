package pageObjects;

import framework.BasePage;
import framework.BasePaseJS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import stepdefinition.SharedSD;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nkkhan on 2/10/18.
 */
public class DarkSkyHomePage extends BasePage {

    private By element = By.xpath("id('timeline')/div[1]/div[3]/span/span");
    private By weekDays = By.xpath("//a[@class='day']/span //span[@class='name']");
    private By minimumTemp  = By.xpath("//a[@class='day']/span //span[@class='minTemp']");
    private By maximumTemp  = By.xpath("//a[@class='day']/span //span[@class='maxTemp']");
    private By timeMachineButton = By.xpath("//a[@class='button']");
    private By timeMachineDate = By.xpath("//button[@class='pika-button pika-day']");
    private By today = By.xpath("//span[@class='name'][contains(text(),'Today')]");
    private By todayMinTemp = By.xpath("//a[@class='day revealed']//span[@class='tempRange']//span[@class='minTemp']");
    private By todayMaxTemp = By.xpath("//a[@class='day revealed']//span[@class='tempRange']//span[@class='maxTemp']");
    private By parentMinTemps = By.xpath("//div[@class='highLowTemp swip']/span[1]/span[1]");
    private By parentMaxTemps = By.xpath("//div[@class='highLowTemp swip']/span[3]/span[1]");
    private LocalDate day = LocalDate.now();



    public void verifyHomePage(){
        Assert.assertEquals(SharedSD.getDriver().getTitle(),"Dark Sky - 260 Broadway, New York City, NY", "Invalid Home Page");
    }


    public List<WebElement> timeLineValues(){
        List<WebElement> lists = getDriver().findElements(element);
        return lists;
    }

    public void compareTimeLineWithLocalTime(){

        List<WebElement> darkSkyHours = getDriver().findElements(element);
        List<String> localComputerHours = new ArrayList<>();

        //creating a local time instance
        LocalTime time = LocalTime.now();
        //formatting the time as hour with am pm
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ha");

        int addHour = 0;

        for(int i=0; i<12; i++){
            String hourWithAmpm = dtf.format(time.plusHours(addHour)).toLowerCase();
            localComputerHours.add(i,hourWithAmpm);
            addHour += 2;
        }
        for (int i=1; i<darkSkyHours.size(); i++){

            String hourFromWebTimeline = darkSkyHours.get(i).getText();
            String hourFromLocalTime = localComputerHours.get(i);

            Assert.assertEquals(hourFromWebTimeline, hourFromLocalTime);
        }
    }

    public List<WebElement> webWeekdaysList(){
        List<WebElement> darkSkyWeekDays = getDriver().findElements(weekDays);
        return darkSkyWeekDays;
    }

    public static List<String> localWeekdaysList(){
        List<String> localWeekDays = new ArrayList<>();

        LocalDate day = LocalDate.now();
        //formatting the time as hour with am pm
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE");
        int addDay = 0;

        for(int i=0; i<8; i++){
            String localWeekDay = dtf.format(day.plusDays(addDay));
            localWeekDays.add(i,localWeekDay);
            addDay ++;
        }
        localWeekDays.set(0,"Today");
        return localWeekDays;
    }

    public void compareWebWeekdaysWithLocal(){
        for (int i=0; i<localWeekdaysList().size(); i++){
            System.out.println(webWeekdaysList().get(i).getText()+" = "+localWeekdaysList().get(i));
            Assert.assertEquals(webWeekdaysList().get(i).getText(),localWeekdaysList().get(i));
        }
    }

    public List<WebElement> minTemps(){
        List<WebElement> minTempValues = getDriver().findElements(minimumTemp);
        return minTempValues;
    }

    public List<WebElement> maxTemps(){
        List<WebElement> maxTempValues = getDriver().findElements(maximumTemp);
        return maxTempValues;
    }

    public void verifyMinAndMaxTemp(){
        for(int i=0; i<minTemps().size(); i++) {
            String minValue = minTemps().get(i).getText();
            String maxValue = maxTemps().get(i).getText();

            int minimumTemp = Integer.parseInt(minValue.substring(0, minValue.length() - 1));
            int maximumTemp = Integer.parseInt(maxValue.substring(0, maxValue.length() - 1));

            boolean check;
            if (minimumTemp <= maximumTemp) {
                check = true;
            }else {
                check = false;
            }
            Assert.assertTrue(check);
        }
    }

    public void clickOnTimeMachine(){
        try {
            BasePaseJS.ScrollDownPage(0,1250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickOn(timeMachineButton);
    }

    public void clickOnTomorrowsDate(){
        DateTimeFormatter timeMachineDay = DateTimeFormatter.ofPattern("d");
        String tomorrowsDate = timeMachineDay.format(day.plusDays(1));

        List<WebElement> timeMachineDays = getDriver().findElements(timeMachineDate);
        for(int i=0; i<timeMachineDays.size(); i++){
            if(timeMachineDays.get(i).getText().equals(tomorrowsDate)){
                timeMachineDays.get(i).click();
                break;
            }
        }
    }

    public void verifyDaysOfTheWeek(){
        List<WebElement> actualDays = getDriver().findElements(weekDays);
        List<String> expectedDays = localWeekdaysList();

        for (int i=0; i<localWeekdaysList().size(); i++){
            System.out.println("Actual  =  Expected");
            System.out.println(actualDays.get(i).getText()+" = "+expectedDays.get(i));
            Assert.assertEquals(webWeekdaysList().get(i).getText(),localWeekdaysList().get(i));
        }
    }

    public void clickOnTodayBar(){
        clickOn(today);
    }

    public void verifyTodayWithParentBar(){
        String todayMinimumTemp = getTextFromElement(todayMinTemp).substring(0,getTextFromElement(todayMinTemp).length()-1);
        String todayMaximumTemp = getTextFromElement(todayMaxTemp).substring(0,getTextFromElement(todayMaxTemp).length()-1);

        List<WebElement> parentBarsMinTemp = getDriver().findElements(parentMinTemps);
        String parentMinTempUnformated = parentBarsMinTemp.get(0).getText();
        String parentMinTemp =parentMinTempUnformated.substring(0,parentMinTempUnformated.length()-1);

        List<WebElement> parentBarsMaxTemp = getDriver().findElements(parentMaxTemps);
        String parentMaxTempUnformated = parentBarsMaxTemp.get(0).getText();
        String parentMaxTemp =parentMaxTempUnformated.substring(0,parentMaxTempUnformated.length()-1);
        System.out.println();

        System.out.println("Today -> minimum = "+todayMinimumTemp+"| maximum = "+todayMaximumTemp);
        System.out.println("parent -> minimum = "+parentMinTemp+"| maximum = "+parentMaxTemp);

        Assert.assertEquals(todayMinimumTemp,parentMinTemp);
        Assert.assertEquals(todayMaximumTemp,parentMaxTemp);
    }


}
