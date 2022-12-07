package qa.MavenJava;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class DemoScrap1 {

	
	@Test
	public void login() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.knowde.com/stores/invista/documents/200470");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("partap.roy@biznlp.com");
		driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Bizkonnect@123");
		driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		// to scroll window by hit and trial coordinate
		js.executeScript("window.scrollBy(0,200)");
		// for checking number of frames present in web page
		System.out.println(driver.findElements(By.tagName("iframe")).size());
		driver.switchTo().frame(0);
		Thread.sleep(5000);
		//driver.switchTo().frame(2);
		//driver.findElement(By.xpath("/html/body/pdf-viewer//viewer-toolbar//div/div[3]/viewer-download-controls//cr-icon-button[1]")).click();
		//driver.findElement(By.xpath("//cr-icon-button[@iron-iron='cr:file-download']")).click();
		//driver.findElement(By.id("print")).click();
		Actions a = new Actions(driver);
		//moves to specific element
		a.moveToElement(driver.findElement(By.xpath("//cr-icon-button[@iron-iron='cr:file-download']"))).click().build().perform();
//		
	}
}
