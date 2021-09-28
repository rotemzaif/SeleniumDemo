package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.JQueryUiMenuPage;
import pageObjects.WelcomePage;
import utils.Utils;

public class JQueryMenuTest extends BaseTest {

    // test objects
    JQueryUiMenuPage jqmp;

    // test variables
    String filename = Utils.readProperty("excelFileName");
    @Test
    public void tc01_check_home_page_display(){
        WelcomePage wp = new WelcomePage(driver);
        expectedHeading = Utils.readProperty("wpTitle");
        pageName = "Welcome";
        Assert.assertTrue(wp.isPageDisplayed(wp.getPageHeading(),expectedHeading),pageName + " page is not loaded");
    }

    @Test(dependsOnMethods = "tc01_check_home_page_display")
    public void tc02_go_to_JQueryUIMenu_page(){
        WelcomePage wp = new WelcomePage(driver);
        pageName = Utils.readProperty("jqmpName");
        expectedHeading = Utils.readProperty("jqmpHeading");
        wp.goToPage(pageName);
        jqmp = new JQueryUiMenuPage(driver);
        Assert.assertTrue(jqmp.isPageDisplayed(jqmp.getPageHeading(),expectedHeading),pageName + " page is not loaded");
    }

    @Test(description = "deleting menu.xls file if it exist in the download directory", dependsOnMethods = "tc02_go_to_JQueryUIMenu_page")
    public void tc03_check_if_file_exist(){
        boolean isFileExist = Utils.isFileExist(filename);
        if(isFileExist)
            Utils.deleteFile(filename);
        isFileExist = Utils.isFileExist(filename);
        Assert.assertFalse(isFileExist,filename + " file already exists and failed to be deleted");
    }

    @Test(description = "selecting enabled/downloads/ excel file and verifying that file was downloaded", dependsOnMethods = "tc03_check_if_file_exist")
    public void tc04_download_excel_file() throws InterruptedException {
        jqmp = new JQueryUiMenuPage(driver);
        jqmp.selectMenu(jqmp.getMainMenuList(),"Enabled");
        jqmp.selectMenu(jqmp.getEnabledMenuList(),"Downloads");
        jqmp.selectMenu(jqmp.getDownloadMenuList(),"Excel");
        Thread.sleep(5000);
        // check if file was downloaded
        Assert.assertTrue(Utils.isFileExist(filename), filename + " was not downloaded");
    }

    @Test(description = "extracting 'tax' value and verifying that it matches the expected value", dependsOnMethods = "tc04_download_excel_file")
    public void tc05_check_Tax_field_value(){
        String excelFilePath = Utils.getDownLoadDir() + "\\" + Utils.readProperty("excelFileName");
        String sheetName = Utils.readProperty("excelSheetName");
        String actualTaxValue = Utils.getFieldValue(excelFilePath, sheetName, "tax");
        System.out.println("Tax value frpom excel file: " + actualTaxValue);
        String expectedValue = "0.13";
        Assert.assertEquals(actualTaxValue, expectedValue);
    }
}
