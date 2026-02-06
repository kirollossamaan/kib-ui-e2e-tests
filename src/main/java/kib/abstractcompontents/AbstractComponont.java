package kib.abstractcompontents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

/** Shared UI helpers and navigation actions. */
public class AbstractComponont {
	WebDriver driver;

	public AbstractComponont(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='menu-list__link-title' and text()='Home']")
	WebElement homeLink;
	@FindBy(xpath = "//span[@class='menu-list__link-title' and text()='Catalog']")
	WebElement catalogLink;
	@FindBy(xpath = "//span[@class='menu-list__link-title' and text()='Contact']")
	WebElement contactLink;

	public void goToHomePage() {
		homeLink.click();
	}

	public void goToCatalogPage() {
		catalogLink.click();
	}

	public void goToContactPage() {
		contactLink.click();
	}

	public void waitForElementToBeVisible(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForElementToBeClickable(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

}
