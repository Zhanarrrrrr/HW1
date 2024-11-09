import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.Assert.assertNotNull;


public class SampleTest {

    private AndroidDriver driver;


    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "nexus");
        desiredCapabilities.setCapability("appium:appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appium:appActivity", ".main.MainActivity");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }


    @Test
    public void fillAndSearch() {
        waitForElementAndClick(
                By.id("fragment_onboarding_skip_button"),
                "Cannot skip the disclaimer page"
        );

        waitForElementAndClick(
                By.xpath(
                        "//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']"),
                "Cannot find label of search"
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "music",
                "No result for search 'music'",
                15
        );

//        waitForElementPresent(
//                By.xpath(
//                        "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Musical theatre']"),
//                "Article about 'Musical theatre' is not found",
//                15
//        );
        waitForElementAndClick(
                By.xpath(
                        "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Musical theatre']"),
                "Cannot be clicked to this article"
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot be saved"
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot be added to folder"
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Fav",
                "cannot rename the folder",
                10
        );
        waitForElementAndClick(
                By.id("android:id/button1"),
                "Rename is not saved"
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot go back to the article list"
        );
        waitForElementAndClick(
                By.xpath(
                        "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='MusicBrainz']"),
                "Cannot be clicked to this article"
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot be saved"
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot be added to folder"
        );
        waitForElementAndClick(
                By.xpath(

                        "//*[@text='Fav']"),
                "The second article cannot be added to Fav"
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot go back to the article list"
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot go back to the main menu"
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot enter to the list of Saved"
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/negativeButton"),
                "Cannot enter to the list of Saved"
        );
        waitForElementAndClick(
                By.xpath(

                        "//*[@text='Fav']"),
                "The second article cannot be added to Fav"
        );
        swipeElemenToLeft(
                By.xpath(
                        "//*[@text='Musical theatre']"),
                "Cannot be deleted"
        );
        waitForElementPresent(By.xpath(
                        "//*[@text='MusicBrainz']"),
                "The article is not in folder", 10);


        waitForElementAndClick(By.xpath(
                        "//*[@text='MusicBrainz']"),
                "cannot be clicked");

        clickToCheckTitle(By.xpath(
                        "//*[@text='MusicBrainz']"),
                "The article is not in folder");
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private void clickToCheckTitle(By by, String error_message) {
        WebElement element = driver.findElement(by);
        assertNotNull("Element not found: " + by, element);

    }

    private void waitForElementAndClick(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 70);
        element.click();
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    protected void swipeElemenToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;


        TouchAction action = new TouchAction(driver);

//        TouchAction action = new TouchAction(driver);
        TouchAction perform = action.press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();


    }


    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}