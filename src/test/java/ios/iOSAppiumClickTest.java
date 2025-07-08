package ios;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class iOSAppiumClickTest extends iOSBaseTest {

    @Test
    public void alertViewFlow() {

        /* ---------- 1. Open “Alert Views” ---------- */
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();

        /* ---------- 2. Tap “Text Entry” ---------- */
        driver.findElement(
                AppiumBy.xpath("//XCUIElementTypeStaticText[@name='Text Entry']"))
              .click();

        /* ---------- 3. Enter text & confirm ---------- */
        driver.findElement(
                AppiumBy.className("XCUIElementTypeTextField"))
              .sendKeys("Hello");

        driver.findElement(AppiumBy.accessibilityId("OK")).click();

        /* ---------- 4. Tap “Confirm / Cancel” ---------- */
        driver.findElement(
                AppiumBy.accessibilityId("Confirm / Cancel"))
              .click();

        /* ---------- 5. Read the alert message ---------- */
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//*[contains(@name,'message')]")));

        System.out.println("Alert message: " + msg.getText());

        /* ---------- 6. Pick “Confirm” ---------- */
        driver.findElement(AppiumBy.accessibilityId("Confirm")).click();
    }
}
