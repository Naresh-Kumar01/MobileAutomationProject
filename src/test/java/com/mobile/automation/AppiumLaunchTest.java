package com.mobile.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppiumLaunchTest {

    AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        System.out.println("🚀 Appium Server se connect ho raha hai (Target: API Demos)...");

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("emulator-5554");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity("io.appium.android.apis.ApiDemos");
        options.setAutoGrantPermissions(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
    }

    @Test
    public void verifyApiDemosHomeLayout() {
        System.out.println("🥳 Success! API Demos app automation se live open ho gayi hai!");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
        // 🔥 STAGE 1: Handle "Continue" Alert Popup
        try {
            System.out.println("🔍 Checking for 'Continue' compatibility popup...");
            WebElement continueBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Continue\")")
                )
            );
            continueBtn.click();
            System.out.println("✅ 'Continue' button clicked!");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("ℹ️ No 'Continue' popup visible.");
        }

        // 🔥 STAGE 2: Handle Subsequent "OK" Warning Popup
        try {
            System.out.println("🔍 Checking for secondary 'OK' dialog block...");
            WebElement okBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"OK\")")
                )
            );
            okBtn.click();
            System.out.println("✅ Secondary 'OK' button cleared!");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("ℹ️ No secondary 'OK' popup visible.");
        }

        // 🔥 STAGE 3: Clear Stale Cache & Refresh Layout Tree
        try {
            driver.getPageSource();
            System.out.println("🔄 Layout tree refreshed completely.");
        } catch (Exception e) {
            // Ignore cache error
        }

        // 🔥 STAGE 4: Click 'Views' with Fail-Safe Fallbacks
        System.out.println("🔄 Locating 'Views' element...");
        WebElement viewsElement = null;
        
        try {
            // Fallback 1: Direct native text strategy
            viewsElement = wait.until(
                ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Views\")")
                )
            );
        } catch (Exception e1) {
            System.out.println("⚠️ Direct strategy failed. Triggering UiScrollable automation engine scan...");
            try {
                // Fallback 2: Deep layout scroll-to-view search mapping (Ye device ko refresh hone par majboor karega)
                viewsElement = driver.findElement(
                    AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Views\"))"
                    )
                );
            } catch (Exception e2) {
                System.out.println("⚠️ Scroll search failed. Attempting classic text-based XPath as last resort...");
                // Fallback 3: Standard Native text XPath
                viewsElement = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Views']"))
                );
            }
        }
        
        Assert.assertNotNull(viewsElement, "❌ Failure: All fail-safe element strategies exhausted.");
        viewsElement.click();
        System.out.println("🎉 SUCCESS: Master strategies matched! 'Views' directory is now open!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🛑 Driver session closed successfully.");
        }
    }
}