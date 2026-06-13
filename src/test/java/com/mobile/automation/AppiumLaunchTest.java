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
        
        // Android 14 background freeze bypass karne ke liye permissions aur setup clean rakein
        options.setAutoGrantPermissions(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
    }

    @Test
    public void verifyApiDemosHomeLayout() {
        System.out.println("🥳 Success! API Demos app automation se live open ho gayi hai!");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        // 🌟 ULTIMATE FIX: Android Framework ka sabse hardcore text pattern scanner fallback mapping
        WebElement viewsElement = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.TextView\").textContains(\"Views\")")
            )
        );
        
        Assert.assertNotNull(viewsElement, "Dashboard fail: Element context engine ne release nahi kiya.");
        System.out.println("✅ Verification Successful: 'Views' text successfully mapped on Android 14!");
        
        // Click karke verify karte hain ki script smooth aage chal rahi hai ya nahi
        viewsElement.click();
        System.out.println("👆 Action Performed: 'Views' folder open ho gaya!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🛑 Driver session closed successfully.");
        }
    }
}