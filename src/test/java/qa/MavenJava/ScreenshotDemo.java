package qa.MavenJava;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
public class ScreenshotDemo {
	public static void main(String[] args) throws InterruptedException {
        //set the location of chrome browser
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
        
        // Initialize browser
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        //navigate to url
        driver.get("https://google.com/");
        Thread.sleep(10);
       //Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        //Copy the file to a location and use try catch block to handle exception
        try {
            FileUtils.copyFile(screenshot, new File("E:\\ss\\homePageScreenshot.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        //closing the webdriver
        //driver.close();
    }
}
