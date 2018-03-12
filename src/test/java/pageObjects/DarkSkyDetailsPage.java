package pageObjects;

import framework.BasePage;
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
public class DarkSkyDetailsPage extends BasePage {

    private By selectedDate = By.xpath("//div[@class='date']");
    private static LocalDate day = LocalDate.now();


    public void verifyTomorrowsDateIsClickable(){
        WebElement element = getDriver().findElement(selectedDate);
        boolean isClickable = isAttribtuePresent(element,"href");
        System.out.println("################## clickable = "+isClickable+" ####################");
        Assert.assertFalse(isClickable);
    }

    private static String LocalDateInDarkSkyFormat(){
        DateTimeFormatter timeMachineDay = DateTimeFormatter.ofPattern("d");
        String tomorrowsDate = timeMachineDay.format(day.plusDays(1));
        int tomorrowsday = Integer.parseInt(tomorrowsDate);
        String[] suffixes =
                //    0     1     2     3     4     5     6     7     8     9
                { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                        //    10    11    12    13    14    15    16    17    18    19
                        "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
                        //    20    21    22    23    24    25    26    27    28    29
                        "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                        //    30    31
                        "th", "st" };
        String dayStr = suffixes[tomorrowsday];

        DateTimeFormatter firstPart = DateTimeFormatter.ofPattern("EEEE, MMM d");
        String dateFirstPart = firstPart.format(day.plusDays(1));

        String fullDateMiddlePart = dayStr+", ";

        DateTimeFormatter secondPart = DateTimeFormatter.ofPattern("yyyy");
        String dateSecondPart = secondPart.format(day.plusDays(1));

        String fullDate = (dateFirstPart+fullDateMiddlePart+dateSecondPart);
        return fullDate;
    }

}
