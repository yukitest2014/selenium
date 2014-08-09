package browser;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserTest {
  private WebDriver driver;
  private Browser browser = null;
  private String projectpath = System.getProperty("user.dir");
  
  private ParseProperties data;
  private ParseProperties locator;
  
  private Wait wait;
  
  @Parameters({"data","locator"})  
  @BeforeMethod(groups="browser")
  public void inital(@Optional("data") String da,@Optional("locator") String loc) {   //默认Chrome启动 
	  	  data = new ParseProperties(projectpath+da);
	  	  locator = new ParseProperties(projectpath+loc);
	  	  System.out.println(data);
	  	  System.out.println(locator);
		  browser = new Browser(DriverType.firefox); 
		  driver = browser.driver; 
		  Wait wait = new Wait(driver);
  }

  @Test(groups="start126")
	public void start126(){		
	  	//访问126邮箱
		driver.get(data.getValue("url"));
		
		//登录126
		WebElement username = driver.findElement(By.xpath(locator.getValue("login")));
		username.clear();
		username.sendKeys(data.getValue("username"));	
		
		WebElement pwd = driver.findElement(By.xpath(locator.getValue("pwd")));
		pwd.clear();
		pwd.sendKeys(data.getValue("password"));
		
		driver.findElement(By.xpath(locator.getValue("loginbotton"))).click();	
		
		//等待首页加载完成
		System.out.println(locator.getValue("homepage"));
		//wait.waitforElementPresent(locator.getValue("homepage"));
		new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getValue("homepage"))));

		//获取收件箱未读邮件的数量
		WebElement msgbox = driver.findElement(By.xpath(locator.getValue("msgbox")));
		String msg=msgbox.getText(); 
		msg = msgbox.getText().replace("(","").replace(")","");
		System.out.println(msg);
		
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
       // driver.findElement(By.xpath(locator.getValue("index"))).click();
        
		//点击未读邮件链接
        driver.findElement(By.xpath(locator.getValue("unread"))).click();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);    
        
        int pagetotal = 0;
        int sum =0;
        WebElement page = driver.findElement(By.xpath(locator.getValue("page")));
        pagetotal = Integer.valueOf(page.getText().split("/")[1]);
        System.out.println(pagetotal);
        List<WebElement> noread = driver.findElements(By.xpath(locator.getValue("noread")));       
        //System.out.println(noread.size());
        
        while(pagetotal>=1){
        	
            sum = sum + noread.size();
			System.out.println(sum);
			try{
				driver.findElement(By.xpath(locator.getValue("nextBtn"))).click();
				
			}catch(Exception e){
				break;
			}
			pagetotal--;
			
		}
        sum = sum + noread.size();
        System.out.println(sum);
    	//Assert.assertEquals(msg, sum);
		}
  /* ---- yuki 2014 08-08 ----*/
  @AfterMethod(groups="browser")
  public void afterClass() {
	  driver.quit();
  }

}
  