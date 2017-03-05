package by.pyrkh.github.pages;

import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RepositoryPage extends AbstractPage {

	private static final Logger log = LogManager.getLogger();
	private static final String BASE_URL = "https://github.com/new";
	private static final String REPOSITIRY_URL = "https://github.com/";
	private static ResourceBundle bundle;
	private boolean flag = false;

	@FindBy(xpath = ".//*[@id='new_repository']//h2")
	private WebElement verificationOfPage;
	@FindBy(id = "repository_name")
	private WebElement fieldRepositoryName;
	@FindBy(id = "repository_description")
	private WebElement fieldRepositoryDescription;
	@FindBy(xpath = ".//*[@id='new_repository']//button[contains(text(),'Create repository')]")
	private WebElement createRepositoryButton;
	@FindBy(xpath = ".//*[@id='js-flash-container']//div[@class='container']")
	private WebElement errorMessage;
	@FindBy(xpath = ".//*[@id='js-repo-pjax-container']//strong/a")
	private WebElement verificationOfRepository;
	@FindBy(xpath = ".//*[@id='js-repo-pjax-container']//nav/a[5]")
	private WebElement settings;
	@FindBy(xpath = ".//*[@id='options_bucket']//button[contains(text(),'Delete this repository')]")
	private WebElement deleteRepositoryButton;
	@FindBy(xpath = ".//*[@id='facebox']//form/p/input")
	private WebElement fieldConfirmRepositoryNameForDeleting;
	@FindBy(xpath = ".//*[@id='facebox']//form/button")
	private WebElement confirmForDeletingButton;
	@FindBy(xpath = ".//div[@id='js-pjax-container']//h2")
	private WebElement loginPageText;
	@FindBy(xpath = ".//*[@id='parallax_error_text']")
	private WebElement verificationOfErrorPage;

	public RepositoryPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
		bundle = ResourceBundle.getBundle("log4j2");
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
		if (verificationOfPage.getText().contains(bundle.getString("createRepositoryPageText"))) {
			flag = true;
			log.info("Repository page opened");
		} else
			log.info("Repository page didn't open");
	}

	public void createRepository(String repositoryName, String repositoryDescription) {
		if (flag) {
			(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(fieldRepositoryName));
			fieldRepositoryName.clear();
			fieldRepositoryName.sendKeys(repositoryName);
			fieldRepositoryDescription.clear();
			fieldRepositoryDescription.sendKeys(repositoryDescription);
			createRepositoryButton.click();
			log.info("Repository " + repositoryName + " created");
		}
	}
	public boolean ifNewRepositoryCreateSuccessfully(String repositoryName) {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(verificationOfRepository));
		if (verificationOfRepository.getText().equals(repositoryName))
			return true;
		else return false;
	}
	public String catchErrorMessage() {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(errorMessage));
		return errorMessage.getText();
	}
	public void deleteRepository(String repositoryName) {
		driver.navigate().to(REPOSITIRY_URL + bundle.getString("username") + "/" + repositoryName);
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(settings));
		settings.click();
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(deleteRepositoryButton));
		deleteRepositoryButton.click();
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(fieldConfirmRepositoryNameForDeleting));
		fieldConfirmRepositoryNameForDeleting.sendKeys(repositoryName);
		fieldConfirmRepositoryNameForDeleting.sendKeys(Keys.ENTER);
		log.info("Repository " + repositoryName + " deleted");
	}
	public String verifyDeletingRepository() {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(loginPageText));
		return loginPageText.getText();
	}
	public boolean verifyRepositoryNotExist(String repositoryName) {
		driver.navigate().to(REPOSITIRY_URL + bundle.getString("username") + "/" + repositoryName);
		(new WebDriverWait(driver, 50)).until(ExpectedConditions.visibilityOf(verificationOfErrorPage));
		return driver.getTitle().equals(bundle.getString("repositoryNotExistText"));
	}
}
