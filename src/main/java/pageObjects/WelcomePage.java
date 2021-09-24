package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

import java.util.List;
/**
 * WelcomePage class is the home page which consists of links to other pages.
 * It consists of a list of all links and a method which clicks on a given link
 * */
public class WelcomePage extends BasePage {
    // page elements
    @FindBy(css = "ul > li > a")
    private List<WebElement> linksList;

    public WelcomePage(WebDriver driver) {
        super(driver);
        pageHeading = driver.findElement(By.cssSelector("[class='heading']"));
    }

    public List<WebElement> getLinksList(){
        return linksList;
    }

    /**
     * A method which clicks on a link
     * @Param - A string link name
     * */
    public void goToPage(String name){
        for (WebElement link : linksList) {
            if(getText(link).equals(name)){
                click(link);
                break;
            }
        }
    }
}
