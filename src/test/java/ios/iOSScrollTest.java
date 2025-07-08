package ios;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class iOSScrollTest extends iOSBaseTest {

    @Test
    public void scrollAndPickColors() {

        // scroll down until "Web View" is visible
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("name", "Web View");
        ((JavascriptExecutor) driver).executeScript("mobile: scroll", params);

        driver.findElement(AppiumBy.accessibilityId("Web View")).click();

        // back to the catalog
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement backBtn = wait.until(
            ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//XCUIElementTypeButton[@name='UIKitCatalog']"))
        );
        backBtn.click();

        // open Picker View
        driver.findElement(AppiumBy.accessibilityId("Picker View")).click();

        // set RGB values
        driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("80");
        driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("220");
        driver.findElement(AppiumBy.accessibilityId("Blue color component value")).sendKeys("105");

        String blue = driver.findElement(
                AppiumBy.accessibilityId("Blue color component value")).getText();
        System.out.println("Blue component now: " + blue);
    }
}
