package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
/**
 * FramesPage class derives from BasePage class and consists of a list of link elements.
 * The class also consists of a 'go to page' method which clicks on a given link from the page
 * */
public class FramesPage extends BasePage {
    // page elements
    @FindBy(css = "ul > li > a")
    List<WebElement> pageLinks;

    public FramesPage(WebDriver driver) {
        super(driver);
        pageHeading = driver.findElement(By.cssSelector(".example > h3"));
    }

    // getters
    public List<WebElement> getPageLinks() {
        return pageLinks;
    }

    // page actions methods
    public void goToPage(String name){
        for (WebElement link : pageLinks) {
            if(getText(link).equals(name)){
                click(link);
                break;
            }
        }
    }
}
