package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BasicAuthPage;
import pageObjects.WelcomePage;
import utils.Utils;

public class BasicAuthTest extends BaseTest {
    // test objects
    BasicAuthPage bap ;
    @Test
    public void tc01_check_home_page_display(){
        WelcomePage wp = new WelcomePage(driver);
        heading = Utils.readProperty("wpTitle");
        pageName = "Welcome";
        Assert.assertTrue(wp.isPageDisplayed(wp.getPageHeading(),heading),pageName + " page is not loaded");
    }

    @Test(dependsOnMethods = "tc01_check_home_page_display")
    public void tc02_go_to_Basic_Auth_page_with_valid_credentials() throws InterruptedException {
        WelcomePage wp = new WelcomePage(driver);
        pageName = Utils.readProperty("bapName");
        wp.goToPage(pageName);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//
////        bap = new BasicAuthPage(driver);
//        for (String str : driver.getWindowHandles()) {
//            System.out.println(str);
//            driver.switchTo().window(str);
//        }
//        Alert alert = driver.switchTo().alert();
    }
}
