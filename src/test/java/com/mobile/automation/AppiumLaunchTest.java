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
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        
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
            
            // Chota sa delay taaki alert hatne ke baad screen refresh ho sake
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("ℹ️ Older version popup nahi aaya, directly proceeding to main dashboard.");
        }

        // 🌟 STEP 2: Main Dashboard Par 'Views' par click karna
        System.out.println("🔄 Locating 'Views' element now...");
        WebElement viewsElement = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Views\")")
            )
        );
        
        Assert.assertNotNull(viewsElement, "Dashboard Error: 'Views' element match nahi ho paya.");
        viewsElement.click();
        System.out.println("🎉 SUCCESS: 'Views' folder successfully opened via Automation!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🛑 Driver session closed successfully.");
        }
    }
}