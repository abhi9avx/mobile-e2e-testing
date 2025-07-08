package ios;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class iOSSwipeTest extends iOSBaseTest {

    @Test
    public void swipeThroughPhotos() {

        /* 1 ── Launch Photos (com.apple.mobileslideshow) */
        driver.activateApp("com.apple.mobileslideshow");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        /* 2 ── Ensure we see *some* collection cells */
        List<WebElement> cells =
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        AppiumBy.xpath("//XCUIElementTypeCell")));

        if (cells.isEmpty()) {
            throw new SkipException("No images/albums in Photos app; nothing to swipe.");
        }

        /* 3 ── Tap the first cell (album OR photo) */
        cells.get(0).click();

        /*
         * Now we may already be inside a photo,
         * or we may be inside an album that still shows thumbnails.
         * Check again and dive in if needed.
         */
        List<WebElement> thumbnails =
                driver.findElements(AppiumBy.xpath("//XCUIElementTypeCell"));

        if (!thumbnails.isEmpty()) {
            thumbnails.get(0).click();
        }

        /* 4 ── Count how many photos exist in this context */
        int photoCount = driver.findElements(
                AppiumBy.xpath("//XCUIElementTypeOther[@name='Photo']//XCUIElementTypeImage"))
                .size();

        // Fallback: if that locator returns 0, fall back to the nav-bar index pattern.
        if (photoCount == 0) {
            String navTitle = driver.findElement(
                    AppiumBy.xpath("//XCUIElementTypeNavigationBar")).getAttribute("name");
            // "1 of 10"  → 10
            if (navTitle != null && navTitle.contains("of")) {
                try {
                    photoCount = Integer.parseInt(navTitle.split("of")[1].trim());
                } catch (Exception ignored) { /* leave as zero */ }
            }
        }

        Assert.assertTrue(photoCount > 0, "No photos found to swipe through.");

        /* 5 ── Swipe left through all photos */
        Map<String, Object> swipe = new HashMap<>();
        swipe.put("direction", "left");

        for (int i = 0; i < photoCount - 1; i++) {
            // Print current nav-bar title if present (e.g. “3 of 10”)
            String title = driver.findElement(
                    AppiumBy.xpath("//XCUIElementTypeNavigationBar"))
                    .getAttribute("name");
            System.out.println("Now viewing: " + title);

            ((JavascriptExecutor) driver).executeScript("mobile: swipe", swipe);
        }

        /* 6 ── Done: back out to Albums */
        driver.navigate().back(); // back to grid (thumbnails)
        driver.findElements(AppiumBy.accessibilityId("Albums"))
              .stream().findFirst().ifPresent(WebElement::click);
    }
}
