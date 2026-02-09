# UI E2E Tests (KIB Demo Store)

End-to-end UI test suite for the KIB Shopify demo store using Selenium + TestNG.

## Tech Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven

## Project Structure

- `src/main/java/kib/pageobjects` - Page Object classes
- `src/main/java/kib/abstractcompontents` - Shared UI helpers/waits
- `src/main/java/kib/config` - Test configuration
- `src/test/java/kib/testcomponents` - Base test setup/teardown
- `src/test/java/kib/tests` - Test classes
- `testng.xml` - TestNG suite definition
- **Reports (generated after running tests):**
  - `target/surefire-reports/index.html` - TestNG main report
  - `target/surefire-reports/emailable-report.html` - Emailable report

## Prerequisites

- Java 17 (Temurin recommended)
- Maven 3.8+
- Chrome / Edge / Firefox installed

## Configuration

Edit `src/main/java/kib/config/config.properties`:

```
browser=edge
headless=false
```

Supported values for `browser`: `chrome`, `firefox`, `edge`.

Set `headless=true` to run in the background.

## Running Tests

From the **project root directory** in a terminal, run:

```
mvn test -Dsurefire.suiteXmlFiles=testng.xml
```

This runs the TestNG suite defined in `testng.xml` and generates the reports in `target/surefire-reports/`.

**Other options:**

Run a single test class:

```
mvn -Dtest=kib.tests.CheckoutPageTest test
```

## Reports

After running `mvn test -Dsurefire.suiteXmlFiles=testng.xml` in the terminal (from the project directory), reports are generated in **`target/surefire-reports/`**.

**Important reports:**

| Report | Path |
|--------|------|
| TestNG main report | `target/surefire-reports/index.html` |
| Emailable report | `target/surefire-reports/emailable-report.html` |

Open these HTML files in a browser to view the results.

## Test Cases (from last run)

Based on **`target/surefire-reports/emailable-report.html`** and **`target/surefire-reports/index.html`**.

**Total: 17 test cases** (17 passed, 0 failed, 0 skipped).

| Page / Test class | # Tests | Test case names |
|-------------------|--------:|------------------|
| **Login Page** | 2 | `validPasswordShowsProducts`, `invalidPasswordShowsError` |
| **Products Page** | 2 | `productsListVisibleAfterLogin`, `getProductThrowsForMissingProduct` |
| **Card Page** | 2 | `buyNowButtonIsVisibleOnCardPage`, `buyNowOpensCheckout` |
| **Checkout Page** | 11 | `invalidEmail_showsError`, `invalidPhone_showsError`, `missingFieldShowsError` (×7), `submitOrderSuccessfully`, `submitWithAllFieldsEmpty_showsAllValidationErrors` |

### Login Page (`LoginPageTest`) — 2 tests

- `validPasswordShowsProducts`
- `invalidPasswordShowsError`

### Products Page (`ProductsPageTest`) — 2 tests

- `productsListVisibleAfterLogin`
- `getProductThrowsForMissingProduct`

### Card Page (`CardPageTest`) — 2 tests

- `buyNowButtonIsVisibleOnCardPage`
- `buyNowOpensCheckout`

### Checkout Page (`CheckoutPageTest`) — 11 tests

- `submitWithAllFieldsEmpty_showsAllValidationErrors`
- `invalidEmail_showsError`
- `invalidPhone_showsError`
- `submitOrderSuccessfully`
- `missingFieldShowsError` (parameterized, 7 runs: email, lastName, address, postal, city, phone, state)

