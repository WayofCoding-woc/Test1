package com.woc.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReportManager extentReportManager = new ExtentReportManager();//thread safe singleton

    private ExtentReports extentReports;

    public static ExtentReportManager getInstance() {
        return extentReportManager;
    }

    public ExtentReports getExtentReports(){
        if(null == extentReports){
            synchronized (this){
                if(null == extentReports){
                    extentReports = createExtentReportsInstance();
                }
            }
        }

        return extentReports;
    }

    private ExtentReports createExtentReportsInstance() {
        ExtentReports extentReports = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/extent-report/extent-report.html");
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Browser", "Chrome");

        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AM Extent Report");
        htmlReporter.config().setReportName("AM Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        //htmlReporter.config().setTimeStampFormat("");

        extentReports.attachReporter(htmlReporter);

        return extentReports;
    }


}
