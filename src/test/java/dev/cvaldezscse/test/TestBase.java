package dev.cvaldezscse.test;

import dev.cvaldezscse.appium.Appium;
import dev.cvaldezscse.capabilities.Capabilities;
import dev.cvaldezscse.reporting.TestSuiteListener;
import dev.cvaldezscse.support.TestFlight;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.hamcrest.Matcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

import static java.lang.String.format;

/**
 * TestBase
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
public class TestBase ReportingTestBase{

    private static AppiumDriverLocalService service;
    protected static AppiumDriver<MobileElement> driver;

    protected static Capabilities capabilities = Capabilities.getCapabilities();
    protected static DesiredCapabilities caps = capabilities.caps();


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        Appium.start();

        // todo use factory

        TestSuiteListener.getTestReport().setBuild(capabilities.getBuild());
        TestSuiteListener.getTestReport().setVersion(capabilities.getVersion());
        TestSuiteListener.getTestReport().setName("ZioSuite Mobile App Automation");
        TestSuiteListener.getTestReport().setRepo("https://git.irhythmtech.org/projects/TA/repos/ziosuite-mobile-automation/browse");
        TestSuiteListener.getTestReport().setInfo("Automation for ZioSuite Mobile App");

        if (Capabilities.platform.equalsIgnoreCase("ios")) {
            TestFlight.installTestFlightApp();
        }

        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting Appium Driver:", e);
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        navigation = new ScreenNavigation(driver, testDetail);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.resetApp();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if (driver != null) {
            driver.quit();
        }
        Appium.stop();
    }


    /**
     * hamcrest's assertThat, adds assertion details and screeshot to report
     * @param reason
     * @param screenshot
     * @param actual
     * @param matcher
     * @param <T>
     */
    protected <T> void assertAndReportWithScreenshot(String reason, String screenshot, T actual, Matcher<? super T> matcher) {
        addScreenshotToThisStep(screenshot);
        super.assertAndReport(reason, actual, matcher);
    }

    // actually used for reporting
    protected void addScreenshotToThisStep(String screenshot) {
        testDetail.addScreenshot(saveAScreenShot(
                format("%s-%s", testDetail.getTestName(), screenshot)));
        System.out.println("saved screenshot " +format("%s-%s", testDetail.getTestName(), screenshot));
    }

    protected void addScreenshotToThisStep(String screenshot, boolean isRunnable) {
        testDetail.addScreenshot(saveAScreenShot(format("%s-%s", testDetail.getTestName(), screenshot)));
        System.out.println("saved screenshot " +format("%s-%s", testDetail.getTestName(), screenshot));
    }


    /**
     * saves a screenshot to target/screenshots/$date/$fileName
     * returns full path of fileName (presume for use in reporting)
     * private, not used by tests,
     * use {@link #addScreenshotToThisStep(String)} in tests
     * @param fileName
     * @return
     */
    private String saveAScreenShot(String fileName) {

        // in memory copy
        File srcFile = driver.getScreenshotAs(OutputType.FILE);

        // file that will be on disk
        File targetFile = new File(
                format("%s/screenshots", ReportEngine.REPORT_DIR), // dir
                format("%s.jpg", fileName));  // filename

        // write the file
        try {
            FileUtils.copyFile(srcFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to save " + targetFile.getName(), e);
        }
        return targetFile.getName();
    }

    public AppiumDriver<MobileElement> getDriver() {
        if (Capabilities.platform.equalsIgnoreCase("ios")) {
            return new IOSDriver<MobileElement>(Appium.getUrl(), caps);
        }
        else {
            return new AndroidDriver<MobileElement>(Appium.getUrl(), caps);

        }
    }
}
