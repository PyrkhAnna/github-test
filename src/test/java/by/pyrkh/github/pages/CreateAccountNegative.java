package by.pyrkh.github.pages;

import org.testng.annotations.Test;

import by.pyrkh.github.steps.Steps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class CreateAccountNegative {

	private final static Logger log = LogManager.getLogger();
	private static ResourceBundle bundle;
	private static String username;
	private static String email;
	private static String password;
	private Steps steps;

	static {
		bundle = ResourceBundle.getBundle("log4j2");
		username = bundle.getString("usernameValid");
		email = bundle.getString("emailValid");
		password = bundle.getString("password");
	}

	@DataProvider(name = "IncorrectUsername")
	public Object[][] usernames() {
		String[] arrayUsernames = bundle.getString("usernameInvalid").split(";");
		return new Object[][] { { arrayUsernames[0] }, { arrayUsernames[1] }, { arrayUsernames[2] },
				{ arrayUsernames[3] }, { "" } };
	}

	@DataProvider(name = "IncorrectEmail")
	public Object[][] emails() {
		String[] arrayEmails = bundle.getString("emailInvalid").split(";");
		return new Object[][] { { arrayEmails[0] }, { arrayEmails[1] }, { "" } };
	}

	@DataProvider(name = "IncorrectPassword")
	public Object[][] passwords() {
		String[] arrayPasswords = bundle.getString("passwordInvalid").split(";");
		return new Object[][] { { arrayPasswords[0] }, { arrayPasswords[1] }, { arrayPasswords[2] },
				{ arrayPasswords[3] }, { "" } };
	}

	@BeforeClass
	public void beforeClass() {
		log.info("CreateAccountNegativeTest started");
		steps = new Steps();
		steps.initBrowser();
	}

	@Test(priority = 1, dataProvider = "IncorrectUsername")
	public void incorrectUsername(String username) {
		steps.createNewAccount(username, email, password);
		log.info("Create account with incorrect username - " + username);
		Assert.assertEquals(false, steps.getErrorMessageForUsername().isEmpty());
	}

	@Test(priority = 2, dataProvider = "IncorrectEmail")
	public void incorrectEmail(String email) {
		steps.createNewAccount(username, email, password);
		log.info("Create account with incorrect email - " + email);
		Assert.assertEquals(false, steps.getErrorMessageForEmail().isEmpty());
	}

	@Test(priority = 3, dataProvider = "IncorrectPassword")
	public void incorrectPassword(String password) {
		steps.createNewAccount(username, email, password);
		log.info("Create account with incorrect password - " + password);
		Assert.assertEquals(false, steps.getErrorMessageForPassword().isEmpty());
	}

	@AfterClass
	public void afterClass() {
		steps.closeBrowser();
	}
}
