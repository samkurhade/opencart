package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);

	}

	// Elements
	@FindBy(xpath = "//div[@class='nav float-end']//div[@class='dropdown']")
	WebElement lnkMyAccount;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;

	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	@FindBy(name = "search")
	WebElement inputSearch;
	
	@FindBy(xpath = "//div[@id='search']//button[@type='button']")
	WebElement btnSearch;

	// Action Methods

	public void clickMyAccount() {
		lnkMyAccount.click();
	}

	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
	
	public void productSearch(String pName) {
		inputSearch.sendKeys(pName);
	}
	
	public void clickSearch() {
		btnSearch.click();
	}
}
