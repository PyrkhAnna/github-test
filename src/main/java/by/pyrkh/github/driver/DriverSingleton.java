package by.pyrkh.github.driver;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {
	private static WebDriver driver;
	private static final Logger log = LogManager.getLogger();
	private static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle("log4j2");
	}

	private DriverSingleton() {
	};

	public static WebDriver getDriver() {
		if (driver == null) {
			System.setProperty(bundle.getString("geckodriver"), bundle.getString("geckodriverPath"));
			driver = new FirefoxDriver();
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			log.info("Browser started");
		}
		return driver;
	}

	public static void closeDriver() {
		driver.quit();
		driver = null;
		log.info("Browser closed");
	}
}
