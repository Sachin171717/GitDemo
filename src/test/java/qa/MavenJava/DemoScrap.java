package qa.MavenJava;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.opencsv.CSVWriter;

public class DemoScrap {

//	@BeforeTest
//	public void BeforeTest()
//	{
//		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://www.knowde.com/b/markets-paints-coatings/products");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//	}
//	
//	@Test
//	public void Pagination() throws InterruptedException
//	{
//		WebDriver driver = new ChromeDriver();
//		List<WebElement> pagination = driver.findElements(By.xpath("//nav[@class='e1hg47tz0 css-15lj04w ev4fpqn0']"));
//		int pgSize = pagination.size();
//		for (int j = 1; j < pgSize; j++) {
//		Thread.sleep(1000);
//		WebElement pagei = driver.findElement(By.xpath("//nav[@class='e1hg47tz0 css-15lj04w ev4fpqn0'][" + j + "]"));
//		pagei.click();
//		}
	//}
		
	@Test
	public void product1() throws InterruptedException, IOException
	{
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.knowde.com/b/markets-paints-coatings/products");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//driver.findElement(By.xpath("(//a[normalize-space()='Brands'])[1]")).click();
		//Thread.sleep(3000);
		//js.executeScript("window.scrollBy(0,400)");
		//driver.findElement(By.xpath("(//a[normalize-space()='Products'])[1]")).click();
		
		// to scroll window by hit and trial coordinate
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[@class='css-opq449 e1x6k1cu0'])[5]")).click();
		Thread.sleep(5000);
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		// to scroll window by hit and trial coordinate
		js1.executeScript("window.scrollBy(0,500)");
		Thread.sleep(5000);
		System.out.println("Chemical Name:"+ driver.findElement(By.xpath("//span[text()='2-Methylpentamethylenediamine']")).getText());
		System.out.println("Chemical Family:"+ driver.findElement(By.xpath("//span[text()='Diamines']")).getText());
		System.out.println("Function:"+ driver.findElement(By.xpath("//span[text()='Corrosion Inhibitor']"))+ driver.findElement(By.xpath("//span[text()='Buffers & pH Stabilizer']")).getText());
		//String cname= ChemicalName.getText();
		//System.out.println("Chemical Name:"+ cname);
		//iJob.setChemicalName(ChemicalName);
		js.executeScript("window.scrollBy(0,900)");
		WebElement middledriver = driver.findElement(By.className("storefront-container"));
		WebElement innermiddledriver = middledriver.findElement(By.className("blocks-columns"));
		List<WebElement> innermiddledrivers = innermiddledriver.findElements(By.tagName("div"));
		
		List<String[]> list = new ArrayList<>();
	       String strChemical;
	       //String strResponseReason;       
	       for(WebElement ele : innermiddledrivers) {
	           System.out.println(ele.getText());
	         if(StringUtils.contains(ele.getText(), "Chemical Family")) {
	               strChemical = ele.getText();
	               
	               
	               String line2[] = {strChemical," ",};
	               //String line2[] = {strCountry, strResponseReason, strreferenceNumber, responseMessage, amount};
	            list.add(line2);
//	           }
	        
	       }
	       Thread.sleep(2000);

	       // Writing data to a csv file
	     // String line1[] = {"country", "referenceNumber", "responseReason", "responseStatus", "responseMessage", "amount"};
	     	     
	      try (CSVWriter writer = new CSVWriter(new FileWriter("E://Output.csv"))) {
	         writer.writeAll(list);	          
	      }
	      System.out.println("Data Captured");
	       	         
	      }
	               
	               
	               
	}
	
	}
	
