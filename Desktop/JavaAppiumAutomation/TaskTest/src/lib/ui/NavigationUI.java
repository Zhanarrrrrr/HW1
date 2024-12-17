package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.reporters.jq.Main;

public class NavigationUI extends MainPageObject {
    private static final String
    MY_LISTS_LINK ="//android.widget.ImageButton[@content-desc=\"Navigate up\"]";
    public NavigationUI(AppiumDriver driver){
        super(driver);
    }
    public void clickMyList(){
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_LINK),
                "Cannot go back to the article list"
        );
    }

}
