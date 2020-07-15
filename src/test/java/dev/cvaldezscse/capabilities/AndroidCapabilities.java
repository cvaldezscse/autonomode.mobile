package dev.cvaldezscse.capabilities;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;

/**
 * AndroidCapabilities
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
public class AndroidCapabilities extends Capabilities {
    private String avd = System.getProperty("avd");

    @Override
    public DesiredCapabilities caps() {
        DesiredCapabilities caps = new DesiredCapabilities().android();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME ,ANDROID_UIAUTOMATOR2);
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 80000);

        if (avd != null) {
            caps.setCapability(AndroidMobileCapabilityType.AVD, "Pixel_2_API_29");
        }
        return caps;
    }

    /**
     * brittle string parsing
     */
    @Override
    public String getVersion() {
        return app.getName().split("-")[1];
    }

    /**
     * brittle string parsing
     */
    @Override
    public String getBuild() {
        return app.getName().split("-")[3].split("\\.")[0];
    }
}
