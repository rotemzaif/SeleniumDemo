package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WelcomePage extends BasePage {
    // page elements
    @FindBy(css = "ul > li > a")
    private List<WebElement> linksList;

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getLinksList(){
        return linksList;
    }

    public void goToPage(String name){
        for (WebElement link : linksList) {
            if(getText(link).equals(name)){
                click(link);
                break;
            }
        }
    }
}
