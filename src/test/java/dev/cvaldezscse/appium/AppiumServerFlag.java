package dev.cvaldezscse.appium;

import io.appium.java_client.service.local.flags.ServerArgument;

/**
 * AppiumServerFlag
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
public enum AppiumServerFlag implements ServerArgument {
    UDID("--udid"),
    DEFAULT_CAPABILITY("--default-capabilities");

    private final String arg;

    private AppiumServerFlag(String arg) {
        this.arg = arg;
    }

    public String getArgument() {
        return this.arg;
    }
}
