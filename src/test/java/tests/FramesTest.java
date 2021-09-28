package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.FramesPage;
import pageObjects.IFramePage;
import pageObjects.WelcomePage;
import utils.Utils;

public class FramesTest extends BaseTest {
    // objects
    FramesPage fp;
    IFramePage ifp;

    @Test
    public void tc01_check_home_page_display(){
        WelcomePage wp = new WelcomePage(driver);
        pageName = "Welcome";
        expectedHeading = Utils.readProperty("wpTitle");
        Assert.assertTrue(wp.isPageDisplayed(wp.getPageHeading(),expectedHeading),pageName + " page is not loaded");
    }

    @Test(dependsOnMethods = "tc01_check_home_page_display")
    public void tc02_go_to_Frames_page() throws InterruptedException {
        WelcomePage wp = new WelcomePage(driver);
        pageName = "Frames";
        wp.goToPage(pageName);
        Thread.sleep(1000);
        fp = new FramesPage(driver);
        expectedHeading = Utils.readProperty("fpTitle");
        Assert.assertTrue(fp.isPageDisplayed(fp.getPageHeading(),expectedHeading), pageName + " page is not loaded");
    }

    @Test(dependsOnMethods = "tc02_go_to_Frames_page")
    public void tc03_go_to_iFrames_page(){
        fp = new FramesPage(driver);
        pageName = "iFrame";
        fp.goToPage(pageName);
        ifp = new IFramePage(driver);
        expectedHeading = Utils.readProperty("ifpTitle");
        Assert.assertTrue(ifp.isPageDisplayed(ifp.getPageHeading(),expectedHeading), pageName + " page is not loaded");
    }

    @Test(description = "enter text in the editor and verify that was indeed written", dependsOnMethods = "tc03_go_to_iFrames_page")
    public void tc04_enter_text_to_editor()throws InterruptedException {
        ifp = new IFramePage(driver);
        ifp.moveToFrame();
        String textToInsert = "rotem";
        ifp.enterTextToEditor(textToInsert);
        ifp.moveBackToMainWindow();
        ifp.moveToFrame();
        String actualText = ifp.getEditorText();
        Assert.assertEquals(actualText, textToInsert);
    }
}
