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
        
        // Explicit Wait config
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // 🌟 ULTIMATE FIX: Android native text property ko catch karne ke liye pure XPath filter use kiya hai
        WebElement viewsElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                org.openqa.selenium.By.xpath("//android.widget.TextView[@text='Views']")
            )
        );
        
        // Verification Assertions
        Assert.assertNotNull(viewsElement, "Dashboard load nahi hua, Views element missing hai!");
        Assert.assertTrue(viewsElement.isDisplayed(), "Views element screen par visible nahi hai!");
        
        System.out.println("✅ Verification Successful: XPath se 'Views' element perfectly dhoondh liya gaya!");
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("🛑 Driver session closed successfully.");
        }
    }
}