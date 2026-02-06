package kib.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import kib.abstractcompontents.AbstractComponont;

/** Page object for the product listing grid. */
public class ProductsPage extends AbstractComponont {
	WebDriver driver;

	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "product-card-gallery__title-placeholder")
	List<WebElement> products;

	By productsListLocator = By.className("product-card-gallery__title-placeholder");

	public List<WebElement> getProductsList() {
		waitForElementToBeVisible(productsListLocator);
		return products;
	}

	public WebElement getProduct(String productName) {
		List<WebElement> productsList = getProductsList();
		return productsList.stream().filter(product -> product.getText().equals(productName)).findFirst()
				.orElseThrow(() -> new RuntimeException("Product " + productName + " not found"));
	}

	public CardPage addToCart(String productName) {
		WebElement product = getProduct(productName);
		product.click();
		CardPage cardPage = new CardPage(driver);
		return cardPage;
	}

}
