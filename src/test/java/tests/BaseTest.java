package tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

/**
 * The BaseTest class consists of @BeforeClass and @AfterClass methods which will be executed in all tests classes.
 *
 * */
public class BaseTest {
    WebDriver driver;
    @BeforeClass
    public void setup(){
        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        driver.get("https://www.saucedemo.com/");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
