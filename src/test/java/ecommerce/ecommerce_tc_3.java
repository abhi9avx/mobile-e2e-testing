package ecommerce;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;

public class ecommerce_tc_3 extends BaseTest {

    @Test
    public void fillFormTest() throws InterruptedException {

        // Select country from dropdown
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\"India\"));"
        )).click();

        // Fill name and select gender
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Abhinav");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        Thread.sleep(2000);

        // Add first two products to cart
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

        // Go to cart
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        // Wait for cart to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(
                driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),
                "text", "Cart"
        ));

        // Calculate total sum
        List<WebElement> productPrice = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
        int count = productPrice.size();
        double totalSum = 0;

        for (int i = 0; i < count; i++) {
            String amountString = productPrice.get(i).getText();
            double price = Double.parseDouble(amountString.substring(1));
            totalSum += price;
        }

        // Get displayed total and compare
        String displaySum = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double displaySumDouble = Double.parseDouble(displaySum.substring(1));
        Assert.assertEquals(totalSum, displaySumDouble);

        // Accept terms and proceed
        WebElement ele = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(ele);
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();

        Thread.sleep(2000);
    }
}
