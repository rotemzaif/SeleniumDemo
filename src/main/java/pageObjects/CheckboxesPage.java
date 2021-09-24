package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckboxesPage extends BasePage {
    // page elements
    @FindBy(css = "#checkboxes > input")
    private List<WebElement> checkboxList;

    public CheckboxesPage(WebDriver driver) {
        super(driver);
        pageHeading = driver.findElement(By.cssSelector("div > h3"));
    }

    // getters
    public List<WebElement> getCheckboxList() {
        return checkboxList;
    }

    // page actions methods
    public void toggleCheckBox(WebElement checkbox, Toggle toggle){
        if(toggle == Toggle.CHECK){
            if(!checkbox.isSelected())
                click(checkbox);
        }
        else if(toggle == Toggle.UNCHECK){
            if(checkbox.isSelected())
                click(checkbox);
        }
    }

    // page validation methods
    public String isCheckBoxSelected(WebElement checkbox){
        if(checkbox.isSelected())
            return "checked";
        else return "unchecked";
    }

    public enum Toggle{
        CHECK, UNCHECK
    }

    public boolean compareCheckboxState(String actualState, String expectedState){
        return actualState.equals(expectedState);

    }
}
