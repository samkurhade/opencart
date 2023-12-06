package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger; // logging
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger; // for logging
	public ResourceBundle rb;

	@BeforeClass(groups = {"Master","Sanity","Regression"})
	@Parameters("browser")
	public void setup(String br) {
		rb = ResourceBundle.getBundle("config"); // load config.properties file
		logger = LogManager.getLogger(this.getClass());

		if (br.equals("chrome")) {
			//Below 2 statements to avoid " Chrome is being controlled by Automated Software "
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			driver = new ChromeDriver(options);
		} else if (br.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().deleteAllCookies();
//		driver.get("http://localhost/opencart/upload/index.php");
		driver.get(rb.getString("appURL"));
		driver.manage().window().maximize();

	}

	@AfterClass(groups = {"Master","Sanity","Regression"})
	public void tearDown() throws InterruptedException {
//		Thread.sleep(4000);
//		driver.quit();
//		driver.close();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomAlphaNumeric() {
		String str = RandomStringUtils.randomAlphabetic(4);
		String num = RandomStringUtils.randomNumeric(3);
		return str + "@" + num;
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}

}
