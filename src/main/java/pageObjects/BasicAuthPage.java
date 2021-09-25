package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class BasicAuthPage extends BasePage {
    public BasicAuthPage(WebDriver driver) {
        super(driver);
    }

    public void enterCredentials(String user, String pswd){
//        allertSendText(user);
//        sleep(500);
//        allertSendText(pswd);
        js.executeScript("window.confirm");
        Alert confirmation = driver.switchTo().alert();
        System.out.println(confirmation.getText());
    }
}
