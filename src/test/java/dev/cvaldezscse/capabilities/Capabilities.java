package dev.cvaldezscse.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static java.lang.String.format;

/**
 * Capabilities
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
public abstract class Capabilities {

    public static String platform = System.getProperty("app.platform", "android");
    public static File app = new File(System.getProperty("app.file", "no_file"));

    public abstract DesiredCapabilities caps();

    public abstract String getVersion();

    public abstract  String getBuild();

    public static Capabilities getCapabilities() {
        if (platform.equalsIgnoreCase("android") && app == null) {
            throw new RuntimeException("Missing App, please use -Dapp.file=path/to/apk_or_ipa");
        }

        if ( platform.equalsIgnoreCase("android") && !app.exists()) {
            throw new RuntimeException(format("%s does not exists", app.getAbsoluteFile()));
        }

        if (platform.equalsIgnoreCase("android")) {
            return new AndroidCapabilities();
        }
        else if (platform.equalsIgnoreCase("ios")) {
            return new IOSCapabilities();
        }
        else {
            throw new RuntimeException("Unrecognized platform: use -Dapp.platform=[ios,android]");
        }
    }
}
