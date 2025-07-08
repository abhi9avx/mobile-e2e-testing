package ecommerce;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.*;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected AndroidDriver driver;
    protected AppiumDriverLocalService service;

    @BeforeClass
    public void startAppiumService() {
        service = new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/opt/homebrew/bin/node"))        // Path to Node
                .withAppiumJS(new File("/opt/homebrew/bin/appium"))              // Path to Appium
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel_6");  // Your emulator name

        // Path to APK
        String appPath = System.getProperty("user.dir") + "/src/test/java/resources/General-Store.apk";
        options.setApp(appPath);

        // ✅ Path to the specific ChromeDriver binary (no sudo or brew needed)
        options.setChromedriverExecutable("/Users/abhinav/Downloads/chromedriver-mac-arm64/chromedriver");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void longPressAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "duration", 2000
        ));
    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left", 100,
                "top", 100,
                "width", 800,
                "height", 1200,
                "direction", "down",
                "percent", 3.0
            ));
        } while (canScrollMore);
    }

    public void swipeAction(WebElement element, String direction, double percent) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", percent
        ));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void stopAppiumService() {
        if (service != null) {
            service.stop();
        }
    }
}
