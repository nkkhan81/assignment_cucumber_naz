package framework;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import stepdefinition.SharedSD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * Created by mohammadmuntakim on 6/9/17.
 */
public class BasePage {

//  Actions: List all actions that you are going to perform on elements
//  eg. click, insert text in a field, get text from an element etc

	//  1) To hover over an element and expand it for next action
	public static void mouseOverAndClick(By hoverElementLocator,By clickLocator)  {

		WebElement element = elementWithWait(hoverElementLocator);

		Actions action = new Actions(getDriver());
		action.moveToElement(element).build().perform();


		clickOn(clickLocator);

	}

	public static void mouseOver(By hoverElementLocator) {

		WebElement element = elementWithWait(hoverElementLocator);

		Actions action = new Actions(getDriver());
		action.moveToElement(element).build().perform();
	}


	//  2) To click an element
	public static void clickOn(By locator){
		elementWithWait(locator).click();
	}



	//  3) To set value to an input field
	public void sendText(By locator, String value){
		elementWithWait(locator).sendKeys(value);
	}


	//  4) To get text value from an element
	public String getTextFromElement(By locator){

		return elementWithWait(locator).getText();

	}


	//  5) To check an element is enabled or not(eg. a link). Return true or false
	public boolean isElementEnabled(By locator){

		return elementWithWait(locator).isEnabled();

	}



	//  6) To check an element is displayed or not. Return true or false
	public boolean isElementDisplayed(By locator){

		return elementWithWait(locator).isDisplayed();

	}



	//  7) To check an element is selected or not(eg. a radio button). Return true or false
	public boolean isElementSelected(By locator){

		return elementWithWait(locator).isSelected();

	}

	//  8) To get text value from an attribute of an element
	public String getAttributeValue(By locator, String attributeName){

		WebElement element = elementWithWait(locator);
		return element.getAttribute(attributeName);

	}


	//  9)  To select a dropdown menu and select a valu by visible text
	public void selectDropDownMenuByVisibleText(By locator, String value){
		Select dropDown = new Select(elementWithWait(locator));
		dropDown.selectByVisibleText(value);
	}


	//  10) To select a dropdown menu and select a value by index no
	public void selectDropDownMenuByIndex(By locator, int index){
		Select dropDown = new Select(elementWithWait(locator));
		dropDown.selectByIndex(index);
	}


	//  11) To select an element by creating list of elements
	public WebElement findElementByList(By locator, String comparableText){
		List<WebElement> lisOfLinks = getDriver().findElements(locator);
		WebElement foundElement = null;
		try {
			for (WebElement element : lisOfLinks) {
				if (element.getText().equals(comparableText)){
					foundElement = element;
					break;
				}
			}
			return foundElement;
		} catch (NoSuchElementException e) {
			System.out.println("Element not found in the list");
			e.printStackTrace();
			return null;
		}
	}
	public List list(By locator){
		List<WebElement> elementList = getDriver().findElements(locator);
		return elementList;
	}

	public void selectFromAutoSuggestJS(By locator, String jsScriptForElement, String matchingValue){
		useKey(locator,Keys.ARROW_DOWN);
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		String jsScript = jsScriptForElement;
		String elementText = (String) js.executeScript(jsScript);

		int i=0;

		while(!elementText.equalsIgnoreCase(matchingValue)){
			i++;
			useKey(locator,Keys.ARROW_DOWN);
			elementText = (String) js.executeScript(jsScript);
			System.out.println(elementText);

			if(i==9){
				break;
			}
		}
	}


	//  12) to select current date from a  date picker
	public void selectCurrentDateFromDatePicker(String pattern, By datePicker) throws InterruptedException {
		//SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		LocalDate date = LocalDate.now();
		String currentDate = date.format(DateTimeFormatter.ofPattern(pattern));

		List<WebElement> days = getDriver().findElements(datePicker);
		for (WebElement day : days) {
			String expectedDay = day.getText();
			if (expectedDay.equals(currentDate)){
				day.click();
				Thread.sleep(3000);
			}
		}
	}


	//  13) simplified the driver instance
	public static WebDriver getDriver(){
		return SharedSD.getDriver();
	}


	//  14) switches to windows based on index
	public void switchToWindow(int index){
		List<String> listOfWindows = new ArrayList<>(getDriver().getWindowHandles());
		getDriver().switchTo().window(listOfWindows.get(index));
	}



	//  15) closes current window and switches back to root window
	public void switchToRootWindowAndCloseCurrentWindow(){
		List<String> listOfWindows = new ArrayList<String>(getDriver().getWindowHandles());
		for (int i = 1; i < listOfWindows.size(); i++){
			getDriver().switchTo().window(listOfWindows.get(i));
			getDriver().close();
		}
		getDriver().switchTo().window(listOfWindows.get(0));
	}


	//  16) select an item from AutoComplete menu
	public static String getText = null;
	public void selectFromAutoCompleteMenu(By listLocator, String value) throws InterruptedException {

		List<WebElement> autoCompleteLists = new ArrayList<>(getDriver().findElements(listLocator));
		try {
			for (WebElement listElement : autoCompleteLists){
				if (listElement.getText().contains(value)){
					getText = listElement.getText();
					listElement.click();
					Thread.sleep(2000);
					break;
				}
			}
		} catch (NoSuchElementException nse) {
			nse.printStackTrace();
		}
	}


	//  17) to perform various alert actions
	public void alertHandler(By alertPopupElement, String inputText) throws InterruptedException {
		clickOn(alertPopupElement);
		Thread.sleep(2000);
		switchAction().sendKeys(inputText);
		switchAction().accept();
		Thread.sleep(2000);
		clickOn(alertPopupElement);
		Thread.sleep(2000);
		System.out.println(switchAction().getText());
		switchAction().dismiss();
		Thread.sleep(2000);
	}

	private Alert switchAction(){
		Alert alert = getDriver().switchTo().alert();
		return alert;
	}


	// 18)  To select a custom date from date picker
	public void selectCustomDateFromDatePicker(String inputDate, By calendarHeader,By nextButton, By dates) throws ParseException {
		SimpleDateFormat simpleDF = new SimpleDateFormat("MM-dd-yyyy");
		Date date = simpleDF.parse(inputDate);

		SimpleDateFormat sdfDay = new SimpleDateFormat("d");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM");
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		String day = sdfDay.format(date);
		String month = sdfMonth.format(date);
		String year = sdfYear.format(date);
		String monthAndYear = (month+" "+year);

		while(!elementWithWait(calendarHeader).getText().contains(monthAndYear)){

			elementWithWait(nextButton).click();
		}


		List<WebElement> days = getDriver().findElements(dates);

		for(WebElement element : days){
			String expectedDate = element.getText();
			if(expectedDate.equals(day)){
				element.click();
				break;
			}
		}
	}

	//if the locator is different for calendar Header Month and Year, use bellow method
	public void selectCustomDateFromDatePicker(String inputDate, By calendarHeaderMonth, By calendarHeaderYear, By nextButton, By dates) throws ParseException {
		SimpleDateFormat simpleDF = new SimpleDateFormat("MM-dd-yyyy");
		Date date = simpleDF.parse(inputDate);

		SimpleDateFormat sdfDay = new SimpleDateFormat("d");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM");
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		String day = sdfDay.format(date);
		String month = sdfMonth.format(date).toLowerCase();
		String year = sdfYear.format(date);
		String monthAndYear = (month+" "+year);

		while(!(elementWithWait(calendarHeaderMonth).getText().toLowerCase()+" "+elementWithWait(calendarHeaderYear).getText()).contains(monthAndYear)){
			elementWithWait(nextButton).click();
		}


		List<WebElement> days = getDriver().findElements(dates);

		for(WebElement element : days){
			String expectedDate = element.getText();
			if(expectedDate.equals(day)){
				element.click();
				break;
			}
		}
	}


	//  19)  method for press any key
	public static void useKey(By locator, Keys key){
		elementWithWait(locator).sendKeys(key);
	}

	//  20)  method for typing upper case text in a input field.
	public void useUpperCaseForInputField(By locator, String text){

		//WebElement to which keyboard action is performed
		WebElement element = elementWithWait(locator);

		//creating object of actions class
		Actions builder = new Actions(getDriver());

		//Generating an action to type a text in caps
		Action typeInCaps = builder.keyDown(element,Keys.SHIFT)
				.sendKeys(element,text)
				.keyDown(element,Keys.SHIFT)
				.build();
		typeInCaps.perform();
	}

	//	21)
	public void clickOnBrowserBackArrow() {
		SharedSD.getDriver().navigate().back();
	}

	//	22)
	public void clickOnBrowserForwardArrow() {
		SharedSD.getDriver().navigate().forward();
	}

	//	23)
	public void refreshBrowser() {
		SharedSD.getDriver().navigate().refresh();
	}

	public void doubleClicOn(By locator){
		WebElement element = elementWithWait(locator);
		Actions actions = new Actions(getDriver());
		actions.doubleClick(elementWithWait(locator)).build().perform();
	}

	//	24)	Get the Title of the page
	public String getTitle(){
		return getDriver().getTitle();
	}

	//	25)	to verify if text/element is clickable or not
	public boolean isClickable(WebElement element){
		try{
			WebDriverWait wait = new WebDriverWait(getDriver(), 6);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	//	26)	to check whether an attribute is present in a tag
	public boolean isAttribtuePresent(WebElement element, String attribute) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null){
				result = true;
			}
		} catch (Exception e) {}

		return result;
	}

	//	27)	to slide into a slideBar
	public void sliderMove(By slider) throws InterruptedException {
//		WebElement sliderElement = getDriver().findElement(slider);
//
//		//Using Action Class
//		Actions move = new Actions(getDriver());
//		Action action = move.dragAndDropBy(sliderElement, xAxis, 0).build();
//		action.perform();


//		WebElement silder = elementWithWait(slider);
//		Dimension dim = silder.getSize();
//		int x = dim.getWidth();
//		System.out.println(x);
		Actions action = new Actions(getDriver());
//		// Code of Silder
//		act.clickAndHold(silder).moveByOffset(x-xAxis, 0).release().build().perform();
//
//		WebElement element = getDriver().findElement(slider);
//		action.click(element).build().perform();
//		Thread.sleep(1000);
//		for (int i = 0; i < 30; i++) {
//			action.sendKeys(Keys.ARROW_LEFT).build().perform();
//			Thread.sleep(200);
//		}


		List<WebElement> tolTips = getDriver().findElements(slider);
		WebElement tolTip1 = tolTips.get(1);
		WebElement tolTip2 = tolTips.get(0);
		action.click(tolTip1).build().perform();
		Thread.sleep(1000);
		for (int i = 0; i < 30; i++) {
			action.sendKeys(Keys.ARROW_LEFT).build().perform();
			Thread.sleep(200);
		}
		Actions action2 = new Actions(getDriver());
		action2.click(tolTip2).build().perform();
		Thread.sleep(1000);
		for (int i = 0; i < 40; i++) {
			action2.sendKeys(Keys.ARROW_RIGHT).build().perform();
			Thread.sleep(200);
		}


		/**
		 * WebElement elem = driver.findElement(By.className("ytp-progress-bar"));

		 int width = elem.getSize().getWidth();

		 Actions act = new Actions(driver);
		 act.moveToElement(elem).moveByOffset((width/2)-2, 0).click().perform();
		 */
	}
	//if you want to move a slider from one point to another
	//here 'WebElement slider' is the button of the slider bar which you want to click and hold
	public void moveSlider(WebElement slider, int xOffset, int yOffset) throws Exception {

		Actions moveSlider = new Actions(getDriver());
		Action action = moveSlider.clickAndHold(slider)
				.moveByOffset(xOffset, yOffset)
				.release()
				.build();
		action.perform();
		Thread.sleep(500);
	}

	//Another variation of this methods is when you're using not offset parameters, but locators for start and end points between which you want to move your element.
	public void dragAndDrop2(WebElement element, int xOffset, int yOffset) throws Exception {
		Actions builder = new Actions(getDriver());
		Action dragAndDrop = builder.dragAndDropBy(element, xOffset, yOffset) .build();
		dragAndDrop.perform();
	}

	//sendText method which slowly enter the text into text field
	public void sendTextSlowly(By locator, String value){
		String val = value;
		WebElement element = getDriver().findElement(locator);
//		element.clear();

		char[] chars = value.toCharArray();

		for (int i = 0; i < chars.length; i++){
			char c = chars[i];
			String s = new StringBuilder().append(c).toString();
			element.sendKeys(s);
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	//Generate Random alphanumeric string

	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public String generateString(int length) {
		Random random = new Random();
		StringBuilder builder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
		}
		return builder.toString();
	}

	//copy text from text field
	public void sendTextAndCopy(By locator, String text){
		WebElement elem = getDriver().findElement(locator);
		elem.sendKeys(text);
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		elem.sendKeys(selectAll);

		String copy = Keys.chord(Keys.CONTROL, "c");
		elem.sendKeys(copy);
	}

	//paste text in a text field
	public void pasteText(By locator){
		WebElement elem = getDriver().findElement(locator);
//		elem.click();
//		String selectAll = Keys.chord(Keys.CONTROL, "a");
//		elem.sendKeys(selectAll);

		String copy = Keys.chord(Keys.CONTROL, "v");
		elem.sendKeys(copy);
	}


	//	private method for fluentWait
	private static WebElement elementWithWait(By locator){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
				.withTimeout(15, TimeUnit.SECONDS)
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


//DIFFERENT WAIT MECHANISM

	//implicit wait
	public static void implicitWait(){
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().get("some URL");
		WebElement myDynamicElement = getDriver().findElement(By.id("some element id"));
	}

	//expected wait
	public static void witUntilElementClickable(){
		WebDriverWait wait = new WebDriverWait(getDriver(),10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("someID")));
	}

	//page load wait
	public static void pageLoadWait(){
		getDriver().manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	}

	//Script Timeout
	public static void synchronousScript(){
		getDriver().manage().timeouts().setScriptTimeout(30,TimeUnit.SECONDS);
	}
}
