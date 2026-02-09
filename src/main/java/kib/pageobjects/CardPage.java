package kib.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kib.abstractcompontents.AbstractComponont;

/** Page object for the product detail/card page actions. */
public class CardPage extends AbstractComponont {
	WebDriver driver;

	public CardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "shopify-payment-button")
	WebElement buyNowButton;

	By buyNowButtonLocator = By.className("shopify-payment-button");

	public boolean isBuyNowButtonVisible() {
		try {
			waitForElementToBeVisible(buyNowButtonLocator);
			return buyNowButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public CheckoutPage buyNow() {
		waitForElementToBeVisible(buyNowButtonLocator);
		buyNowButton.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
