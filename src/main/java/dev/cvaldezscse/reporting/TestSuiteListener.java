package dev.cvaldezscse.reporting;

import org.testng.ISuite;
import org.testng.ISuiteListener;

/**
 * TestSuiteListener
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
public class TestSuiteListener implements ISuiteListener {

    private static ReportEngine reportEngine = new ReportEngine("report_template.ftlh","TEST_REPORT.html");
    private static TestReport testReport = new TestReport("sample","git","this is a sample test");

    @Override
    public void onStart(ISuite suite) {
        System.out.println("TestSuite Started");
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("TestSuite Finished");
        reportEngine.generateReport(testReport);

    }

    public static TestReport getTestReport() {
        return testReport;
    }
}
