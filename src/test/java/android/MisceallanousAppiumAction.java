package android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.DeviceRotation;

public class MisceallanousAppiumAction extends BaseTest{


    @Test
    public void miscellaneousAppiumActionTest() {
        System.out.println("Appium Test Running");
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        //LANDSCAPE MODE
        DeviceRotation landscape = new DeviceRotation(0, 0, 90);
        driver.rotate(landscape);

        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle, "WiFi settings");
        System.out.println(alertTitle);

        //copy text to clipboard
        driver.setClipboardText("HelloAbhinav");

        //paste text from clipboard
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(driver.getClipboardText());

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));


       // driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("HelloAbhinav");
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();  // ✅ valid

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));


    }
}

