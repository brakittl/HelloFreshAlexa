package main;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import helloFreshPages.HomePage;
import helloFreshPages.MealPage;
import helloFreshPages.MenuPage;

public class HelloFreshController {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.hellofresh.com/");
		
		
		HomePage homePage = new HomePage(driver);
		MenuPage menuPage = homePage.performLogin();
	
//		menuPage.viewMenu();
		

		System.out.println("select menu item");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		
		MealPage mealPage = menuPage.selectMealToCook();
		
		
		
		mealPage.mealPrepAssistant();
//		driver.quit();
			
		System.out.println("SUCCESSSSS!!!!!!!!!!!");
	}
}
