package com.woc.extentreport;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNgExtentReportIntegration  implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = ExtentReportManager.getInstance().getExtentReports().createTest(result.getMethod().getMethodName(), " test passed");
        test.log(Status.INFO, MarkupHelper.createLabel(result.getMethod().getMethodName()+" PASSED", ExtentColor.BLUE));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentReportManager.getInstance().getExtentReports().createTest(result.getMethod().getMethodName(), " test failed");
        test.log(Status.ERROR, MarkupHelper.createLabel(result.getMethod().getMethodName() +" FAILED", ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentReportManager.getInstance().getExtentReports().createTest(result.getMethod().getMethodName(), " test skipped");
        test.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName() +" SKIPPED", ExtentColor.YELLOW));
    }

    @Override
    public void onStart(ITestContext context) {
        ExtentTest test = ExtentReportManager.getInstance().getExtentReports().createTest(context.getName(), " test suite started");
        test.log(Status.INFO, MarkupHelper.createLabel(context.getName() +" STARTED", ExtentColor.GREEN));
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentTest test = ExtentReportManager.getInstance().getExtentReports().createTest(context.getName(), " test suite completed");
        test.log(Status.INFO, MarkupHelper.createLabel(context.getName() +" COMPLETED", ExtentColor.GREEN));
        ExtentReportManager.getInstance().getExtentReports().flush();
    }
}
