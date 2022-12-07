package qa.MavenJava;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class sampleEndToEnd {

	@Test
	public void EndToEnd() throws InterruptedException
	{
		
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.knowde.com/b/markets-paints-coatings/products");
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	      
//		driver.findElement(By.xpath("(//div[contains(text(),'Sign In')])[1]")).click();
//		driver.findElement(By.id("email")).sendKeys("partap.roy@biznlp.com");
//		Thread.sleep(10000);
//		//driver.findElement(By.className("fresnel-container")).click();
//		driver.findElement(By.cssSelector("button[type='submit'] div[class='css-opq449 e1x6k1cu0']")).click();
//		driver.findElement(By.id("password")).sendKeys("Bizkonnect@123");
//		driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[@class='css-opq449 e1x6k1cu0'])[8]")).click();
		Thread.sleep(5000);
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("window.scrollBy(0,400)");
		
		WebElement middledriver = driver.findElement(By.xpath("(//knowde-product-summary[@class='ng-star-inserted'])[1]"));
		//WebElement innermiddledriver = middledriver.findElement(By.className("blocks-columns"));
		List<WebElement> innermiddledrivers = middledriver.findElements(By.tagName("div"));
		
		List<String[]> list = new ArrayList<>();
	       String strChemical;
	       //String strResponseReason;
	    
	       
	       for(WebElement ele : innermiddledrivers) {
	           System.out.println(ele.getText());
	         if(StringUtils.contains(ele.getText(), "Chemical Family")) {
	               strChemical = ele.getText();
		
	}
}
	}
}
