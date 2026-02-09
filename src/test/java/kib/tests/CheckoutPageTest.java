package kib.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;
import kib.pageobjects.CardPage;
import kib.pageobjects.CheckoutPage;
import kib.pageobjects.ProductsPage;
import kib.testcomponents.BaseTest;

/** Checkout flow and validation coverage tests. */
public class CheckoutPageTest extends BaseTest {

	private CheckoutPage goToCheckout() throws IOException {
		final String storePassword = "Test123";
		final String productName = "Test Product";

		loginPage.enterPassword(storePassword);
		ProductsPage productsPage = loginPage.clickSubmit();
		CardPage cardPage = productsPage.addToCart(productName);
		return cardPage.buyNow();
	}

	@Test
	public void submitWithAllFieldsEmpty_showsAllValidationErrors() throws IOException {
		final String empty = "";
		CheckoutPage checkoutPage = goToCheckout();
		checkoutPage.fillCheckoutForm(empty, empty, empty, empty, empty, empty, empty, empty);
		checkoutPage.completeOrder();

		Assert.assertEquals(checkoutPage.getErrorForEmail().trim(), "Enter an email or phone number");
		Assert.assertEquals(checkoutPage.getErrorForLastName().trim(), "Enter a last name");
		Assert.assertEquals(checkoutPage.getErrorForAddress().trim(), "Enter an address");
		Assert.assertEquals(checkoutPage.getErrorForCity().trim(), "Enter a city");
		Assert.assertEquals(checkoutPage.getErrorForPostalCode().trim(), "Enter a ZIP / postal code");
		Assert.assertEquals(checkoutPage.getErrorForPhone().trim(), "Enter a phone number");
		Assert.assertEquals(checkoutPage.getErrorForState().trim(), "Select a state / province");
	}

	@Test
	public void invalidEmail_showsError() throws IOException {
		final String invalidEmail = "notanemail";
		final String firstName = "John";
		final String lastName = "Doe";
		final String address = "123 Main Street";
		final String city = "San Jose";
		final String postalCode = "96150";
		final String phone = "2125551234";
		final String state = "California";

		CheckoutPage checkoutPage = goToCheckout();
		checkoutPage.fillCheckoutForm(invalidEmail, firstName, lastName, address, city, postalCode, phone, state);
		checkoutPage.completeOrder();

		Assert.assertEquals(checkoutPage.getErrorForEmail().trim(), "Enter a valid email");
	}

	@Test
	public void invalidPhone_showsError() throws IOException {
		final String email = "testuser@email.com";
		final String firstName = "John";
		final String lastName = "Doe";
		final String address = "123 Main Street";
		final String city = "San Jose";
		final String postalCode = "96150";
		final String invalidPhone = "999";
		final String state = "California";

		CheckoutPage checkoutPage = goToCheckout();
		checkoutPage.fillCheckoutForm(email, firstName, lastName, address, city, postalCode, invalidPhone, state);
		checkoutPage.completeOrder();

		Assert.assertEquals(checkoutPage.getErrorForPhone().trim(), "Enter a valid phone number");
	}

	@Test
	public void submitOrderSuccessfully() throws IOException {
		final String email = "testuser@email.com";
		final String firstName = "John";
		final String lastName = "Doe";
		final String address = "123 Main Street";
		final String city = "San Jose";
		final String postalCode = "96150";
		final String phone = "2125551234";
		final String state = "California";

		CheckoutPage checkoutPage = goToCheckout();

		checkoutPage.fillCheckoutForm(email, firstName, lastName, address, city, postalCode, phone, state);
		checkoutPage.completeOrder();

		Assert.assertTrue(checkoutPage.isPaymentErrorVisible(), "Payment error banner did not appear");
	}

	@DataProvider
	public Object[][] missingFieldData() {
		return new Object[][] {
				{ "", "John", "Doe", "123 Main Street", "San Jose", "96150", "2125551234", "California",
						"Enter an email or phone number", "email" },
				{ "testuser@email.com", "John", "", "123 Main Street", "San Jose", "96150", "2125551234", "California",
						"Enter a last name", "lastName" },
				{ "testuser@email.com", "John", "Doe", "", "San Jose", "96150", "2125551234", "California",
						"Enter an address", "address" },
				{ "testuser@email.com", "John", "Doe", "123 Main Street", "San Jose", "", "2125551234", "California",
						"Enter a ZIP / postal code", "postal" },
				{ "testuser@email.com", "John", "Doe", "123 Main Street", "", "96150", "2125551234", "California",
						"Enter a city", "city" },
				{ "testuser@email.com", "John", "Doe", "123 Main Street", "San Jose", "96150", "", "California",
						"Enter a phone number", "phone" },
				{ "testuser@email.com", "John", "Doe", "123 Main Street", "San Jose", "96150", "2125551234", "",
						"Select a state / province", "state" } };
	}

	@Test(dataProvider = "missingFieldData")
	public void missingFieldShowsError(String email, String firstName, String lastName, String address, String city,
			String postalCode, String phone, String state, String expectedError, String field) throws IOException {
		CheckoutPage checkoutPage = goToCheckout();
		checkoutPage.fillCheckoutForm(email, firstName, lastName, address, city, postalCode, phone, state);
		checkoutPage.completeOrder();

		String errorText;
		switch (field) {
		case "email":
			errorText = checkoutPage.getErrorForEmail();
			break;
		case "lastName":
			errorText = checkoutPage.getErrorForLastName();
			break;
		case "address":
			errorText = checkoutPage.getErrorForAddress();
			break;
		case "postal":
			errorText = checkoutPage.getErrorForPostalCode();
			break;
		case "city":
			errorText = checkoutPage.getErrorForCity();
			break;
		case "phone":
			errorText = checkoutPage.getErrorForPhone();
			break;
		case "state":
			errorText = checkoutPage.getErrorForState();
			break;
		default:
			throw new IllegalArgumentException("Unknown field: " + field);
		}

		Assert.assertEquals(errorText.trim(), expectedError, "Unexpected error message");
	}
}
