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

        // 🌟 STEP 2: Pure Cache Purge & DOM Reset Hack
        try {
            System.out.println("⏳ Forcing Appium to clear stale UI cache...");
            Thread.sleep(3000);
            
            // 🔄 Yeh line Appium driver ka pura layout cache force-refresh kar degi!
            String dump = driver.getPageSource(); 
            System.out.println("🔄 DOM Layout Cache completely refreshed! (XML Size: " + dump.length() + " chars)");
        } catch (Exception e) {
            System.out.println("⚠️ Cache refresh failed, attempting selector anyway...");
        }

        // 🌟 STEP 3: Main Dashboard - Target Views
        System.out.println("🔄 Locating 'Views' element now...");
        
        WebElement viewsElement = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Views\")")
            )
        );
        
        Assert.assertNotNull(viewsElement, "Dashboard Error: 'Views' element blank mila.");
        viewsElement.click();
        System.out.println("🎉 SUCCESS: Cache tod kar 'Views' successfully click ho gaya!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🛑 Driver session closed successfully.");
        }
    }
}