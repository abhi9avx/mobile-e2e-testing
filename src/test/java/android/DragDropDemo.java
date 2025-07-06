package android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

public class DragDropDemo extends BaseTest {

    @Test
    public void DragDropDemoTest() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        WebElement source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));

        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
            "elementId", ((RemoteWebElement) source).getId(),
            "endX", 625,
            "endY", 555,
            "speed", 1000  // Optional: pixels per second
        ));

        Thread.sleep(2000);
        driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).getText().equals("Dropped!");
    }
}
