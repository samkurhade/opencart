package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//*[@id='content']//div//img")
	List<WebElement> searchProducts;
	
	@FindBy(xpath = "//input[@id='input-quantity']")
	WebElement txtQuantity;
	
	@FindBy(xpath = "//button[@id='button-cart']")
	WebElement btnToAddCart;
	
	@FindBy(xpath="//div[contains(text(),'Success: You have added')]")
	WebElement cnfMsg;


	
	public boolean isProductExists(String productName) {
		boolean flag = false;

		for (WebElement product : searchProducts) {
			if (product.getAttribute("title").equals(productName)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public void selectProduct(String productName)
	{
		for(WebElement product:searchProducts)
		{				
			if(product.getAttribute("title").equals(productName))
			{
				product.click();
			}
		}
	
	}
	
	public void setQuantity(String qty)
	{
		txtQuantity.clear();
		txtQuantity.sendKeys(qty);
	}
	
	public void addToCart()
	{
		btnToAddCart.click();
	}
	
	public boolean checkConfMsg()
	{
		try
		{
		return cnfMsg.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
