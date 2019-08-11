package dataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExcelRead {
	
	WebDriver driver;
	
	


    public void openBrowser(String URL) throws Exception {
    	System.setProperty("webdriver.chrome.driver", "E:\\Automation\\chromedriver.exe");
    	driver = new ChromeDriver();
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	driver.manage().deleteAllCookies();
    	driver.manage().window().maximize();
    	Thread.sleep(5000);
    	driver.get(URL);
    }	
    
    public void closeBrowser() {
    	
    	driver.close();
    	
    }
	public void read_data() throws Exception {
		FileInputStream fil = new FileInputStream("D:\\LoginData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fil);
		XSSFSheet shet = wb.getSheetAt(0);
		XSSFRow row = shet.getRow(0);
		int rowsize = shet.getLastRowNum()+1;
		int colsize = row.getLastCellNum();
		
		System.out.println("Rowsize>>"+rowsize+">> and coloumns size>>"+colsize);
		for(int i = 1;i<rowsize;i++) {
		   	for (int j = 1;j<colsize;j++) {
		   	String value = shet.getRow(i).getCell(j).getCellType().toString();
		  //System.out.println(value);
		   	if(value == "STRING") {
		   		System.out.println(shet.getRow(i).getCell(j).getStringCellValue());}
		   	else if (value == "NUMERIC") {System.out.println(shet.getRow(i).getCell(j).getNumericCellValue());}
		   	}
			}
		FileOutputStream fout = new FileOutputStream("D:\\LoginData.xlsx");
		wb.write(fout);
		fout.close();
		}
	
	public void login_paramerize() throws Exception {
		  
        openBrowser("https://accounts.google.com/ServiceLogin?"  
        				+ "continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1#identifier");		
		FileInputStream fil = new FileInputStream("D:\\LoginData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fil);
		XSSFSheet shet = wb.getSheetAt(0);
		XSSFRow row = shet.getRow(0);
		int rowsize = shet.getLastRowNum()+1;
		int colsize = row.getLastCellNum();
		
		System.out.println("Rowsize>>"+rowsize+">> and coloumns size>>"+colsize);
		for(int i = 1;i<rowsize;i++) {
		   	
		   	String Username = shet.getRow(i).getCell(1).getStringCellValue();
		   	String Password = shet.getRow(i).getCell(2).getStringCellValue();
		   		System.out.print("Username >>"+ Username+"Password>>"+Password);
		   		System.out.println();
		   		Login(Username,Password);
		   	}
		FileOutputStream fout = new FileOutputStream("D:\\LoginData.xlsx");
		wb.write(fout);
		fout.close();
		closeBrowser();    
		
	}
	
	public void Login(String Username,String Password) throws Exception  {
		
//		System.setProperty("webdriver.chrome.driver", "E:\\Automation\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.get("https://accounts.google.com/ServiceLogin?"
//				+ "continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1#identifier");
//		driver.manage().window().maximize();
		WebElement email = driver.findElement(By.xpath("//input[@type = 'email']"));
		if (email.isDisplayed()) {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@type = 'email']")).sendKeys(Username);
		driver.findElement(By.xpath("//span[text() = 'Next']")).click();
		} else {
			driver.findElement(By.xpath("//div[@id = 'profileIdentifier']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[text() = 'Use another account']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@type = 'email']")).sendKeys(Username);
			driver.findElement(By.xpath("//span[text() = 'Next']")).click();	
		}
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@type = 'password']")).sendKeys(Password);
		driver.findElement(By.xpath("//span[text() = 'Next']")).click();
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(20000);	
		WebElement ele = driver.findElement(By.xpath("https://accounts.google.com/SignOutOptions?"
				+ "hl=en&continue=https://mail.google.com/mail&service=mail"));
//		if (ele.isDisplayed()) {System.out.println("Element is displayed"); }
//		else {driver.navigate().refresh();}
		  ele.click();
		System.out.println(driver.getCurrentUrl());
		
		WebElement ele1 = driver.findElement(By.xpath("//a[text() = 'Sign out']"));
		if (ele1.isDisplayed()) {
		driver.findElement(By.xpath("//a[text() = 'Sign out']")).click();}
		else {
		 Alert alr = driver.switchTo().alert();
		 alr.dismiss();
		driver.navigate().refresh();
		 ele.click();
		 ele1.click();
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(10000);
		}
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ExcelRead read = new ExcelRead();
		//read.read_data();
		//read.Login("Bharathbbk365@gmail.com", "bharath0732");
        read.login_paramerize();		
	}
	

}
