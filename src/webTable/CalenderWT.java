package webTable;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;





public class CalenderWT {
   
	 WebDriver driver;
	 
	 //Columns :: //*[@id="ui"]/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div[2]/div[1]/div

	//Rows :: //*[@id="ui"]/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/div[1]
	 @Test
	 public void login() throws Exception {
		  System.setProperty("webdriver.chrome.driver", "E:\\Automation\\chromedriver.exe");
			
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();
	        
	        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        
	        driver.get("https://www.freecrm.com/index.html");

				Thread.sleep(10000);
				 String title = driver.getTitle();
				 System.out.println(title);
				 driver.findElement(By.xpath("//a[@class='btn btn-primary btn-xs-2 btn-shadow btn-rect btn-icon btn-icon-left' ]")).click();
				 Thread.sleep(5000);
				 driver.findElement(By.name("email")).sendKeys("bharathbbk365@gmail.com");
				 //"bharathbbk365@gmail.com" or "Bharath0732"
	           driver.findElement(By.name("password")).sendKeys("Bharath0732");
				 WebElement loginBtn =
						 driver.findElement(By.xpath("//div[text()='Login']"));
						 JavascriptExecutor js = (JavascriptExecutor)driver;
						 js.executeScript("arguments[0].click();", loginBtn);
						 String title1 = driver.getTitle();
						 System.out.println(title1);			
				         Thread.sleep(10000);
				//driver.findElement(By.xpath("//div[@class = 'ui basic button floating item dropdown']")).click();
				//Thread.sleep(5000);
				System.out.println("LogOut is going to happend" + driver.getCurrentUrl());
		       driver.findElement(By.xpath("//span[text() = 'Calendar']")).click();
List<WebElement> getRow = driver.findElements(By.xpath("//div[@id='ui']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/div[1]"));
List<WebElement> getcol = driver.findElements(By.xpath("//div[@id='ui']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div[2]/div[1]/div"));
       System.out.println(getRow.size() +">>>>"+getcol.size());

List<WebElement> getRD = driver.findElements(By.xpath("//div[@id='ui']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[4]"));       
WebElement dat = driver.findElement(By.xpath("//div[@id='ui']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div[2]/div[2]/div/div[7]"));
       String a = dat.getText();
       Integer i = Integer.parseInt(a);
       if (i == 04) {System.out.println("matched");}                                
     
       
       for(int x = 2;x<=getRow.size()+1;x++) {
    	 for (int y = 1;y<getcol.size()+1;y++) {
    		
           WebElement getEachDay = driver.findElement(By.xpath("//div[@id='ui']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div["+x+"]/div[2]/div/div["+y+"]"));		 
    	   System.out.println(x+">>>"+y+">>"+getEachDay.getText());
    	   Integer ch = Integer.parseInt(getEachDay.getText());
    	   if(ch == 31) {
    		   Thread.sleep(5000);
    		   getEachDay.click();
    		   System.out.println("Loop Entered"+driver.getCurrentUrl());
    		   break;   
    	   }
    	   
    	 } 
    	 break;
     }   
       Thread.sleep(3000);       
       driver.findElement(By.xpath("//input[@name = 'title']")).sendKeys("Created by automation");
       driver.findElement(By.xpath("//button[text() = 'Save']")).click();
       System.out.println("eventCreated" + driver.getCurrentUrl());
	 }	 
}
