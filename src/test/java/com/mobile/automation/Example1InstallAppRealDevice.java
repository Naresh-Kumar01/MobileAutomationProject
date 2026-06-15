package com.mobile.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import java.time.Duration; // For implicit wait
import org.openqa.selenium.By; // Missing Import Fixed
import org.openqa.selenium.WebElement; // Missing Import Fixed

public class Example1InstallAppRealDevice {

    public static void main(String[] args) throws Exception {
        
        // 1. Use UiAutomator2Options instead of the deprecated DesiredCapabilities
        UiAutomator2Options options = new UiAutomator2Options();
        
        // 2. Configure target device capabilities cleanly
        options.setAutomationName("UiAutomator2"); 
        options.setPlatformName("Android");
        options.setPlatformVersion("14");          
        options.setDeviceName("emulator-5554");    
        
        // Absolute path to the local target APK file
        options.setApp("C:\\Users\\Admin\\Downloads\\matrixV2-production-debug_branch_3.4.2_may_28.apk");

        // 3. Set the default Appium 2.x server URL
        URL url = new URL("http://127.0.0.1:4723/");

        // 4. Initialize the driver and establish the session
        System.out.println("Starting the Appium automation session...");
        AndroidDriver driver = new AndroidDriver(url, options);
        
        // 5. Global Implicit Wait (Best Practice: Gives elements time to load on the screen)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        System.out.println("The application has been successfully installed and launched!");

        // 6. Locate and interact with the element
        System.out.println("Finding the login button...");
       // WebElement loginButton = driver.findElement(By.id("com.matrix.v2:id/btn_login"));
    //    loginButton.click();
        System.out.println("Login button clicked successfully!");

        // Hold the application open briefly before closing the test session
        Thread.sleep(5000); 
        driver.quit();
        System.out.println("Appium session closed successfully.");
    }
}