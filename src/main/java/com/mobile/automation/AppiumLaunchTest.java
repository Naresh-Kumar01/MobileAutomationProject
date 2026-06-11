package com.mobile.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumLaunchTest {
    public static void main(String[] args) {

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("VC498PZT7LTGOJKJ")
                .setAppPackage("com.android.settings")
                .setAppActivity("com.android.settings.Settings");
        options.setCapability("appium:androidInstallTimeout", 180000);
        options.setCapability("appium:uiautomator2ServerInstallTimeout", 180000);
        options.setCapability("appium:adbExecTimeout", 180000);
        options.setCapability("appium:noReset", true);
        

        AndroidDriver driver = null;

        try {
            System.out.println("🚀 Appium Server se connect ho raha hai (Stable Version)...");
            URL serverUrl = new URL("http://127.0.0.1:4723/");

            driver = new AndroidDriver(serverUrl, options);
            System.out.println("🥳 Success! Realme phone me Settings app successfully khul gayi hai!");
            
            Thread.sleep(4000); // 4 seconds tak open rakhein

        } catch (MalformedURLException e) {
            System.out.println("❌ URL galat hai: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Test execution me error aaya: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                try {
                    driver.quit();
                    System.out.println("🛑 Driver session closed.");
                } catch (Exception e) {
                    System.out.println("🛑 Driver close karte waqt issue aaya: " + e.getMessage());
                }
            }
        }
    }
}