package kib.tests;

import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;
import kib.pageobjects.CardPage;
import kib.pageobjects.CheckoutPage;
import kib.pageobjects.ProductsPage;
import kib.testcomponents.BaseTest;

/** Product card page coverage tests. */
public class CardPageTest extends BaseTest {

	@Test
	public void buyNowButtonIsVisibleOnCardPage() throws IOException {
		final String storePassword = "Test123";
		final String productName = "Test Product";

		loginPage.enterPassword(storePassword);
		ProductsPage productsPage = loginPage.clickSubmit();
		CardPage cardPage = productsPage.addToCart(productName);

		Assert.assertTrue(cardPage.isBuyNowButtonVisible(), "Buy Now button should be visible on card page");
	}

	@Test
	public void buyNowOpensCheckout() throws IOException {
		final String storePassword = "Test123";
		final String productName = "Test Product";

		loginPage.enterPassword(storePassword);
		ProductsPage productsPage = loginPage.clickSubmit();
		CardPage cardPage = productsPage.addToCart(productName);

		CheckoutPage checkoutPage = cardPage.buyNow();
		Assert.assertNotNull(checkoutPage, "Expected CheckoutPage after clicking buy now");
	}
}
