package helloFreshPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{

	private String email = "brakittl@umich.edu";
	private String password = "Skits19762";
	
	@FindBy(id="login-button")
	private WebElement loginButton;	
	@FindBy(id="email-input")
	private WebElement emailInput;
	@FindBy(id="password-input")
	private WebElement passwordInput;
	@FindBy(id="submit-login-button")
	private WebElement submitLoginButton;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public MenuPage performLogin() {
		clickLogin();
		inputLoginInformation();
		return new MenuPage(driver);
	}
	
	private void clickLogin() {
		loginButton.click();
	}
	
	private void inputLoginInformation() {
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		submitLoginButton.click();
	}

}
