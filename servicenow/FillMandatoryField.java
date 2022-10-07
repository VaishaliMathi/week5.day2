package week5.day2.servicenow;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class FillMandatoryField {

	public static void main(String[] args) throws InterruptedException {
	//	1. Launch ServiceNow application
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev94743.service-now.com/");
		
	//	2. Login with valid credentials 
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	//	3. Enter Knowledge  in filter navigator and press enter
		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(40);
		shadow.findElementByXPath("//div[@aria-expanded='false']").click();
		shadow.findElementByXPath("//input[contains(@class,'navigation-filter keyboard')]").sendKeys("knowledge");
		shadow.findElementByXPath("//mark[text()='Knowledge']").click();
		
	//	4. Create new Article
		WebElement frame = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//button[@role='link']")).click();
		
	//	5.Select knowledge base as IT and category as IT- java and Click Ok
		driver.findElement(By.xpath("(//button[contains(@id,'lookup.kb')])[1]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstwindow=new ArrayList<String>(windowHandles);
		driver.switchTo().window(lstwindow.get(1));
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		driver.switchTo().window(lstwindow.get(0));
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[contains(@id,'lookup.kb')])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='IT']")).click();
		driver.findElement(By.xpath("//span[text()='Java']")).click();
		driver.findElement(By.xpath("//button[text()='OK']")).click();
	
	//	6.Update the other mandatory fields
		driver.findElement(By.xpath("//input[@id='kb_knowledge.short_description']")).sendKeys("Created via Autoamtion");
		
	//	7.Select the submit option
		driver.findElement(By.xpath("(//button[text()='Submit'])[2]")).click();

	}

}
