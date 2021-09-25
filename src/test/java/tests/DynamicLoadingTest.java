package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DynamicLoadingEx2Page;
import pageObjects.DynamicLoadingMainPage;
import pageObjects.WelcomePage;
import utils.Utils;

public class DynamicLoadingTest extends BaseTest {
    // test objects
    DynamicLoadingMainPage dlmp;
    DynamicLoadingEx2Page dl2p;

    @Test
    public void tc01_check_home_page_display(){
        WelcomePage wp = new WelcomePage(driver);
        heading = Utils.readProperty("wpTitle");
        pageName = "Welcome";
        Assert.assertTrue(wp.isPageDisplayed(wp.getPageHeading(),heading),pageName + " page is not loaded");
    }

    @Test(dependsOnMethods = "tc01_check_home_page_display")
    public void tc02_go_to_DynamicLoading_Main_page(){
        WelcomePage wp = new WelcomePage(driver);
        pageName = Utils.readProperty("dlmpName");
        heading = Utils.readProperty("dlmpHeading");
        wp.goToPage(pageName);
        dlmp = new DynamicLoadingMainPage(driver);
        Assert.assertTrue(dlmp.isPageDisplayed(dlmp.getPageHeading(),heading),pageName + " page is not loaded");
    }

    @Test(dependsOnMethods = "tc02_go_to_DynamicLoading_Main_page")
    public void tc03_go_to_DynamicLoading_Ex2_page(){
        dlmp = new DynamicLoadingMainPage(driver);
        heading = Utils.readProperty("dl2pHeading");
        dlmp.goToPage(heading);
        dl2p = new DynamicLoadingEx2Page(driver);
        Assert.assertTrue(dl2p.isPageDisplayed(dl2p.getPageHeading(),heading),pageName + " page is not loaded");
    }

    @Test(description = "clicking on 'start' button and verifying that 'Hello World! is displayed", dependsOnMethods = "tc03_go_to_DynamicLoading_Ex2_page")
    public void tc04_verify_finish_text(){
        dl2p = new DynamicLoadingEx2Page(driver);
        dl2p.start();
        String expectedText = "Hello World!";
        String actualText = dl2p.getFinishText();
        Assert.assertEquals(actualText, expectedText, expectedText + " is not displayed");
    }
}
