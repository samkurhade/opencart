package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-shipping-firstname']")
	WebElement txtfirstName;

	@FindBy(xpath = "//input[@id='input-shipping-lastname']")
	WebElement txtlastName;

	@FindBy(xpath = "//input[@id='input-shipping-address-1']")
	WebElement txtaddress1;

	@FindBy(xpath = "//input[@id='input-shipping-address-2']")
	WebElement txtaddress2;

	@FindBy(xpath = "//input[@id='input-shipping-city']")
	WebElement txtcity;

	@FindBy(xpath = "//input[@id='input-shipping-postcode']")
	WebElement txtpin;

	@FindBy(xpath = "//select[@id='input-shipping-country']")
	WebElement drpCountry;

	@FindBy(xpath = "//select[@id='input-shipping-zone']")
	WebElement drpState;

	@FindBy(xpath = "//button[@id='button-shipping-address']")
	WebElement btncontinueBillingAddress;

	@FindBy(xpath = "//input[@id='input-shipping-existing']")
	WebElement btncontinueDeliveryAddress;

	@FindBy(xpath = "//textarea[@name='comment']")
	WebElement txtDeliveryMethod;

	@FindBy(xpath = "//input[@id='button-shipping-method']")
	WebElement btncontinueShippingAddress;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkboxTerms;

	@FindBy(xpath = "//select[@id='input-shipping-method']")
	WebElement btncontinueShippingMethod;

	@FindBy(xpath = "//select[@id='input-payment-method']")
	WebElement btncontinuePaymentMethod;

	@FindBy(xpath = "//body[1]/main[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/table[1]/tfoot[1]/tr[3]/td[1]/strong[1]//following::td")
	WebElement lblTotalPrice;

	@FindBy(xpath = "//button[@id='button-confirm']")
	WebElement btnConfOrder;

	@FindBy(xpath = "//*[@id='content']/h1")
	WebElement lblOrderConMsg;

	public void setfirstName(String firstName) {
		txtfirstName.sendKeys(firstName);
	}

	public void setlastName(String lastName) {
		txtlastName.sendKeys(lastName);
	}

	public void setaddress1(String address1) {
		txtaddress1.sendKeys(address1);
	}

	public void setaddress2(String address2) {
		txtaddress2.sendKeys(address2);
	}

	public void setcity(String city) {
		txtcity.sendKeys(city);
	}

	public void setpin(String pin) {
		txtpin.sendKeys(pin);
	}

	public void setCountry(String Country) {
		new Select(drpCountry).selectByVisibleText(Country);
	}

	public void setState(String State) {
		new Select(drpState).selectByVisibleText(State);
	}

	public void clickOnContinueAfterBillingAddress() {
		btncontinueBillingAddress.click();
	}

	public void clickOnContinueAfterDeliveryAddress() {
		btncontinueDeliveryAddress.click();
	}

	public void setDeliveryMethodComment(String deliverymsg) {
		txtDeliveryMethod.sendKeys(deliverymsg);

	}

	public void clickOnContinueAfterDeliveryMethod() {
		btncontinueShippingAddress.click();
	}

	public void selectTermsAndConditions() {
		chkboxTerms.click();
	}

	public void clickOnContinueAfterShippingMethod() {
//		selectByVisibleText("Flat Shipping Rate - $5.00");
		new Select(btncontinueShippingMethod).selectByIndex(1);

	}

	public void clickOnContinueAfterPaymentMethod() {
//		selectByVisibleText("Cash On Delivery");
		new Select(btncontinuePaymentMethod).selectByIndex(1);
	}

	public String getTotalPriceBeforeConfOrder() {
		return lblTotalPrice.getText(); // $207.00

	}

	public void clickOnConfirmOrder() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnConfOrder);

	}

	public boolean isOrderPlaced() throws InterruptedException {
		try {
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			btnConfOrder.click();
			Thread.sleep(3000);
			if (lblOrderConMsg.getText().equals("Your order has been placed!"))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}

	}

}
