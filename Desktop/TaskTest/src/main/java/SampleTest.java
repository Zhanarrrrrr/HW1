import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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


    @Test
    public void Test() {
        waitForElementAndClick(
                "fragment_onboarding_skip_button",
                "Cannot skip the disclaimer page"
        );

        waitForElementAndClick(
                "//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']",
                "Cannot find label of search"
        );

        WebElement searchInput = waitForElementPresent(
                "org.wikipedia:id/search_src_text",
                "Cannot find input line"
        );

        String searchTitle = searchInput.getAttribute("text");

        Assert.assertEquals(
                "Field names are not equal",
                "Search Wikipedia",
                searchTitle
        );
    }


    private WebElement waitForElementPresent(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by)

        );
    }

    private WebElement waitForElementPresent(String xpath, String error_message) {
        return waitForElementPresent(xpath, error_message, 10);
    }


    private void waitForElementAndClick(String xpath, String error_message) {
        WebElement element = waitForElementPresent(xpath, error_message, 10);
        element.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}