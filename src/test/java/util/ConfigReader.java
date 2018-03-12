package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Mohammad Mutakim on 11/11/17.
 */
public class ConfigReader {

    private String darksky_url;
    private String hotels_url;

    private String browser;
    private String chromeDriverPath;
    private String geckoDriverPath;

    public ConfigReader() {

        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream("config.properties");
            //load a properties file
            properties.load(inputStream);

            //get the property value and print it out
            this.darksky_url = properties.getProperty("darksky_url");
            this.hotels_url = properties.getProperty("hotels_url");

            this.chromeDriverPath = properties.getProperty("chrome_driver_path");
            this.geckoDriverPath = properties.getProperty("gecko_driver_path");
            this.browser = properties.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getDarksky_url() { return darksky_url; }
    public String getHotels_url() { return hotels_url; }

    public String getBrowser() {
        return browser;
    }
    public String getChromeDriverPath() {
        return chromeDriverPath;
    }
    public String getGeckoDriverPath() {
        return geckoDriverPath;
    }

}
