package by.pyrkh.github.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage {
	private static final Logger log = LogManager.getLogger();
	private static final String BASE_URL = "https://github.com";

	@FindBy(id = "user[login]")
	private WebElement usernameField;
	@FindBy(id = "user[email]")
	private WebElement userEmailField;
	@FindBy(id = "user[password]")
	private WebElement passwordField;
	@FindBy(xpath = ".//button[text()='Sign up for GitHub']")
	private WebElement signUpBigButton;
	@FindBy(xpath = ".//form[@id='signup-form']/div[@class='flash flash-error my-3']")
	private WebElement errorMessageForAccount;
	@FindBy(xpath = ".//*[@id='signup-form']/dl[1]/dd[2]")
	private WebElement errorMessageForUsername;
	@FindBy(xpath = ".//*[@id='signup-form']/dl[2]/dd[2]")
	private WebElement errorMessageForEmail;
	@FindBy(xpath = ".//*[@id='signup-form']/dl[3]/dd[2]")
	private WebElement errorMessageForPassword;
	@FindBy(xpath = "html/body/div[4]/div[1]/div/div/div[1]/h1")
	private WebElement verificationOfMainPage;

	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
		log.info("Main page is opened");
	}

	public void fillCreateAccountForm(String nameValue, String emailValue, String passwordValue) {
		usernameField.clear();
		usernameField.sendKeys(nameValue);
		userEmailField.clear();
		userEmailField.sendKeys(emailValue);
		passwordField.clear();
		passwordField.sendKeys(passwordValue);
		signUpBigButton.click();
		log.info("Main form for new account is filled");
	}

	public String getRegistrationAccountError() {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(errorMessageForAccount));
		return errorMessageForAccount.getText();
	}

	public String getRegistrationUsernameError() {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(errorMessageForUsername));
		return errorMessageForUsername.getText();
	}

	public String getRegistrationEmailError() {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(errorMessageForEmail));
		return errorMessageForEmail.getText();
	}

	public String getRegistrationPasswordError() {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(errorMessageForPassword));
		return errorMessageForPassword.getText();
	}

	public String ifOnMainPage() {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(verificationOfMainPage));
		return verificationOfMainPage.getText();
	}
}
