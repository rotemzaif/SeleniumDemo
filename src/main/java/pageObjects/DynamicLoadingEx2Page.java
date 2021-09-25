package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * DynamicLoadingEx2Page class derives from Base page with all its variables, objects and methods.
 * The class includes start button, loading and finish elements, start() and text getter methods.
 * The class deals with page function: when clicking on 'start' button, loading process start and when it ends,
 * 'Hello World!' is displayed
 * */
public class DynamicLoadingEx2Page extends BasePage {

    // page elements
    @FindBy(css = ".example > #start button")
    WebElement startBtn;
    @FindBy(css = ".example > #loading")
    WebElement loading;
    @FindBy(css = "#finish > h4")
    WebElement finishTxt;

    public DynamicLoadingEx2Page(WebDriver driver) {
        super(driver);
        pageHeading = driver.findElement(By.cssSelector(".example > h4"));
    }

    // page getters
    public String getFinishText(){
        return getText(finishTxt);
    }

    // page actions methods
    public void start(){
        click(startBtn);
        wait.until(ExpectedConditions.visibilityOf(loading));
    }
}
