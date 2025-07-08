package ios;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class iOSBasic extends iOSBaseTest {

    @Test
    public void testiOSBasic() {
        // Tap on "Alert Views"
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();

        // Tap on "Text Entry"
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == 'Text Entry'`]")).click();

        // Enter text in the alert input field
        driver.findElement(AppiumBy.className("XCUIElementTypeTextField")).sendKeys("Hello Abhinav");

        // Tap OK to confirm
        driver.findElement(AppiumBy.accessibilityId("OK")).click();

        // Tap on "Confirm / Cancel"
        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value == 'Confirm / Cancel'")).click();

        // Wait and fetch alert text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
            AppiumBy.iOSNsPredicateString("name BEGINSWITH[c] 'A message'")
        ));
        System.out.println("Alert Text: " + alertMessage.getText());

        // Tap on "Confirm"
        driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Confirm'")).click();
        System.out.println("Alert Confirmed");
    }
}
