package ecommerce;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class ecommerce_tc_4_Hybrid extends BaseTest {

    @Test
    public void fillFormTest() throws InterruptedException {

        // Country selection
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\"India\"));"
        )).click();

        // Form input
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Abhinav");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        Thread.sleep(2000); // Wait for product screen

        // Add products to cart
        List<WebElement> addToCartButtons = driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']"));
        addToCartButtons.get(0).click();
        addToCartButtons.get(1).click();

        // Go to cart
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        // Wait for cart page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(
                AppiumBy.id("com.androidsample.generalstore:id/toolbar_title"),
                "text", "Cart"
        ));

        // Price validation
        List<WebElement> prices = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice"));
        double total = 0;
        for (WebElement price : prices) {
            total += Double.parseDouble(price.getText().substring(1));
        }

        String displayTotal = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double displayTotalDouble = Double.parseDouble(displayTotal.substring(1));

        Assert.assertEquals(total, displayTotalDouble);

        // Accept terms and proceed
        WebElement terms = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(terms);
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();

        // Wait for WebView to load
        Thread.sleep(5000);

        // Switch context to WebView
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            System.out.println("Available context: " + context);
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                break;
            }
        }

        // Wait for web search box
        WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webWait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));

        // Interact with WebView
        driver.findElement(By.name("q")).sendKeys("abhi9avx");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        // Return to native context
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }
}
