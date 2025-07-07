package ecommerce;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.AndroidKey;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;

public class ecommerce_tc_4_Hybrid extends BaseTest {

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

        Thread.sleep(2000); // Wait for product screen to load

        // Add first two products to cart
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

        // Go to cart
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        // Wait for cart page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(
                AppiumBy.id("com.androidsample.generalstore:id/toolbar_title"),
                "text", "Cart"
        ));

        // Calculate total sum of products
        List<WebElement> productPrice = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
        double totalSum = 0;
        for (WebElement priceElement : productPrice) {
            double price = Double.parseDouble(priceElement.getText().substring(1));
            totalSum += price;
        }

        // Compare with displayed total
        String displaySum = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double displaySumDouble = Double.parseDouble(displaySum.substring(1));
        Assert.assertEquals(totalSum, displaySumDouble);

        // Accept terms and proceed
        WebElement ele = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(ele);
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();

        // Wait for WebView to load
        Thread.sleep(5000);

        // Print and switch to web context
        Set<String> contexts = driver.getContextHandles();
        for (String contextName : contexts) {
            System.out.println("Available context: " + contextName);
        }

        // Switch to WebView
        for (String context : contexts) {
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                break;
            }
        }

        // Wait for WebView to render search box
        WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webWait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));

        // Interact with web content
        driver.findElement(By.name("q")).sendKeys("abhi9avx");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        // Go back to native context
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }
}
