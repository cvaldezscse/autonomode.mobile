package dev.cvaldezscse.reporting;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * TestReport
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
public class TestReport {

    private String name;
    private String repo;
    private String info;
    private String date;
    private Map<String, TestDetail> testDetails;
    private List<TestDetail> testDetailsList; // attempt to inline refactor //
    private String version;
    private String build;
    private String totalTest;
    private String pass;
    private String fail;
    private String passRate;

    public List<TestDetail> getTestDetailsList() {
        testDetailsList = new ArrayList<>();
        testDetails.forEach((k,v) -> testDetailsList.add(v));
        Collections.sort(testDetailsList);
        return testDetailsList;
    }

    private String failRate;

    public TestReport(String name, String repo, String info) {
        this.name = name;
        this.repo = repo;
        this.info = info;
        this.date = new SimpleDateFormat("MM/DD/YYYY hh:mm:ss aaa").format(new Date());
        version = "";
        build = "";
        totalTest = "";
        pass = "";
        fail = "";
        passRate = "";
        failRate = "";
        testDetails = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getRepo() {
        return repo;
    }

    public void addTestDetail(TestDetail testDetail) {
        testDetails.put(testDetail.getTestName(), testDetail);
    }

    public Map<String, TestDetail> getTestDetails() {
        return testDetails;
    }

    public TestDetail getTestDetail(String key) {
        return testDetails.get(key);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getTotalTest() {
        return totalTest;
    }

    public void setTotalTest(String totalTest) {
        this.totalTest = totalTest;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public String getFailRate() {
        return failRate;
    }

    public void setFailRate(String failRate) {
        this.failRate = failRate;
    }
}
