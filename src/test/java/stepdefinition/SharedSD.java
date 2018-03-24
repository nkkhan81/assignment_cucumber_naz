package stepdefinition;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import util.ConfigReader;
import org.openqa.selenium.Platform;

public class SharedSD {

	private static WebDriver driver = null;

	//variable for sauceLab driver settings
	private static final String USER_NAME = "koushiktec";
	private static final String ACCESS_KEY = "ec28ec96-c9e2-493c-ad25-57dcabbcf6ca";
	private static final String SAUCE_URL = "https://"+USER_NAME+":"+ACCESS_KEY+"@ondemand.saucelabs.com:443/wd/hub";

	@Before
	public static void before() {

		ConfigReader configReader = new ConfigReader();

		File chrome = new File(configReader.getChromeDriverPath());
		File gecko = new File(configReader.getGeckoDriverPath());

		//browser choosing option based on the property value of config.property file
		String browser = configReader.getBrowser();
		switch (browser.toLowerCase()){
			case "chrome":
				System.setProperty("webdriver.chrome.driver",chrome.getAbsolutePath());
				driver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver",gecko.getAbsolutePath());
				driver = new FirefoxDriver();
				break;
			case "safari":
				driver = new SafariDriver();
				break;


			case "sauce":
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				caps.setCapability("platform", "Windows 10");
				caps.setCapability("version", "64.0");

				try {
					driver = new RemoteWebDriver(new URL(SAUCE_URL),caps);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;


			case "grid":
				//node web driver location
				System.setProperty("webdriver.chrome.driver", "/Users/nkkhan/Projects/SeleniumUdemy/chromedriver");
				//set DesiredCapabilities
				DesiredCapabilities dc = new DesiredCapabilities();
				dc.setBrowserName("chrome");
				dc.setPlatform(Platform.WINDOWS);

				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;
		}


		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(configReader.getHotels_url());
//		driver.get(configReader.getDarksky_url());

		driver.manage().window().fullscreen();
	}

	@After
	public static void after() {

		if (driver != null){
			driver.manage().deleteAllCookies();
			driver.quit();
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
