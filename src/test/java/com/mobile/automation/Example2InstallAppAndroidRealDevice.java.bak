package com.mobile.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import java.time.Duration;



public class Example2InstallAppAndroidRealDevice {

    public static void main(String[] args) {
        // Driver reference declaration
        AndroidDriver driver = null;

        try {
            // 1. Initialize modern Appium 2.x options object instead of old DesiredCapabilities
            UiAutomator2Options options = new UiAutomator2Options();
            
            // 2. Define standard cross-platform capabilities
            options.setAutomationName("UiAutomator2"); 
            options.setPlatformName("Android");
            
            // 3. Define target physical hardware identification string
            options.setDeviceName("VC498PZT7LTGOJKJ"); 
            
            // 4. Configure local source repository path for target binary setup
            options.setApp("C:\\Users\\Admin\\Downloads\\matrixV2-production-debug_branch_3.4.2_may_28.apk");

            // 5. Establish connection to standard local Appium 2.x server endpoint
            URL serverUrl = new URL("http://127.0.0.1:4723/");

            // 6. Instantiating driver context instance execution
            System.out.println("Starting the Appium automation session on the target device...");
            driver = new AndroidDriver(serverUrl, options);
            
            // 7. Configure global synchronization threshold loop rules
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            System.out.println("Application package deployed and initialized successfully.");

            // ==========================================
            // TEST SCRIPTS STAGE / INTERACTIONS SECTION
            // ==========================================
            System.out.println("Beginning test script execution...");

            // Target generic component node assignment wrapper loop
            // Example layout targeting abstract UI container structures:
            // WebElement targetElement = driver.findElement(By.id("btn_login"));
            // targetElement.click();

            // Intermediary script checkpoint hold parameter window
            Thread.sleep(5000);

        } catch (Exception exception) {
            // Catching environment execution lifecycle failures
            System.err.println("An unexpected structural or driver processing failure occurred during execution:");
            exception.printStackTrace();
            
        } finally {
            // 8. Safely tear down and release target driver threads out of workspace memory
            if (driver != null) {
                driver.quit();
                System.out.println("Appium engine automated session connection closed cleanly.");
            }
        }
    }
}