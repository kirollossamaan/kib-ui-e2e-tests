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

Run all tests:

```
mvn test
```

Run a single class:

```
mvn -Dtest=kib.tests.CheckoutPageTest test
```

Run via TestNG suite:

```
mvn -Dsurefire.suiteXmlFiles=testng.xml test
```

## Reports

After a run, reports are generated here:

- TestNG report: `test-output/index.html`
- Surefire report: `target/surefire-reports/`

Open the HTML files in a browser.

## Test Coverage (Examples)

- Login: valid and invalid password
- Products: list visibility, missing product behavior
- Cart/Card: open checkout from product
- Checkout: required field validations and payment error banner

