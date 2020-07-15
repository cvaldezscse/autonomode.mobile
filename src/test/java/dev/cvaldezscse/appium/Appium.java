package dev.cvaldezscse.appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.net.URL;

import static java.lang.String.format;

/**
 * Appium
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
public class Appium {

    private static AppiumDriverLocalService appium;

    /**
     * <p>path to node executable</p>
     * <p>, on most macs this will be /usr/local/bin/node, which is the default value</p>
     */
    private static String nodeHomeDir = System.getProperty("node.home","/usr/local/bin/node");

    /**
     * <p>path to appium</p>
     * <p>default is "/usr/local/lib/node_modules/appium" when installed via "npm install -g appium"</p>
     */
    private static String appiumHomeDir = System.getProperty("appium.home", "/usr/local/lib/node_modules/appium");

    /**
     * default is warn (quiet)
     * <p>available options are debug, info, warn, error</p>
     * <p> see http://appium.io/docs/en/writing-running-appium/server-args/ for more info</p>
     */
    private static String appiumLogLevel = System.getProperty("appium.logLevel", "warn");


    /**
     * ios only
     */
    private static String udid = System.getProperty("udid");

    /**
     * <p>starts appium service</p>
     */
    public static void start() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .usingDriverExecutable(new File(nodeHomeDir))
                .withAppiumJS(new File( format("%s%s", appiumHomeDir, "/build/lib/main.js")))
                .withArgument(GeneralServerFlag.LOG_LEVEL, appiumLogLevel)
                .usingAnyFreePort();
        if (udid != null && udid.length() > 1) {
            builder = builder.withArgument(AppiumServerFlag.DEFAULT_CAPABILITY, format("{\"appium:udid\":\"%s\"}", udid));
        }

        appium = builder.build();
        appium.start();
    }

    /**
     * <p>stops appium service if running</p>
     */
    public static void stop() {
        if (appium != null && appium.isRunning()) {
            appium.stop();
        }
    }


    /**
     * <p>appium url, used when created driver</p>
     * @return
     */
    public static URL getUrl() {
        return appium.getUrl();
    }
}
