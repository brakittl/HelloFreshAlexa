package helloFreshPages;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class MenuPage extends ParentPage{
	//TODO 
	//Go to next weeks meals
	
	
	@FindBy(xpath = "//*[@id=\"my-deliveries-fragment.37b3288d-bf76-4c63-b235-27528a5218fb\"]/section/section[2]/section[1]/div/div[3]/div/div/div/div[2]/div/div[1]/div/span")
	private WebElement clickToViewMenu; 
	
	public MenuPage(WebDriver driver) {
		super(driver);
	}
	
	public void viewMenu() {
		  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(30, TimeUnit.SECONDS)
			       .pollingEvery(5, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);
		
		
		  clickToViewMenu = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       return driver.findElement(By.className("fela-ikk4u6 fela-1rtnddl"));
			     }
			   });
		  
		clickToViewMenu.click();
	}
	
	public MealPage selectMealToCook() {
		  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(30, TimeUnit.SECONDS)
			       .pollingEvery(5, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);
		
		  
			Scanner reader = new Scanner(System.in);  // Reading from System.in
		
			while(true) {
				System.out.println("What meal do you want to cook: ");
//				String menuItem = reader.nextLine();
				String menuItem = "burger";
				
				
				  WebElement retrievedMeal = wait.until(new Function<WebDriver, WebElement>() {
					     public WebElement apply(WebDriver driver) {
//					       return driver.findElement(By.className("fela-vjy7zp"));
					       return driver.findElement(By.cssSelector("a[href*='" + menuItem + "']"));			     }
					   });
				  
				  if(retrievedMeal.getText() != null) {
					  retrievedMeal.click();
					  return new MealPage(driver);
				  }else {
					  System.out.println("That item was not delivered with your box");
				  }
			}
	}

}
