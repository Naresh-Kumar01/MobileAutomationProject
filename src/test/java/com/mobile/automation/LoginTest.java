package com.mobile.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import java.net.URL;
import java.time.Duration;

public class LoginTest {
    // Global driver
    public AndroidDriver driver;

    @BeforeMethod
    public void setup() throws Exception {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        dc.setCapability("appium:automationName", "UiAutomator2");
        dc.setCapability("appium:app", "C:\\Users\\Admin\\Downloads\\matrixV2-production-debug_branch_3.4.2_may_28.apk");
        
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), dc);
    }

    @Test
    public void testLoginFlow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1. Enter IP
        driver.findElement(AppiumBy.id("com.logixgrid.app.matrixv2.production:id/edit_server_code")).sendKeys("192.168.1.50");

        // 2. Enter Org Code
        driver.findElement(AppiumBy.id("com.logixgrid.app.matrixv2.production:id/edit_register_organization_code")).sendKeys("ORG123");

        // 3. Click Submit
        driver.findElement(AppiumBy.id("com.logixgrid.app.matrixv2.production:id/button_register_organization")).click();

        // 4. Assertion
        try {
            WebElement dashboardTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.id("com.logixgrid.app.matrixv2.production:id/dashboard_title")));
            
            Assert.assertTrue(dashboardTitle.isDisplayed(), "Dashboard screen nahi dikhi!");
            System.out.println("Login Success: Test Passed!");
            
        } catch (Exception e) {
            Assert.fail("Login failed: Dashboard element nahi mila. Error: " + e.getMessage());
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}