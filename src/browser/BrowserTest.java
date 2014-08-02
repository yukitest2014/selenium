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
  public void inital(@Optional("data") String da,@Optional("locator") String loc) {   //Ĭ��Chrome���� 
	  	  data = new ParseProperties(projectpath+da);
	  	  locator = new ParseProperties(projectpath+loc);
	  	  System.out.println(data);
	  	  System.out.println(locator);
		  browser = new Browser(DriverType.chrome); 
		  driver = browser.driver; 
		  Wait wait = new Wait(driver);
  }

  @Test(groups="start126")
	public void start126(){		
	  	//����126����
		driver.get(data.getValue("url"));
		
		//��¼126
		WebElement username = driver.findElement(By.xpath(locator.getValue("login")));
		username.clear();
		username.sendKeys(data.getValue("username"));	
		
		WebElement pwd = driver.findElement(By.xpath(locator.getValue("pwd")));
		pwd.clear();
		pwd.sendKeys(data.getValue("password"));
		
		driver.findElement(By.xpath(locator.getValue("loginbotton"))).click();	
		
		//�ȴ���ҳ�������
		System.out.println(locator.getValue("homepage"));
		//wait.waitforElementPresent(locator.getValue("homepage"));
		new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.getValue("homepage"))));

		//��ȡ�ռ���δ���ʼ�������
		WebElement msgbox = driver.findElement(By.xpath(locator.getValue("msgbox")));
		String msg=msgbox.getText(); 
		msg = msgbox.getText().replace("(","").replace(")","");
		System.out.println(msg);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
       // driver.findElement(By.xpath(locator.getValue("index"))).click();
        
		//���δ���ʼ�����
        driver.findElement(By.xpath(locator.getValue("unread"))).click();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
             
        List<WebElement> noread = driver.findElements(By.xpath(locator.getValue("noread")));       
        System.out.println(noread.size());
        
        int pagetotal = 0;
        int sum =0;
        WebElement page = driver.findElement(By.xpath(locator.getValue("page")));
        pagetotal = Integer.valueOf(page.getText().split("/")[1]);
        System.out.println(pagetotal);
        
        
        while(pagetotal>=1){
        	sum += noread.size(); 
        	try{
        		driver.findElement(By.xpath(locator.getValue("next"))).click();
        	}catch(Exception e){
        		break;
        	}
        	pagetotal--;
        	System.out.println(sum);      	
        }
        System.out.println(sum);
    	Assert.assertEquals(msg, sum);
		}

  @AfterMethod(groups="browser")
  public void afterClass() {
	  //driver.quit();
  }

}
  