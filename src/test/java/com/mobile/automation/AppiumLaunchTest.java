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
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // 🌟 STEP 1: Android 14 Older Version Warning Popup Bypass
        try {
            System.out.println("🔍 Checking if older version warning popup exists...");
            WebElement alertButton = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Continue\")")
                )
            );
            alertButton.click();
            System.out.println("✅ Popup Bypassed: Clicked on 'Continue' button successfully!");
        } catch (Exception e) {
            System.out.println("ℹ️ Older version popup nahi aaya, directly proceeding to main dashboard.");
        }

        // 🌟 STEP 2: UI Synchronization Buffering
        // Popup hatne ke baad DOM freeze todne ke liye explicitly 3-4 seconds ka hard sleep zaroori hai Jenkins pe
        try {
            System.out.println("⏳ Waiting for Android OS layout hierarchy to fully refresh...");
            Thread.sleep(4000); 
        } catch (InterruptedException ie) {
            // Ignore context intercept
        }

        // 🌟 STEP 3: Main Dashboard - Dynamic Text Identifier Matching
        System.out.println("🔄 Locating 'Views' element via native UI scroll scan...");
        
        // Is baar hum exact text attribute structure aur pure index mapping ko target karenge
        WebElement viewsElement = wait.until(
            ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Views\")")
            )
        );
        
        Assert.assertNotNull(viewsElement, "Dashboard Error: 'Views' element structure blank mila.");
        viewsElement.click();
        System.out.println("🎉 SUCCESS: Finally 'Views' open ho gaya aur test complete pass hai!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🛑 Driver session closed successfully.");
        }
    }
}