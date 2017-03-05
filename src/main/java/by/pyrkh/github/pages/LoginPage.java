package by.pyrkh.github.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {
	private static final Logger log = LogManager.getLogger();
	private static final String BASE_URL = "https://github.com/login";

	@FindBy(id = "login_field")
	private WebElement login;
	@FindBy(id = "password")
	private WebElement password;
	@FindBy(xpath = "//input[@value='Sign in']")
	private WebElement submitButton;
	@FindBy(xpath = "//div[@id='js-flash-container']//div[@class='container']")
	private WebElement errorMessage;
	@FindBy(xpath = "//div[@id='js-pjax-container']//h2")
	private WebElement loginPageText;
	@FindBy(xpath = ".//*[@id='user-links']//button[contains(text(),'Sign out')]")
	private WebElement logOutSubmit;
	@FindBy(xpath = "//a[@aria-label='View profile and more']")
	private WebElement linkToProfileMenu;
	@FindBy(xpath = "//input[@aria-label='Search GitHub']")
	private WebElement searchField;
	@FindBy(xpath = ".//*[@id='user-links']//a[contains(text(),'Your profile')]")
	private WebElement linkForPersonalPage;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
		log.info("Login page opened");
	}

	public void fillLoginForm(String loginValue, String passwordValue) {
		login.clear();
		login.sendKeys(loginValue);
		password.clear();
		password.sendKeys(passwordValue);
		submitButton.click();
		log.info("Fill login form: username - " + loginValue + ", password - " + passwordValue);
	}

	public String getLoginError() {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(errorMessage));
		return errorMessage.getText();
	}

	public String ifLogInSuccessful() {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(loginPageText));
		return loginPageText.getText();
	}

	public void logOut() {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(searchField));
		searchField.click();
		linkToProfileMenu.click();
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(logOutSubmit));
		logOutSubmit.click();
		log.info("Log out from account");
	}

	public void moveToPersonalPage() {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(searchField));
		searchField.click();
		linkToProfileMenu.click();
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(linkForPersonalPage));
		linkForPersonalPage.click();
	}
}
