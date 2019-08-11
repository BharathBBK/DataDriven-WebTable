package webTable;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WebTableDriven<WebElements> {
    WebDriver driver;
	
	
	@Test
	public void getRows() throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver", "E:\\Automation\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		
		Thread.sleep(5000);
		List<WebElement> getRow = driver.findElements(By.xpath("//table[@id='customers']/tbody/tr/td[1]"));
		List<WebElement> getcol = driver.findElements(By.xpath("//table[@id='customers']/tbody/tr/th"));
		System.out.println("No Of rows"+getRow.size());
        System.out.println("No of coloumns"+getcol.size());
        
        for (int i = 2;i<=getRow.size()+1;i++) {
        	for (int j = 1;j<=getcol.size();j++) {
        //System.out.println(i+">>>>"+j+">> "+ driver.findElement(By.xpath("//table[@id='customers']/tbody/tr["+i+"]/td["+j+"]")).getText());
        String getText = driver.findElement(By.xpath("//table[@id='customers']/tbody/tr["+i+"]/td["+j+"]")).getText();
        //System.out.println(getText);	
        if (getText.equals("Maria Anders")) {
        	System.out.println("matched"+getText);
        	break;
        }
        
        	}       	
        }
        
		driver.close();
	}
}
