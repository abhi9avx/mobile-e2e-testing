package android;

import io.appium.java_client.AppiumBy;

import org.testng.annotations.Test;

public class ScrollDemo extends BaseTest {

    @Test
    public void ScrollDemoTest() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        // Scroll until the element with text "WebView" is in view
        // driver.findElement(AppiumBy.androidUIAutomator(
        //         "new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"
        // ));

       
        // Perform scroll gesture using Appium 2 gesture API
        // boolean canScrollMore = (boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
        //         "left", 100,
        //         "top", 100,
        //         "width", 800,
        //         "height", 1200,
        //         "direction", "down",
        //         "percent", 3.0
        // ));

        scrollToEndAction();
        System.out.println("Reached the end of scrollable view.");

        

        // Thread.sleep(2000); // optional pause to observe
    }
}
