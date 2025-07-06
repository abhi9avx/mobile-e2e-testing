package ecommerce;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class ecommerce_tc_2 extends BaseTest {

    @Test
    public void fillFormTest() throws InterruptedException {

        // Select country from dropdown
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\"India\"));"
        )).click();

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Abhinav");


        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        Thread.sleep(2000); 

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\"Jordan 6 Rings\"));"
        ));

        int productCount = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
        System.out.println("Product Count: " + productCount);

        for (int i = 0; i < productCount; i++) {
            String productName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (productName.contains("Jordan 6 Rings")) {   
                driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        Thread.sleep(2000);

        //wait for the product to be added to the cart

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));


        String productNameCheckout = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(productNameCheckout, "Jordan 6 Rings");


    }
}
