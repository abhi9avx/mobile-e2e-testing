package ios;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class iOSSliderTest extends iOSBaseTest {

    @Test
    public void setSliderToMax() {

        /* 1 ── Open the “Sliders” demo screen */
        driver.findElement(AppiumBy.accessibilityId("Sliders")).click();

        /* 2 ── Wait for the first slider to appear */
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        WebElement slider = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath("//XCUIElementTypeSlider")));

        /* 3 ── Move the slider to 100 % */
        // Apple’s slider takes a float 0–1.0  (1.0 = 100 %)
        slider.sendKeys("1");

        /* 4 ── Verify the value text reads “100 %” */
        String value = slider.getAttribute("value");   // e.g. “100 %”
        Assert.assertEquals(value, "100%", "Slider did not reach 100 %");
    }
}
