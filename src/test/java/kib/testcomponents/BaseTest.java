package kib.testcomponents;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.io.FileInputStream;
import java.io.IOException;
import kib.pageobjects.LoginPage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/** Base test setup/teardown and driver initialization. */
public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;

	public void initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/kib/config/config.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		boolean headless = Boolean.parseBoolean(prop.getProperty("headless", "false"));
		if (browserName.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (headless) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
			}
			driver = new ChromeDriver(options);

		} else if (browserName.equals("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			if (headless) {
				options.addArguments("-headless");
			}
			driver = new FirefoxDriver(options);
		} else if (browserName.equals("edge")) {
			EdgeOptions options = new EdgeOptions();
			if (headless) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
			}
			driver = new EdgeDriver(options);
		} else {
			throw new RuntimeException("Browser name is not supported: " + browserName);
		}
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public LoginPage launchApplication() throws IOException {
		initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}

	@AfterMethod
	public void closeApplication() {
		driver.quit();
	}

}