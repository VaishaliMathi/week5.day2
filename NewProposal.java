package week5.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class NewProposal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
	//	3. Click All and Enter Proposal in filter navigator and press enter
		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(40);
		shadow.findElementByXPath("//div[@aria-expanded='false']").click();
		shadow.findElementByXPath("//input[contains(@class,'navigation-filter keyboard')]").sendKeys("Proposal");
		shadow.findElementByXPath("//span[text()='My ']").click();
	//	4. Click- new  and  fill mandatory fields and click 'Submit' Button.
		WebElement frame = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//button[text()='New']")).click();
		String text = driver.findElement(By.xpath("//input[@id='std_change_proposal.number']")).getAttribute("value");
		System.out.println("proposal number:"+text);
		driver.findElement(By.xpath("//input[@id='std_change_proposal.short_description']")).sendKeys("Created via Automation");
		driver.findElement(By.xpath("(//span[text()='Change Request values'])[1]")).click();
		WebElement element = driver.findElement(By.xpath("//input[@aria-label='Short description']"));
		element.clear();
		element.sendKeys("Created via Automation");
		driver.findElement(By.xpath("//button[@id='sysverb_insert_bottom']")).click();
		driver.switchTo().defaultContent();
	//	5. Verify the successful creation of new Proposal
		String text2 = driver.findElement(By.xpath("//table[@id='std_change_proposal_table']//tbody//tr[2]//td[3]")).getText();
		if(text2==text) {
			System.out.println("New proposal created successfully");
		}
		else {
			System.out.println("Not created");
		}
		
	}

}
