package week5.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class OderingMobile {

	public static void main(String[] args) throws InterruptedException {
		//1. Launch ServiceNow application
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev94743.service-now.com/");
		
	//	2. Login with valid credentials username as admin and password as India@123
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	//	3. Click-All Enter Service catalog in filter navigator and press enter 
		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(20);
		shadow.findElementByXPath("//div[@aria-expanded='false']").click();
		Thread.sleep(2000);
	//	shadow.findElementByXPath("//input[contains(@class,'navigation-filter keyboard')]").sendKeys("service");
		shadow.findElementByXPath("//span[text()='Service Catalog']").click();
		
	//	4. Click on  mobiles
		Thread.sleep(2000);
	//	driver.findElement(By.xpath("//div[@id='body_com.glideapp.servicecatalog.RenderCategory_0166498067653108']/div[1]/a[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]/span[1]/h2[1]")).click();
		WebElement frame = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//h2[contains(@text(),'Mobile')]")).click();
		driver.switchTo().defaultContent();
		
	//	5.Select Apple iphone6s
		driver.findElement(By.xpath("//strong[text()='Apple iPhone 13']")).click();
		
	//	6.Update color field to rose gold and storage field to 128GB
		Thread.sleep(5000);
		driver.findElement(By.xpath("//label[text()='Midnight']")).click();	
		driver.findElement(By.xpath("(//select[contains(@class,'form-control')])[1]")).click();
		//h2[contains(@style,'font-size:larger; font-weight:normal')]
	//	7.Select  Order now option
		WebElement dropdown = driver.findElement(By.xpath("//button[@id='oi_order_now_button']"));
		Select source=new Select(dropdown);
		source.selectByIndex(1);
		driver.findElement(By.xpath("(//label[@class='radio-label'])[2]")).click();
		
	//	8.Verify order is placed and copy the request number"
		String text = driver.findElement(By.xpath("//span[@aria-live='assertive']")).getText();
		System.out.println(text);
		String text2 = driver.findElement(By.xpath("//a[@id='requesturl']")).getText();
		System.out.println("The request number is "+ text2);
	}

}
