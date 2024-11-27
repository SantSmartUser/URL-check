package util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.*;

public class ExtentReportGenerator extends Base {
	static Date date1 = new Date();
	static SimpleDateFormat formatter = new SimpleDateFormat("dd_MMM_yyyy_HH_mm");
	// System.out.println();
	public static String filename1 = "UrlStatus_" + formatter.format(date1);

	public static ExtentReports getExtentReport() {
		ExtentReports extent = new ExtentReports();
		File file = new File(props.getProperty("extentReport_path") + filename1 + ".html");
		ExtentSparkReporter sparkReprter = new ExtentSparkReporter(file);
		sparkReprter.config().setTheme(Theme.DARK);
		sparkReprter.config().setReportName("URL check");
		sparkReprter.config().setDocumentTitle("URL status");
		// sparkReprter.config().setCss(null);
		extent.attachReporter(sparkReprter);
		return extent;
	}
}
