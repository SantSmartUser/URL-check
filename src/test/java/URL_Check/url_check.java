package URL_Check;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import util.ReadURL;

public class url_check {
	public WebDriver driver;

	//parameters will help to run code in parallel mode. 
	//Once for chrome and once for firefox
	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser) {
		System.out.println("browser name: " + browser);
		if (browser.equalsIgnoreCase("chrome"))
		{
		
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
		}
		else
		{
			throw new IllegalArgumentException("Invalid browser value: " + browser);
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	// data provider will read urls from excel and return in 2 D array.
	@DataProvider(name = "urls")
	public Object[][] urlDataProvider() throws IOException {

		ReadURL ru = new ReadURL();

		List<String> urls = ru.readURLsFromExcel();

		Object[][] data = new Object[urls.size()][1];

		for (int i = 0; i < urls.size(); i++) {
			data[i][0] = urls.get(i);
		}

		return data;
	}
	

	//test will be executed for # of times which we have data in excel 

	@Test(dataProvider = "urls", retryAnalyzer=util.RetryAnalyzer.class)
	public void CheckingUrlStatus(String url) {
		driver.get(url);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
				// Check the status code
		try {
			int statusCode = ((HttpURLConnection) new URL(url).openConnection()).getResponseCode();

			if (statusCode <= 200 & statusCode > 300)
				System.out.println(url + " URL is  working" + statusCode);
			else
				System.out.println(url + " URL is  not working" + statusCode);
			} catch (Exception e) {

		}
		
	}
}
