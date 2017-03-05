package by.pyrkh.github.pages;

import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.pyrkh.github.steps.Steps;

public class LogOutFromAccount {

	private final static Logger log = LogManager.getLogger();
	private static ResourceBundle bundle;
	private Steps steps;

	static {
		bundle = ResourceBundle.getBundle("log4j2");
	}

	@BeforeClass
	public void beforeClass() {
		log.info("LogOutTest started");
		steps = new Steps();
		steps.initBrowser();
		steps.logInGitHub(bundle.getString("username"), bundle.getString("password"));
		steps.isLoggedInSuccessful();
	}

	@Test(priority = 1)
	public void logOut() {
		steps.logOut();
		log.info("Log Out from account");
		Assert.assertEquals(bundle.getString("verificationOfMainPageText"), steps.ifOnMainPage());
	}

	@AfterClass
	public void afterClass() {
		steps.closeBrowser();
	}
}
