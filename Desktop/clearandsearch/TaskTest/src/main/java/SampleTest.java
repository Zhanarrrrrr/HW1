import io.appium.java_client.android.AndroidDriver;
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


//    @Test
//    public void Test() {
//        waitForElementAndClick(
//               By.id( "fragment_onboarding_skip_button"),
//                "Cannot skip the disclaimer page"
//        );
//
//        waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']"),
//                "Cannot find label of search"
//        );
//
//        WebElement searchInput = waitForElementPresent(
//               By.id( "org.wikipedia:id/search_src_text"),
//                "Cannot find input line"
//        );
//
//        String searchTitle = searchInput.getAttribute("text");
//
//        Assert.assertEquals(
//                "Field names are not equal",
//                "Search Wikipedia",
//                searchTitle
//        );
//    }
//
//
//    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
//        wait.withMessage(error_message + "\n");
////        By by = By.xpath(by);
//        return wait.until(ExpectedConditions.presenceOfElementLocated(by)
//
//        );
//    }
//
//    private WebElement waitForElementPresent(By by, String error_message) {
//        return waitForElementPresent(by, error_message, 10);
//    }
//
//
//    private void waitForElementAndClick(By by, String error_message) {
//        WebElement element = waitForElementPresent(by, error_message, 10);
//        element.click();
//    }
//
//    @After
//    public void tearDown() {
//        driver.quit();
//    }
//}


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

        waitForElementPresent(
                By.xpath(
                        "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Musical theatre']"),
                "Article about 'Musical theatre' is not found",
                15
        );
    }

    @Test
    public void clearSearchAndCheck() {
        clearSearch(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot clear search line",
                10
        );
        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Musical theatre']"),
                "Results are still on page",
                10);
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by)
        );
    }


    private void waitForElementAndClick(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);
        element.click();
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement clearSearch(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private boolean waitForElementNotPresent(By xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(xpath)
        );
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}