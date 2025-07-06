package android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LongPressTest extends BaseTest {

    @Test
    public void LongPressTest() {
        // Navigate to the view
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Expandable Lists']")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

        // Find the element to long press
        WebElement ele = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='People Names']"));

        // Perform long press action using method from BaseTest
        longPressAction(ele);

        // Validate context menu appears
        WebElement menuTitle = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Sample menu']"));
        String menuText = menuTitle.getText();
        System.out.println(menuText);

        // Assertion
        Assert.assertEquals(menuText, "Sample menu");
        Assert.assertTrue(menuTitle.isDisplayed());
    }
}
