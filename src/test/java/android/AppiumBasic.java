package android;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;
import org.testng.Assert;

public class AppiumBasic extends BaseTest {

    @Test
    public void appiumTest() {
        System.out.println("Appium Test Running");
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle, "WiFi settings");
        System.out.println(alertTitle);
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("HelloAbhinav");
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();  // ✅ valid


    }
}
