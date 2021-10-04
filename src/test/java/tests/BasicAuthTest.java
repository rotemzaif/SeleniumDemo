package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BasicAuthPage;
import pageObjects.WelcomePage;
import utils.Utils;
import java.io.IOException;

public class BasicAuthTest extends BaseTest {
    // test objects
    WelcomePage wp;
    BasicAuthPage bap ;
    @Test
    public void tc01_check_home_page_display(){
        wp = new WelcomePage(driver);
        expectedHeading = Utils.readProperty("wpTitle");
        pageName = "Welcome";
        Assert.assertTrue(wp.isPageDisplayed(wp.getPageHeading(),expectedHeading),pageName + " page is not loaded");
    }

    @Test(description = "moving to Basic Auth page, entering valid credentials and verifying that page is displayed", dependsOnMethods = "tc01_check_home_page_display")
    public void tc02_go_to_Basic_Auth_page_with_valid_credentials() throws InterruptedException, IOException {
        wp = new WelcomePage(driver);
        pageName = Utils.readProperty("bapName");
        wp.goToPage(pageName);
        // enter valid credentials (admin/admin)
        wp.login("success");
        Thread.sleep(2000);
        bap = new BasicAuthPage(driver, "success");
        expectedHeading = Utils.readProperty("bapHeading");
        Assert.assertTrue(bap.isPageDisplayed(bap.getPageHeading(),expectedHeading), pageName + " page is not loaded");
    }

    @Test(description = "verifying that Congratulations statement is displayed", dependsOnMethods = "tc02_go_to_Basic_Auth_page_with_valid_credentials")
    public void tc03_Basic_Auth_Page_check_success_statement(){
        bap = new BasicAuthPage(driver, "success");
        String expectedLabel = Utils.readProperty("successStatement");
        Assert.assertEquals(bap.getconfirmationLabelText(), expectedLabel, "Expected 'Congratulations ....' label is not displayed");
    }

    @Test(description = "opening chrome settings and clearing its password cache in the last hour", dependsOnMethods = "tc03_Basic_Auth_Page_check_success_statement")
    public void tc04_clear_broswer_cache_after_successful_login() throws InterruptedException {
        bap = new BasicAuthPage(driver, "success");
        bap.goToWelcomePage();
        Thread.sleep(1000);
        wp = new WelcomePage(driver);
        wp.clearCache(driver,Utils.readProperty("homePageURL"));
        wp = new WelcomePage(driver);
        Thread.sleep(2000);
        expectedHeading = Utils.readProperty("wpTitle");
        pageName = "Welcome";
        Assert.assertTrue(wp.isPageDisplayed(wp.getPageHeading(),expectedHeading),pageName + " page is not loaded");
    }

    @Test(description = "logining with invalid credentials and verifying that confirmation not authorization labels are displayed", dependsOnMethods = "tc04_clear_broswer_cache_after_successful_login")
    public void tc05_Basic_Auth_Page_login_with_invalid_credentials() throws InterruptedException, IOException{
        wp = new WelcomePage(driver);
        pageName = Utils.readProperty("bapName");
        wp.goToPage(pageName);
        wp.login("invalidCredentials");
        Thread.sleep(2000);
        bap = new BasicAuthPage(driver,"");
        Assert.assertFalse(bap.isConfrimationLabelExist() && bap.isAuthorizationLabelDisplayed(),
                "confirmation or authorization statements are displayed when loging with invalid credentials");
    }

    @Test(description = "canceling loging attempt and verifying that authorization statements is displayed", dependsOnMethods = "tc05_Basic_Auth_Page_login_with_invalid_credentials")
    public void tc06_Basic_Auth_Page_login_cancel()throws InterruptedException, IOException{
        driver.get(Utils.readProperty("homePageURL"));
        wp = new WelcomePage(driver);
        pageName = Utils.readProperty("bapName");
        wp.goToPage(pageName);
        wp.login("cancel");
        Thread.sleep(2000);
        bap = new BasicAuthPage(driver,"cancel");
        String expectedStatement = Utils.readProperty("cancelStatement");
        Assert.assertEquals(bap.getAuthorizationText(), expectedStatement);
    }
}
