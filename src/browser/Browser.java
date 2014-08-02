package browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Browser<publlic> {
	  public WebDriver driver = null;
	  private FirefoxProfile firefoxprofile = null;
	  private static DesiredCapabilities caps = null;
	  
      public Browser(DriverType drivertype){
    	  switch(drivertype){
    	  case firefox:  
    		  firefoxprofile = new FirefoxProfile();
    		  driver = new FirefoxDriver(firefoxprofile);
    		  driver.manage().window().maximize();
    		  break;
    	  case chrome:
    		  caps = DesiredCapabilities.chrome();
    		  driver = new ChromeDriver(caps);
    		  driver.manage().window().maximize();
    		  break;
    	  case ie:
    		  caps = DesiredCapabilities.internetExplorer();
    		  driver = new InternetExplorerDriver(caps);
    		  driver.manage().window().maximize();
    		  break;
    	  }
    	    	  
      }
}
