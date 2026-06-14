package com.mobile.automation;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class MobileAutomationPipelineTest {
	
	private AndroidDriver driver;

    @BeforeMethod
	@BeforeClass
    public void setUp() throws MalformedURLException {
        // 1. Inspector waali saari Desired Capabilities yahan set karenge
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("Pixel 8");
        
        // Aapki APK ka path (Jo Inspector mein use kiya tha)
        options.setApp("C:\\Users\\Admin\\Desktop\\app-debug.apk");

        // Baar-baar fresh install ka jhanjhat khatam karne ke liye
        options.setCapability("appium:noReset", true);
        options.setCapability("appium:fullReset", false);

        // 2. Local Appium Server (Port 4723) se connect karna
        URL serverUrl = new URL("http://127.0.0.1:4723/");
        driver = new AndroidDriver(serverUrl, options);

        // Global implicit wait lagana taaki element load hone ka time mile
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void verifyHelloWorldText() {
        System.out.println("--- Automation Test Flow Shuru Ho Raha Hai ---");

        // 3. Jo XPath humne Appium Inspector se nikala tha, use yahan paste kiya
        String xpathLocator = "//android.widget.TextView[@text='Hello World!']";
        WebElement helloWorldText = driver.findElement(AppiumBy.xpath(xpathLocator));

        // 4. Element se text nikal kar print karna
        String actualText = helloWorldText.getText();
        System.out.println("Emulator screen se mila text: " + actualText);

        // 5. TestNG Assertion - Check karna ki text 'Hello World!' hi hai na
     // Format: Assert.assertEquals(actual, expected, message);
        Assert.assertEquals(actualText, "Hello World!", "Bhai, text match nahi hua!");
        System.out.println("--- Automation Test Pass Ho Gaya! ---");
    }

    @AfterMethod
	@AfterClass
    public void tearDown() {
        // Test khatam hone ke baad session ko safely close karna
        if (driver != null) {
            driver.quit();
            System.out.println("Appium Session safely closed.");

}
    }
}
