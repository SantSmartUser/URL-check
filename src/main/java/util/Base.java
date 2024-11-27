package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

import java.awt.AWTException;
import java.awt.Dimension;

import java.awt.Rectangle;

import java.awt.Robot;

import java.awt.Toolkit;

import java.awt.image.BufferedImage;

public class Base {

	public WebDriver driver;

	public static Properties props;

	public Base() {
		try {
			props = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\Amjad.Sayyed\\Downloads\\URL_Check\\src\\main\\java\\Config\\config.properties");
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String takeScreenshot(String testName, WebDriver driver) throws IOException, AWTException {
		Robot robot = new Robot();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rect = new Rectangle(dimension);
		BufferedImage bufferedImage = robot.createScreenCapture(rect);

		String url = driver.getCurrentUrl();
		System.out.println(url);
		String a[] = url.split("//");
		String b[] = a[1].split("/");

		String screenshotPath = "C:\\Users\\Amjad.Sayyed\\Downloads\\URL_Check\\ExtentReports\\" + b[0] + ".png";

		ImageIO.write(bufferedImage, "png", new File(screenshotPath));

		// File sourceScreenshots = ((TakesScreenshot)
		// driver).getScreenshotAs(OutputType.FILE);

		String destinationScreenshotpath = "C:\\Users\\Amjad.Sayyed\\Downloads\\URL_Check\\ExtentReports" + b[0]
				+ ".png";

		// System.out.println(System.getProperty("user.dir"));

		/*
		 * try {
		 * 
		 * //FileUtils.copyFile(sourceScreenshots, new File(screenshotPath));
		 * 
		 * } catch (IOException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * }
		 */

		return destinationScreenshotpath;

	}
}
