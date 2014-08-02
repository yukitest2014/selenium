package browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Wait {
	private WebDriver driver;
	private int timeout = 10;
	private Wait waiter;
	
	public Wait(WebDriver driver){
		this.driver = driver; 
	}
    
	public void waitforElementPresent(String locator){
		try{
			(new WebDriverWait(driver,timeout)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		}catch(Exception e){
			
		}
	}
	
   public void waitforElementIsEnable(String locator){
	   try{
		   (new WebDriverWait(driver,timeout)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

       }catch (Exception e){
	   
       }
   }

}
