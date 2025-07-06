package ecommerce;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;

public class ecommerce_tc_1 extends BaseTest {

    @Test
    public void fillFormTest() throws InterruptedException {

        // Select country from dropdown
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\"India\"));"
        )).click();

        // Attempt to submit without name to trigger toast
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        Thread.sleep(2000); // Wait for toast

        // Verify toast message
        String toastText = driver.findElement(AppiumBy.xpath("//android.widget.Toast[1]")).getAttribute("name");
        System.out.println("Toast Message: " + toastText);
        Assert.assertEquals(toastText, "Please enter your name");

        // Fill name and submit again
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Abhinav");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        Thread.sleep(2000); // Optional wait to observe the result
    }
}
