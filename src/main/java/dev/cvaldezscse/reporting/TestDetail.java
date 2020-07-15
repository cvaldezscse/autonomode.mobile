package dev.cvaldezscse.reporting;

import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TestDetail
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
public class TestDetail implements Comparable<TestDetail>{

    private String testName = "";
    private List<String> parameters;
    private String description = "";
    private String result = "";
    private String resultColor = "";
    private List<Assertion> assertions;
    private List<String> steps;
    private List<String> logs;
    private List<String> screenshots;

    public TestDetail(String testName) {

        this.testName = testName;
        parameters = new ArrayList<>();
        assertions = new ArrayList<>();
        steps = new ArrayList<>();
        logs = new ArrayList<>();
        screenshots = new ArrayList<>();
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultColor() {
        return resultColor;
    }

    public void setResultColor(String resultColor) {
        this.resultColor = resultColor;
    }

    public List<Assertion> getAssertions() {
        return assertions;
    }

    public List<String> getSteps() {
        return steps;
    }

    public List<String> getLogs() {
        return logs;
    }

    public List<String> getScreenshots() {
        return screenshots;
    }

    /*
     * Convenience methods
     */


    public void addAssertion(Assertion assertion) {
        assertions.add(assertion);
    }

    public void addStep(String step) {
        steps.add(step);
    }

    public void addLog(String log) {
        logs.add(log);
    }

    public void addParameter(String param) {
        parameters.add(param);
    }

    public void addScreenshot(String screenshot) {
        screenshots.add(screenshot);
    }


    public void setResultAndColor(ITestResult testResult) {
        String resultAsString;
        String color;
        switch (testResult.getStatus()) {
            case 1:
                resultAsString = "PASS";
                color = "9ACD32"; // green
                break;
            default:
                resultAsString = "FAIL";
                color = "FF6347"; // red
        }
        result = resultAsString;
        resultColor = color;
    }

    @Override
    public String toString() {
        return "TestDetail{" +
                ", testName='" + testName + '\'' +
                ", parameters=" + parameters +
                ", description='" + description + '\'' +
                ", result='" + result + '\'' +
                ", resultColor='" + resultColor + '\'' +
                ", assertions=" + assertions +
                ", steps=" + steps +
                ", logs=" + logs +
                ", screenshots=" + screenshots +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestDetail detail = (TestDetail) o;
        return
                testName.equals(detail.testName);
    }

    @Override
    public int hashCode() {
        return Objects.hash("", testName);
    }

    @Override
    public int compareTo(TestDetail t) {
        return 0;

    }
}
