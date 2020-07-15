package dev.cvaldezscse.support;

import dev.cvaldezscse.appium.Appium;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * TestFlight
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
public class TestFlight {
    public static void installTestFlightApp() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone XS Max");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.1.3");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.apple.TestFlight");
        capabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, "55CL7NXWST");
        capabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "iPhone Developer");

        AppiumDriver driver = new AppiumDriver(Appium.getUrl(), capabilities);

        if (driver.isAppInstalled("YOUR_APP_BUNDLEID"))  {
            driver.removeApp("YOUR_APP_BUNDLEID");
        }

        driver.launchApp();
        driver.findElement(MobileBy.iOSNsPredicateString("name = 'INSTALL'")).click();

        final int TIMEOUT = 60; // seconds
        for (int i = 0; i < TIMEOUT; i++) {
            if (driver.isAppInstalled("YOUR_APP_BUNDLEID"))  {
                break;
            }
            try { Thread.currentThread().sleep(1000); } catch (Exception e) {}
        }
        driver.quit();
    }
}
