package kib.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import kib.abstractcompontents.AbstractComponont;

/** Page object for the password gate/login screen. */
public class LoginPage extends AbstractComponont {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "password")
	WebElement passwordInput;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitButton;

	@FindBy(className = "error-message")
	WebElement errorMessage;

	By errorMessageLocator = By.className("error-message");

	public void goTo() {
		driver.get("https://kib-connect-demo-store-4.myshopify.com/password");
	}

	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}

	public ProductsPage clickSubmit() {
		submitButton.click();
		ProductsPage productsPage = new ProductsPage(driver);
		return productsPage;
	}

	public String getErrorMessage() {
		waitForElementToBeVisible(errorMessageLocator);
		return errorMessage.getText();
	}

}
