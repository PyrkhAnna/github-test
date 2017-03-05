package by.pyrkh.github.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import by.pyrkh.github.driver.DriverSingleton;
import by.pyrkh.github.pages.RepositoryPage;
import by.pyrkh.github.pages.LoginPage;
import by.pyrkh.github.pages.MainPage;
//import by.pyrkh.github.pages.RepositoriesPage;

public class Steps {
	private WebDriver driver;
	private final Logger log = LogManager.getLogger();
		
	public void initBrowser(){
		driver = DriverSingleton.getDriver();
		log.info("initBrowser");
	}

	public void closeBrowser() {
		DriverSingleton.closeDriver();
		log.info("closeBrowser");
	}
	
	//MainPage
	/*public void moveToLoginPage() {
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		mainPage.moveToLoginPage();
	}
	public void moveToCreateAccountPageWithSmallBotton() {
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		mainPage.moveToCreateAccountPage();
	}*/
	public void createNewAccount(String nameValue, String emailValue, String passwordValue) {
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		mainPage.fillCreateAccountForm(nameValue, emailValue, passwordValue);
	}
	public String getErrorMessageForAccount() {
		MainPage mainPage = new MainPage(driver);
		return mainPage.getRegistrationAccountError();
	}
	public String getErrorMessageForUsername() {
		MainPage mainPage = new MainPage(driver);
		return mainPage.getRegistrationUsernameError();
	}
	public String getErrorMessageForEmail() {
		MainPage mainPage = new MainPage(driver);
		return mainPage.getRegistrationEmailError();
	}
	public String getErrorMessageForPassword() {
		MainPage mainPage = new MainPage(driver);
		return mainPage.getRegistrationPasswordError();
	}
	public String ifOnMainPage() {
		MainPage mainPage = new MainPage(driver);
		return mainPage.ifOnMainPage();
	}
		
	//LoginPage
	public void logInGitHub(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.fillLoginForm(username, password);
	}
	public String getErrorMessageForLogIn() {
		LoginPage loginPage = new LoginPage(driver);
		return loginPage.getLoginError();
	}
	public String isLoggedInSuccessful() {
		LoginPage loginPage = new LoginPage(driver);
		return loginPage.ifLogInSuccessful();
	}
	public void moveToPersonalPage(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.moveToPersonalPage();
	}
	public void logOut() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.logOut();
	}
	
	
	//RepositoryPage
	public void createNewRepository(String repositoryName, String repositoryDescription){
		RepositoryPage repositoryPage = new RepositoryPage(driver);
		repositoryPage.openPage();
		repositoryPage.createRepository(repositoryName, repositoryDescription);
	}
	public boolean ifNewRepositoryCreateSuccessful(String repositoryName) {
		RepositoryPage repositoryPage = new RepositoryPage(driver);
		return repositoryPage.ifNewRepositoryCreateSuccessfully(repositoryName);
	}
	public String getErrorMessageForCreateNewRepository() {
		RepositoryPage repositoryPage = new RepositoryPage(driver);
		return repositoryPage.catchErrorMessage();
	}
	
	public void deleteRepository(String repositoryName){
		RepositoryPage repositoryPage = new RepositoryPage(driver);
		repositoryPage.deleteRepository(repositoryName);
	}
	public String ifRepositoryDeleteSuccessfully(String repositoryName) {
		RepositoryPage repositoryPage = new RepositoryPage(driver);
		return repositoryPage.verifyDeletingRepository();
	}
	public boolean notExistRepositoryDelete(String repositoryName) {
		RepositoryPage repositoryPage = new RepositoryPage(driver);
		repositoryPage.verifyRepositoryNotExist(repositoryName);
		return repositoryPage.verifyRepositoryNotExist(repositoryName);
	}
	
		
}
