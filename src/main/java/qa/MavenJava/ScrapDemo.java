package qa.MavenJava;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.opencsv.CSVWriter;

public class ScrapDemo {

	@Test
	public void Scrapdemo() throws IOException, InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.knowde.com/b/markets-paints-coatings/products");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//driver.findElement(By.xpath("(//a[normalize-space()='Brands'])[1]")).click();
		//Thread.sleep(3000);
		//js.executeScript("window.scrollBy(0,400)");
		//driver.findElement(By.xpath("(//a[normalize-space()='Products'])[1]")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		// to scroll window by hit and trial coordinate
		js.executeScript("window.scrollBy(0,700)");
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
	       String chemicalFamily;
	       String chemicalName;	       
	       for(WebElement ele : innermiddledrivers) {
	           System.out.println(ele.getText());
	         if(StringUtils.contains(ele.getText(), "Chemical Family")) {
	               chemicalFamily = ele.getText();
	               //System.out.println(chemicalFamily);
	         }
	         else if (StringUtils.contains(ele.getText(), "Chemical Name")) {
	               chemicalName = ele.getText();
	               System.out.println(chemicalName);
	               
	          CSVWriter writer = new CSVWriter(new FileWriter("E://Data.csv"));     
	          String line1[] = {"Name of Family", "Name of Chemical"};
	          String line2[] = {"Chemical Family", "Chemical Name"};
	          List list1 = new ArrayList();
		      list1.add(line1);
		      list1.add(line2);
		      
		      writer.writeAll(list1);
		      writer.flush();
		     
	}
	         System.out.println("Data Scrapped");
	
}
	}
}
