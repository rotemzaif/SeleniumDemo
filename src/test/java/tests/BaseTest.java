package tests;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * The BaseTest class consists of @BeforeClass and @AfterClass methods which will be executed in all tests classes.
 *
 * */
public class BaseTest {
    // common objects
    WebDriver driver;

    // common variables
    String heading = "";
    String pageName = "";

//    @BeforeClass
//    public void setup(){
//        String driverType = Utils.readProperty("driverType");
//        switch (driverType){
//            case "chrome":
//                ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
//                break;
//            case "firefox":
//                FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX);
//                break;
//            case "edge":
//                EdgeDriverManager.getInstance(DriverManagerType.EDGE);
//                break;
//            case "ie":
//                InternetExplorerDriverManager.getInstance(DriverManagerType.IEXPLORER);
//                break;
//        }
//        // for file download purposes
////        String downLoadPath = Utils.readProperty("fileDownLoadPath");
//        String downLoadPath ="C:\\Users\\Rotem\\git_repo\\capitolisHomeAssignment\\src\\main\\resources\\data";
//        HashMap<String, Object> chromePrefs = new HashMap<>();
//        chromePrefs.put("profile.default_content_settings.popups", 0);
//        chromePrefs.put("download.default_directory", System.getProperty(downLoadPath));
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", chromePrefs);
//        //
//
//        //
//        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
//        driver.get(Utils.readProperty("url"));
//    }

    @BeforeClass
    public void setup() throws IOException {
        String driverType = Utils.readProperty("driverType");
        switch (driverType){
            case "chrome":
                ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                break;
            case "firefox":
                FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX);
                break;
            case "edge":
                EdgeDriverManager.getInstance(DriverManagerType.EDGE);
                break;
            case "ie":
                InternetExplorerDriverManager.getInstance(DriverManagerType.IEXPLORER);
                break;
        }
        String projPath = System.getProperty("user.dir");
        String downloadPath = projPath + "\\src\\main\\resources\\data";
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--disable-notifications");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        driver.get(Utils.readProperty("url"));
    }



    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
