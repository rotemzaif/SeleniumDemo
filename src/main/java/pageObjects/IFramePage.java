package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * iFramePage class derives from BasePage with all its objects, variables and methods.
 * iFramePage class consists of 2 elements:
 *  a frame element with 'move to frame' and 'move back from frame' methods.
 *  a text editor element with reading and writing methods
 * */
public class IFramePage extends BasePage {
    // page elements
    WebElement textArea;
    @FindBy(css = "#mce_0_ifr")
    WebElement frame;

    public IFramePage(WebDriver driver) {
        super(driver);
        pageHeading = driver.findElement(By.cssSelector(".example > h3"));
    }

    // page getters
    public String getEditorText(){
        return getText(textArea);
    }

    // page actions methods
    public void moveToFrame(){
        driver.switchTo().frame(frame);
        textArea = driver.findElement(By.cssSelector("#tinymce > p"));
    }

    public void moveBackToMainWindow(){
        driver.switchTo().defaultContent();
    }

    public void enterTextToEditor(String text){
        fillText(textArea, text);
    }
}
