package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	private static ExtentReports extent;
	private static ExtentTest test;
	public static String reportPath;

	public static ExtentReports getReportInstance() {
		if (extent == null) {
			String timestamp = new SimpleDateFormat("YYYY-MM-DD_HH-mm-ss").format(new Date());
			reportPath = "reports/ExtentReport_" + timestamp + ".html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setDocumentTitle("test report");
			reporter.config().setReportName(reportPath);
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}
		return extent;
	}

	public static ExtentTest createTest(String testname) {
		test = getReportInstance().createTest(testname);
		return test;
	}

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String Path = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png";
			System.out.println("path of SS" + Path);
			FileUtils.copyFile(src, new File(Path));
			return Path;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
