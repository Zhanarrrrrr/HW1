package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertNotNull;

public class MainPageObject {
    protected AppiumDriver driver;
    public MainPageObject(AppiumDriver driver){
        this.driver=driver;
    }
    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public void clickToCheckTitle(By by, String error_message) {
        WebElement element = driver.findElement(by);
        assertNotNull("Element not found: " + by, element);

    }

    public void waitForElementAndClick(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 70);
        element.click();
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public void swipeElemenToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;


        TouchAction action = new TouchAction((PerformsTouchActions) driver);

//        TouchAction action = new TouchAction(driver);
        TouchAction perform = action.press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();


    }

}
