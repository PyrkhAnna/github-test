package by.pyrkh.github.pages;

import org.testng.annotations.Test;

import by.pyrkh.github.steps.Steps;

import org.testng.annotations.BeforeClass;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class DeleteRepositoryPositiveNegative {

	private final static Logger log = LogManager.getLogger();
	private static ResourceBundle bundle;
	private static String repositoryName;
	private static String repositoryNotExist;
	private Steps steps;

	static {
		bundle = ResourceBundle.getBundle("log4j2");
		repositoryName = bundle.getString("repositoryName");
		repositoryNotExist = bundle.getString("repositoryNotExistText");
	}

	@BeforeClass
	public void beforeClass() {
		log.info("DeleteRepositoryPositiveNegative started");
		steps = new Steps();
		steps.initBrowser();
		steps.logInGitHub(bundle.getString("username"), bundle.getString("password"));
		steps.isLoggedInSuccessful();
		steps.createNewRepository(repositoryName, repositoryName);
	}

	@Test(priority = 1)
	public void deleteRepositoryPositive() {
		steps.deleteRepository(repositoryName);
		log.info("Delete Repository Positive - " + repositoryName);
		Assert.assertEquals(bundle.getString("loginPageText"), steps.ifRepositoryDeleteSuccessfully(repositoryName));
	}

	@Test(priority = 2)
	public void deleteRepositoryNegative() {
		log.info("Delete Repository Negative - " + repositoryNotExist);
		Assert.assertTrue(steps.notExistRepositoryDelete(repositoryNotExist));
	}

	@AfterClass
	public void afterClass() {
		steps.closeBrowser();
	}
}
