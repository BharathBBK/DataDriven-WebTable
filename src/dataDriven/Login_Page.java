package dataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login_Page {
	
	WebDriver driver ;
//	public static void main(String[] args) throws InterruptedException {
//		// TODO Auto-generated method stub
//		//System.setProperty("webdriver.gecko.driver", "E:\\selenium-jar\\firefox\\geckodriver.exe");
//		
//		Login_Page lp = new Login_Page();
//		lp.open_Browser();
//		lp.crmLogin("bharathbbk365@gmail.com", "Bharath0732");
//		lp.crmLogin("bharathbbk365@gmail.com", "Bharath0732");
//	}
	
	@BeforeTest
	public void open_Browser() throws InterruptedException {
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
	}
	
	
	@Test(dataProvider = "getdata")
	public void crmLogin(String Username,String Password) throws InterruptedException {
    	 driver.findElement(By.name("email")).sendKeys(Username);
			 //"bharathbbk365@gmail.com" or "Bharath0732"
           driver.findElement(By.name("password")).sendKeys(Password);
			 WebElement loginBtn =
					 driver.findElement(By.xpath("//div[text()='Login']"));
					 JavascriptExecutor js = (JavascriptExecutor)driver;
					 js.executeScript("arguments[0].click();", loginBtn);
					 String title1 = driver.getTitle();
					 System.out.println(title1);			
			         Thread.sleep(10000);
			driver.findElement(By.xpath("//div[@class = 'ui basic button floating item dropdown']")).click();
			Thread.sleep(5000);
			System.out.println("LogOut is going to happend" + driver.getCurrentUrl());
			driver.findElement(By.xpath("//span[text()='Log Out']")).click();
			Thread.sleep(10000);
			//driver.close();
	
//	System.out.println(Username + Password);
	
	}

	@AfterTest
	public void close_Browser() {driver.close();}
	
	@DataProvider
	public Object[][] getdata() {
	
	Object[][] data = 	utily.getTestData();
		return data;
	}
	//*[@id="customers"]/tbody/tr[2]/td[1]
	
	
	
    // @Test
      public void data_Driven_Excel() throws Exception {
    	  FileInputStream fip = new FileInputStream("D:\\LoginData.xlsx");
    	  XSSFWorkbook wb = new XSSFWorkbook(fip);
    	  XSSFSheet sheet1 = wb.getSheetAt(0);
    	  XSSFRow rw = sheet1.getRow(0);
    	  int RowSize = sheet1.getLastRowNum();
    	  System.out.println(sheet1.getSheetName());
    	  System.out.println(RowSize+"--Row size along  with last cell>>>"+rw.getLastCellNum());
    	  
    	  open_Browser();
    	  
    	  for (int i = 1;i<RowSize+1;i++) {		    		  
    		  
    		  String UserName = sheet1.getRow(i).getCell(1).getStringCellValue();
    		  String Password = sheet1.getRow(i).getCell(2).getStringCellValue();
    		  crmLogin(UserName,Password);
    		  Thread.sleep(5000);
    		  int itera =(int) sheet1.getRow(i).getCell(3).getNumericCellValue();
    		  System.out.println(itera);
    		  
    	  }
    	  
    	  close_Browser();
    	  FileOutputStream fop = new FileOutputStream("D:\\LoginData.xlsx");
    	  wb.write(fop);
          fop.close();    	  
      }
  

}
