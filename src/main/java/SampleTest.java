import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

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
    public void sampleTest() {
        System.out.println("hello friends! It is my first test.");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}