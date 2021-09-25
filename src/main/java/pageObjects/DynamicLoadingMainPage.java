package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
/**
 * Dynamic Loading Main page Class derives from Base page with all its variables, objects and methods.
 * The class includes a Link WebElement list and a method that clicks on a link.
 * */
public class DynamicLoadingMainPage extends BasePage {
    // page elements
    @FindBy(css = ".example > a")
    List<WebElement> linkList;
    public DynamicLoadingMainPage(WebDriver driver) {
        super(driver);
        pageHeading = driver.findElement(By.cssSelector(".example > h3"));
    }

    // page getters
    public List<WebElement> getLinkList() {
        return linkList;
    }

    // page actions methods
    public void goToPage(String linkName){
        for (WebElement link : linkList) {
            if(getText(link).equals(linkName)){
                click(link);
                break;
            }
        }
    }
}
