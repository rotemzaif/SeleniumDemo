package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
/**
 * The BasePage class consists of objects and methods that are common in the rest of the page objects.
 * Other page objects classes will inherit from this class and use its objects and methods.
 * It is part of the POM.
 * */
public class BasePage {
    // objects
    WebDriver driver;
    JavascriptExecutor js;
    WebElement pageHeading;

    // constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        js = (JavascriptExecutor) driver;
    }

    // elements actions
    public void fillText(WebElement el, String text){
        elementHighLight(el, "yellow");
        el.clear();
        el.sendKeys(text);
    }

    public void click(WebElement el){
        elementHighLight(el, "yellow");
        el.click();
    }

    public String getText(WebElement el){
        elementHighLight(el, "orange");
        return el.getText();
    }

    public void elementHighLight(WebElement element, String color){
        //keep the old style to change it back
        String originalStyle = element.getAttribute("style");
        String newStyle = "background-color:" + color + ";" + originalStyle;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Change the style
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
                element);
        // Change the style back after few miliseconds
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
                + originalStyle + "');},400);", element);
    }

    // others
    public boolean isPageDisplayed(WebElement el, String name){
        return getText(el).equals(name);
    }

    public void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement getPageHeading() {
        return pageHeading;
    }
}
