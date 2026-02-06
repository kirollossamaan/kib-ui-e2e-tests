package kib.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kib.abstractcompontents.AbstractComponont;

/** Page object for checkout form and validation errors. */
public class CheckoutPage extends AbstractComponont {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email")
	WebElement emailInput;

	@FindBy(name = "firstName")
	WebElement firstNameInput;

	@FindBy(name = "lastName")
	WebElement lastNameInput;

	@FindBy(name = "address1")
	WebElement addressInput;

	@FindBy(name = "city")
	WebElement cityInput;

	@FindBy(name = "postalCode")
	WebElement postalCodeInput;

	@FindBy(name = "phone")
	WebElement phoneInput;

	@FindBy(name = "zone")
	WebElement stateDropdown;

	@FindBy(id = "checkout-pay-button")
	WebElement completeOrderButton;

	@FindBy(id = "PaymentErrorBanner")
	WebElement paymentErrorBanner;

	@FindBy(id = "error-for-email")
	WebElement errorForEmail;

	@FindBy(id = "error-for-TextField1")
	WebElement errorForLastName;

	@FindBy(id = "error-for-shipping-address1")
	WebElement errorForAddress;

	@FindBy(id = "error-for-TextField3")
	WebElement errorForCity;

	@FindBy(id = "error-for-Select1")
	WebElement errorForState;

	@FindBy(id = "error-for-TextField4")
	WebElement errorForPostalCode;

	@FindBy(id = "error-for-TextField5")
	WebElement errorForPhone;

	By errorForEmailLocator = By.id("error-for-email");
	By errorForLastNameLocator = By.id("error-for-TextField1");
	By errorForAddressLocator = By.id("error-for-shipping-address1");
	By errorForCityLocator = By.id("error-for-TextField3");
	By errorForStateLocator = By.id("error-for-Select1");
	By errorForPostalCodeLocator = By.id("error-for-TextField4");
	By errorForPhoneLocator = By.id("error-for-TextField5");

	By emailInputLocator = By.id("email");
	By paymentErrorBannerLocator = By.id("PaymentErrorBanner");
	By completeOrderButtonLocator = By.id("checkout-pay-button");

	public void fillCheckoutForm(String email, String firstName, String lastName, String address, String city,
			String postalCode, String phone, String state) {
		waitForElementToBeVisible(emailInputLocator);
		emailInput.sendKeys(email);
		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		addressInput.sendKeys(address);
		cityInput.sendKeys(city);
		postalCodeInput.sendKeys(postalCode);
		phoneInput.sendKeys(phone);
		stateDropdown.sendKeys(state);
	}

	public void completeOrder() {
		waitForElementToBeClickable(completeOrderButtonLocator);
		completeOrderButton.click();
	}

	public boolean isPaymentErrorVisible() {
		waitForElementToBeVisible(paymentErrorBannerLocator);
		return paymentErrorBanner.isDisplayed();
	}

	public String getErrorForEmail() {
		waitForElementToBeVisible(errorForEmailLocator);
		return errorForEmail.getText();
	}

	public String getErrorForLastName() {
		waitForElementToBeVisible(errorForLastNameLocator);
		return errorForLastName.getText();
	}

	public String getErrorForAddress() {
		waitForElementToBeVisible(errorForAddressLocator);
		return errorForAddress.getText();
	}

	public String getErrorForCity() {
		waitForElementToBeVisible(errorForCityLocator);
		return errorForCity.getText();
	}

	public String getErrorForState() {
		waitForElementToBeVisible(errorForStateLocator);
		return errorForState.getText();
	}

	public String getErrorForPostalCode() {
		waitForElementToBeVisible(errorForPostalCodeLocator);
		return errorForPostalCode.getText();
	}

	public String getErrorForPhone() {
		waitForElementToBeVisible(errorForPhoneLocator);
		return errorForPhone.getText();
	}
}
