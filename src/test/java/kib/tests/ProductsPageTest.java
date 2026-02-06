package kib.tests;

import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;
import kib.pageobjects.ProductsPage;
import kib.testcomponents.BaseTest;

/** Product listing coverage tests. */
public class ProductsPageTest extends BaseTest {

	@Test
	public void productsListVisibleAfterLogin() throws IOException {
		final String storePassword = "Test123";

		loginPage.enterPassword(storePassword);
		ProductsPage productsPage = loginPage.clickSubmit();

		Assert.assertTrue(productsPage.getProductsList().size() > 0, "Expected product list to be visible after login");
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void getProductThrowsForMissingProduct() throws IOException {
		final String storePassword = "Test123";

		loginPage.enterPassword(storePassword);
		ProductsPage productsPage = loginPage.clickSubmit();

		productsPage.getProduct("Non Existing Product");
	}
}
