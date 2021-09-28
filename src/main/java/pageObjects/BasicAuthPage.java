package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Utils;

/**
 * BasicAuthPage class derives from BasicPage class with all its variables, objects and methods.
 * The class includes and 2 label elements and methods that support getting text and validating elements existance
 * and display
 */
public class BasicAuthPage extends BasePage {
    // page elements
    WebElement confirmationLabel;
    WebElement authorizationLabel;

    public BasicAuthPage(WebDriver driver, String loginType) {
        super(driver);
        switch (loginType){
            case "success":
                pageHeading = driver.findElement(By.cssSelector(".example > h3"));
                confirmationLabel = driver.findElement(By.cssSelector(".example > p"));
                break;
            case "cancel":
                authorizationLabel = driver.findElement(By.cssSelector("body"));
                break;
        }
    }

    // page getters
    public String getconfirmationLabelText(){
        return getText(confirmationLabel);
    }

    public String getAuthorizationText(){
        return getText(authorizationLabel);
    }

    // page actions
    public void goToWelcomePage(){
        driver.get(Utils.readProperty("homePageURL"));
    }

    // validation methods
    public boolean isConfrimationLabelExist(){
        if(driver.findElements(By.cssSelector(".example > p")).isEmpty())
            return false;
        else return true;
    }
    public boolean isAuthorizationLabelDisplayed(){
        if(driver.findElement(By.cssSelector("body")).getText().isEmpty())
            return false;
        else return true;
    }

}
