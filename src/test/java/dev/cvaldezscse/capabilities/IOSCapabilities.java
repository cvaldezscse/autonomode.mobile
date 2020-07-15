package dev.cvaldezscse.capabilities;

import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * IOSCapabilities
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
public class IOSCapabilities extends Capabilities{
    @Override
    public DesiredCapabilities caps() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone XS Max");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.1.3");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.irhythm.ZioMD.qa");
        capabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, "55CL7NXWST");
        capabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "iPhone Developer");

        return capabilities;
    }

    @Override
    public String getVersion() {
        return "not_implemented";
    }

    @Override
    public String getBuild() {
        return "not_implemented";
    }
}
