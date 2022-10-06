package week5.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class NewCaller {

	public static void main(String[] args) {
	//	1. Launch ServiceNow application
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev94743.service-now.com/");
	//	2. Login with valid credential
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	//	3. Click-All and Enter Callers in filter navigator and press enter
		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(40);
		shadow.findElementByXPath("//div[@aria-expanded='false']").click();
		shadow.findElementByXPath("//input[contains(@class,'navigation-filter keyboard')]").sendKeys("callers");
		shadow.findElementByXPath("//mark[text()='Callers']").click();

	//	4. Create new Caller by filling all the fields and click 'Submit'.
		WebElement frame = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//button[text()='New']")).click();
		driver.findElement(By.xpath("//input[@id='sys_user.first_name']")).sendKeys("vaishali");
		driver.findElement(By.xpath("//input[@id='sys_user.last_name']")).sendKeys("mathi");
		driver.findElement(By.xpath("//input[@id='sys_user.title']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@id='sys_user.email']")).sendKeys("kerenkavi@gmail.com");
		driver.findElement(By.xpath("(//button[text()='Submit'])[2]")).click();
		
	//	5. Search and verify the newly created Caller"
		Actions act=new Actions(driver);
		driver.findElement(By.xpath("(//input[@placeholder='Search'])[1]")).sendKeys("vaishali");
		act.sendKeys(Keys.ENTER);
		String text = driver.findElement(By.xpath("//table[@id='sys_user_table']//tbody//tr[1]//td[4]")).getText();
		System.out.println(text);
		
	}

}
