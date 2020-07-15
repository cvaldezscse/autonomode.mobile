package dev.cvaldezscse.reporting;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static java.lang.String.format;

/**
 * ReportingTestBase
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
@Listeners({TestSuiteListener.class, TestListener.class})
public abstract class ReportingTestBase {

    private TestReport report = TestSuiteListener.getTestReport();
    protected TestDetail testDetail;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestResult result) {
        String testName = format("%s.%s_%d",
                result.getMethod().getRealClass().getSimpleName(),
                result.getMethod().getMethodName(),
                result.getMethod().getCurrentInvocationCount());

        testDetail = new TestDetail(testName);
        report.addTestDetail(testDetail);
        System.out.println("Starting Test " + testDetail.getTestName());

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        testDetail.setResultAndColor(result);
    }

    public <T> void assertAndReport(String reason, T actual, Matcher<? super T> matcher) {

        Assertion assertion = new Assertion(reason);

        Description expectedDescription = new StringDescription()
                .appendText("\nExpected: ")
                .appendDescriptionOf(matcher);
        assertion.setExpected(expectedDescription.toString());

        Description actualDescription = new StringDescription()
                .appendText("\n     Found: ")
                .appendValue(actual);
        assertion.setActual(actualDescription.toString());

        // create test detail and add step, assertion
        String step = format("Verify %s" , reason);
        testDetail.addStep(step);
        System.out.println(step);
        testDetail.addAssertion(assertion);

        if (!matcher.matches(actual)) {
            matcher.describeMismatch(actual, expectedDescription.appendText("\n     Found: "));
            throw new AssertionError(expectedDescription.toString());
        }
    }

    protected void addTestDescription(String desc) {
        testDetail.setDescription(desc);
    }

    protected void addTestStep(String testStep) {
        testDetail.addStep(testStep);
    }

    protected void addTestLog(String log) {
        testDetail.addLog(log);
    }

}
