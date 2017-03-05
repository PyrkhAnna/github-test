package by.pyrkh.github.pages;

import org.testng.annotations.Test;

import by.pyrkh.github.steps.Steps;

import org.testng.annotations.BeforeClass;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class CreateRepositoryPositiveNegative {

	private final static Logger log = LogManager.getLogger();
	private static ResourceBundle bundle;
	private static String username;
	private static String password;
	private static String repositoryName;
	private static String existRepositoryName;
	private Steps steps;

	static {
		bundle = ResourceBundle.getBundle("log4j2");
		username = bundle.getString("username");
		password = bundle.getString("password");
		repositoryName = bundle.getString("repositoryName");
		existRepositoryName = bundle.getString("existRepositoryName");
	}

	@BeforeClass
	public void beforeClass() {
		log.info("CreateRepositoryPositiveNegativeTest started");
		steps = new Steps();
		steps.initBrowser();
		steps.logInGitHub(username, password);
		steps.isLoggedInSuccessful();
	}

	@Test(priority = 1)
	public void createNewRepositoryPositive() {
		steps.createNewRepository(repositoryName, repositoryName);
		log.info("Create New Repository  - " + repositoryName);
		Assert.assertTrue(steps.ifNewRepositoryCreateSuccessful(repositoryName));
	}

	@Test(priority = 2)
	public void createNewRepositoryNegative() {
		steps.createNewRepository(existRepositoryName, existRepositoryName);
		log.info("Create New Repository  - " + existRepositoryName);
		Assert.assertFalse(steps.getErrorMessageForCreateNewRepository().isEmpty());
	}

	@AfterClass
	public void afterClass() {
		steps.deleteRepository(repositoryName);
		steps.closeBrowser();
	}
}
