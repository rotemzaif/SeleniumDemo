package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

/**
 * JQueryUiMenuPage class derives from BasePage with all its variables, objects and methods.
 * The Class includes menus webelement lists and a method which allows to select a menu and click on it.
 * */
public class JQueryUiMenuPage extends BasePage {

    // page elements
    @FindBy(css = "#menu > li")
    List<WebElement> mainMenuList;
    @FindBy(css = "#menu > li:nth-child(2) > ul > li")
    List<WebElement> enabledMenuList;
    @FindBy(css = "#menu > li:nth-child(2) > ul > li:nth-child(1) > ul > li")
    List<WebElement> downloadMenuList;




    public JQueryUiMenuPage(WebDriver driver) {
        super(driver);
        pageHeading = driver.findElement(By.cssSelector(".example > h3"));
    }

    // page getters
    public List<WebElement> getMainMenuList() {
        return mainMenuList;
    }

    public List<WebElement> getEnabledMenuList() {
        return enabledMenuList;
    }

    public List<WebElement> getDownloadMenuList() {
        return downloadMenuList;
    }

    // page actions methods
    public void selectMenu(List<WebElement> menuList, String menuName){
        for (WebElement menu : menuList) {
            if(getText(menu).equals(menuName)){
                click(menu);
                break;
            }
        }
    }
}
