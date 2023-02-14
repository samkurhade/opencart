package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_004_SearchProductTest extends BaseClass {

	@Test
	void test_searchProduct() {

		logger.info(" Starting TC_004_SearchProductTest ");

		try {
			HomePage hp = new HomePage(driver);
			hp.productSearch("mac");
			hp.clickSearch();

			SearchPage sp = new SearchPage(driver);
//			sp.isProductExists("MacBook");

			Assert.assertEquals(sp.isProductExists("iMac"), true);

		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail();
		}
		logger.info(" Finished TC_004_SearchProductTest ");
	}
}
