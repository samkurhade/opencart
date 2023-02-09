package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Regression","Master"})
	void test_account_registration() {

		logger.debug("application logs...");
		logger.info("*** Starting TC_001_AccountRegistrationTest ***");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount link");
			hp.clickRegister();
			logger.info("Clicked on Register link");

			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			logger.info("Providing customer data");
			regPage.setFirstName(randomString().toUpperCase());
			regPage.setLastName(randomString().toUpperCase());
			regPage.setEmail(randomString() + "@gmail.com"); // randomly generated email address
			regPage.setPassword(randomAlphaNumeric());
			regPage.setNewsletter();
			regPage.setPrivacyPolicy();
			regPage.clickContinue();
			logger.info("Clicked on continue");
			String confMsg = regPage.getConfirmationMsg();
			logger.info("Validating expected msg");
			Assert.assertEquals(confMsg, "Your Account Has Been Created!", "Test failed");
		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		logger.info("*** Finished TC_001_AccountRegistrationTest ***");

	}

}
