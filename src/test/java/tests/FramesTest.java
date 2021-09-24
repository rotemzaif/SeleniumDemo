package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.FramesPage;
import pageObjects.WelcomePage;
import utils.Utils;

public class FramesTest extends BaseTest {

    @Test
    public void tc01_check_home_page_display(){
        WelcomePage wp = new WelcomePage(driver);
        pageName = "Welcome";
        heading = Utils.readProperty("wpTitle");
        Assert.assertTrue(wp.isPageDisplayed(wp.getPageHeading(),heading),pageName + " page is not loaded");
    }

    @Test(dependsOnMethods = "tc01_check_home_page_display")
    public void tc02_go_to_Frames_page() throws InterruptedException {
        WelcomePage wp = new WelcomePage(driver);
        pageName = "Frames";
        wp.goToPage(pageName);
        Thread.sleep(1000);
        FramesPage fp = new FramesPage(driver);
        heading = Utils.readProperty("fpTitle");
        Assert.assertTrue(fp.isPageDisplayed(fp.getPageHeading(),heading), pageName + " page is not loaded");
    }

    @Test(dependsOnMethods = "tc02_go_to_Frames_page")
    public void tc03_go_to_iFrames_page() throws InterruptedException {
        FramesPage fp = new FramesPage(driver);
        fp.goToPage("iFrame");
        Thread.sleep(5000);
    }
}
