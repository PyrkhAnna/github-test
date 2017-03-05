package by.pyrkh.github.pages;

import org.testng.annotations.Test;

import by.pyrkh.github.steps.Steps;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginWithIncorrectAndCorrectValues {

	private final static Logger log = LogManager.getLogger();
	private static ResourceBundle bundle;
	private Steps steps;

	static {
		bundle = ResourceBundle.getBundle("log4j2");
	}

	@DataProvider(name = "IncorrectValues")
	public Object[][] incorrectValues() {
		String[] arrayUsernames = bundle.getString("usernameIncorrect").split(";");
		String[] arrayPasswords = bundle.getString("passwordIncorrect").split(";");
		return new Object[][] { { arrayUsernames[0], arrayPasswords[0] }, { arrayUsernames[1], arrayPasswords[1] },
				{ arrayUsernames[2], arrayPasswords[2] }, { "", "" } };
	}

	@BeforeClass
	public void beforeClass() {
		log.info("LoginWithIncorrectAndCorrectValuesTest started");
		steps = new Steps();
		steps.initBrowser();
	}

	@Test(priority = 1, dataProvider = "IncorrectValues")
	public void incorrectValue(String username, String password) {
		log.info("Login with incorrect value" + username + ", " + password);
		steps.logInGitHub(username, password);
		Assert.assertEquals(false, steps.getErrorMessageForLogIn().isEmpty());
	}

	@Test(priority = 2)
	public void correctValue() {
		log.info("Login with correct value");
		steps.logInGitHub(bundle.getString("username"), bundle.getString("password"));
		Assert.assertEquals(bundle.getString("loginPageText"), steps.isLoggedInSuccessful());
	}

	@AfterClass
	public void afterClass() {
		steps.closeBrowser();
	}
}
