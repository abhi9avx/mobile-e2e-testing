package android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class MisceallanousAppiumAction extends BaseTest {

    @Test
    public void launchPreferenceDependenciesDirectly() {

        // ✅ Directly start activity EXACTLY as working ADB command
        Map<String, Object> params = new HashMap<>();
        params.put("component", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies");
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", params);

        // ✅ Enable WiFi checkbox
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        // ✅ Rotate to LANDSCAPE
        DeviceRotation landscape = new DeviceRotation(0, 0, 90);
        driver.rotate(landscape);

        // ✅ Open WiFi settings
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='WiFi settings']")).click();

        // ✅ Verify Alert Title
        String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle, "WiFi settings");
        System.out.println("Alert title verified: " + alertTitle);

        // ✅ Clipboard actions
        ((AndroidDriver) driver).setClipboardText("HelloAbhinav");
        driver.findElement(AppiumBy.id("android:id/edit"))
                .sendKeys(((AndroidDriver) driver).getClipboardText());

        // ✅ Press ENTER key
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

        // ✅ Click OK button
        driver.findElement(AppiumBy.id("android:id/button1")).click();

        // ✅ BACK and HOME
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
