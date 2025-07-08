package ios;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;

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

        // Tap on "Confirm"
        driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Confirm'")).click();
        System.out.println("Alert Confirmed");
    }
}
