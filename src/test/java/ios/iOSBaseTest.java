package ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class iOSBaseTest {

    protected IOSDriver driver;
    protected AppiumDriverLocalService service;

    @BeforeClass
    public void startAppiumService() {
        service = new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/opt/homebrew/bin/node"))
                .withAppiumJS(new File("/opt/homebrew/bin/appium"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();
    }

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 16 Pro");
        options.setPlatformName("iOS");
        options.setPlatformVersion("18.5");
        options.setApp("/Users/abhinav/Library/Developer/Xcode/DerivedData/UIKitCatalog-fslyncbznotkowdcsljlmhlkahnt/Build/Products/Debug-iphonesimulator/UIKitCatalog.app");
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));
        
        driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
