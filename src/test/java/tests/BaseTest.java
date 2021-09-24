package tests;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.Utils;

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

    @BeforeClass
    public void setup(){
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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        driver.get(Utils.readProperty("url"));
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
