package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.CheckboxesPage;
import pageObjects.WelcomePage;
import utils.Utils;

import java.util.List;

public class CheckBoxesTest extends BaseTest {
    // variables
    String expectedState;
    String actualState;
    List<WebElement> checkboxList;
    int counter = 0;

    // objects
    WebElement checkbox;

    @Test
    public void tc01_check_home_page_display(){
        WelcomePage wp = new WelcomePage(driver);
        heading = Utils.readProperty("wpTitle");
        pageName = "Welcome";
        Assert.assertTrue(wp.isPageDisplayed(wp.getPageHeading(),heading),pageName + " page is not loaded");
    }

    @Test(description = "moving to Checkboxes page and verifying that page is displayed", dependsOnMethods = "tc01_check_home_page_display")
    public void tc02_go_to_CheckBoxes_page() throws InterruptedException {
        WelcomePage wp = new WelcomePage(driver);
        pageName = "Checkboxes";
        wp.goToPage(pageName);
        Thread.sleep(1000);
        CheckboxesPage cbp = new CheckboxesPage(driver);
        heading = Utils.readProperty("cbpTitle");
        checkboxList = cbp.getCheckboxList();
        Assert.assertTrue(cbp.isPageDisplayed(cbp.getPageHeading(),heading), pageName + " page is not displayed");
    }

    @Test(dataProvider = "getCheckboxes", description = "toggle checkbox to the opposite state and verify it was toggled", dependsOnMethods = "tc02_go_to_CheckBoxes_page")
    public void tc03_toggle_Checkbox1(WebElement checkbox) throws InterruptedException {
        counter += 1;
        CheckboxesPage cbp = new CheckboxesPage(driver);
        if(cbp.isCheckBoxSelected(checkbox).equals("checked")){
            cbp.toggleCheckBox(checkbox, CheckboxesPage.Toggle.UNCHECK);
            expectedState = "unchecked";
        }
        else {
            cbp.toggleCheckBox(checkbox, CheckboxesPage.Toggle.CHECK);
            expectedState = "checked";
        }
        // getting checkbox state after toggle
        actualState = cbp.isCheckBoxSelected(checkbox);
        Assert.assertNotEquals(cbp.compareCheckboxState(actualState, expectedState), "checkbox #" + counter + " was not toggled");
    }

    @DataProvider
    public Object[][] getCheckboxes(){
        int rows = checkboxList.size();
        WebElement[][] checkboxes = new WebElement[rows][1];
        for (int i = 0; i < rows; i++) {
            checkboxes[i][0] = checkboxList.get(i);
        }
        return checkboxes;
    }
}
