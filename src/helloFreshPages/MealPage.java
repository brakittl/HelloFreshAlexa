package helloFreshPages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class MealPage extends ParentPage {

	private List<String> stepList;
	private String[] instructions;
	private int stepCounter = 0;
	private int instructionCounter = 0;
	
	private static final String FINISHED = "finished";
	
	public MealPage(WebDriver driver) {
		super(driver);
	}
	
	public void mealPrepAssistant() {
		setSteps();
		while(!FINISHED.equals(getNextStep())) {
			//If new step mention (instrucitonCounter=0)
				//Read first instruction;
				//Wait for 'next'
			//
			System.out.println(getNextInstruction());
		}
	}
	
	private void setSteps() {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, 1000)");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("window.scrollTo(1000, 5000)");
		
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		
		// fela-12sjl9r
		// fela-1p37q19
		WebElement instructionList = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.className("fela-12sjl9r"));
			}
		});

		List<WebElement> stepObjects = instructionList.findElements(By.className("fela-1p37q19"));

		stepList = new ArrayList<String>();
		for (WebElement step : stepObjects) {
			stepList.add(step.getText());
		}
	}

	private void setInstructions() {
		instructions = stepList.get(stepCounter).substring(2).split("\\. ");
	}
	
	private String getNextInstruction() {
		if(instructionCounter >= instructions.length) {
			instructionCounter = 0;
			getNextStep();
			setInstructions();
			return getNextInstruction();
		}
		return instructions[instructionCounter++]; 
	}
	
	private String getNextStep() {
		if(stepCounter >= stepList.size()) {
			return FINISHED;
		}
		return stepList.get(stepCounter++);
	}
	
	// Go Back Function

}
