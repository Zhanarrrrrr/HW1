package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{
    private static final String
    SHOW_LIST = "org.wikipedia:id/nav_tab_reading_lists",
    NEGATIVE_BUTTON = "org.wikipedia:id/negativeButton",
CREATED_LIST = "//*[@text='Fav']";
    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }

    public void navigateToMyList(){
        this.waitForElementAndClick(
                By.id(SHOW_LIST),
                "Cannot enter to the list of Saved"
        );
        this.waitForElementAndClick(
                By.id(NEGATIVE_BUTTON),
                "Cannot enter to the list of Saved"
        );
    }

    public void displayList(){
        this.waitForElementAndClick(
                By.xpath(CREATED_LIST),
                "The second article cannot be added to Fav"
        );
    }
}
