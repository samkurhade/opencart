package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	void test_loginDDT(String email, String password, String exp) {
		try {

			logger.info("**** Starting TC_003_LoginDataDrivenTest ****");

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();

			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();
			

			MyAccountPage maccp = new MyAccountPage(driver);

			boolean targetPage = maccp.isMyAccountPageExists();

			if (exp.equals("Valid")) {
				if (targetPage == true) {
					maccp.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.fail();
				}
			}

			if (exp.equals("Invalid")) {
				if (targetPage == true) {
					maccp.clickLogout();
					Assert.fail();
				} else {

					Assert.assertTrue(true);
				}
			}

		} catch (

		Exception e) {
			Assert.fail();
		}

		logger.info("**** Finished TC_003_LoginDataDrivenTest ****");

	}

}
