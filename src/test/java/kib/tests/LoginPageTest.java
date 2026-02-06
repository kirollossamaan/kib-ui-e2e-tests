package kib.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import kib.pageobjects.ProductsPage;
import kib.testcomponents.BaseTest;

/** Login page coverage tests. */
public class LoginPageTest extends BaseTest {

	@Test
	public void validPasswordShowsProducts() throws IOException {
		final String storePassword = "Test123";

		loginPage.enterPassword(storePassword);
		ProductsPage productsPage = loginPage.clickSubmit();

		Assert.assertTrue(productsPage.getProductsList().size() > 0, "Expected product list to be visible after login");
	}

	@Test
	public void invalidPasswordShowsError() throws IOException {
		final String wrongPassword = "WrongPassword123";

		loginPage.enterPassword(wrongPassword);
		loginPage.clickSubmit();

		String errorText = loginPage.getErrorMessage();
		Assert.assertTrue(errorText.toLowerCase().contains("incorrect"),
				"Error message does not contain 'incorrect' word");
	}
}
