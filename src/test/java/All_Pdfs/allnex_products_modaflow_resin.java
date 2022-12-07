package All_Pdfs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class allnex_products_modaflow_resin {

	@Test
	public void extraction() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.knowde.com/stores/allnex/products/modaflow-resin/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// To sign in
		driver.findElement(By.xpath("(//p[normalize-space()='Sign In'])[1]")).click();
		driver.findElement(By.xpath("(//input[@type='email'])[1]")).sendKeys("partap.roy@biznlp.com");
		driver.findElement(By.className("registration-form__submit")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("Bizkonnect@123");
		driver.findElement(By.className("registration-form__submit")).click();
		Thread.sleep(5000);
		// To scroll down
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.className("buttons-box__product-buttons")).click();
		Thread.sleep(3000);
		String linkpdf = driver.findElement(By.xpath("(//iframe[@class='embedded-document__document'])[1]"))
				.getAttribute("src");
		 //System.out.println(linkpdf);
		String split1 = linkpdf.split("v")[0];
		 //System.out.println(split1);
		String split2 = linkpdf.split("pr")[1];
		//System.out.println(split2);
		String finalpdf = (split1 + "pr" + split2);
		// System.out.println(finalpdf);
		driver.navigate().to(finalpdf);
		Thread.sleep(3000);
		driver.navigate().back();
		driver.navigate().back();
//		String clicklnk = Keys.chord(Keys.CONTROL, Keys.ENTER);
//		driver.findElement(By.xpath("(//button[normalize-space()='Safety Data Sheet'])[1]")).sendKeys(clicklnk);
//		String linkpdf2 = driver.findElement(By.xpath("(//iframe[@class='embedded-document__document'])[1]"))
//				.getAttribute("src");
//		System.out.println(linkpdf2);
//		String ssplit1 = linkpdf.split("v")[0];
//		// System.out.println(ssplit1);
//		String ssplit2 = linkpdf.split("pr")[1];
//		// System.out.println(ssplit2);
//		String finalpdf2 = (ssplit1 + "pr" + ssplit2);
//		// System.out.println(finalpdf2);
//		driver.navigate().to(finalpdf2);

		File fileLoc = new File("E:\\softwares\\All_pdf's\\TechnicalDataSheet_allnex_products_modaflow_resin.pdf");
		//File fileLoc1 = new File("E:\\softwares\\All_pdf's\\SafetyDataSheet_allnex_products_modaflow_resin.pdf");
		pdfdownload(finalpdf, fileLoc);
		//pdfdownload(finalpdf2, fileLoc1);
	}

	// @Test //(dependsOnMethods= {"extraction"})
	public static void pdfdownload(String urlLink, File fileLoc) {
		try {

			byte[] buffer = new byte[1024];
			double TotalDownload = 0.00;
			int readbyte = 0; // Stores the number of bytes written in each iteration.
			double percentOfDownload = 0.00;

			URL url = new URL(urlLink);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			double filesize = (double) http.getContentLengthLong();

			BufferedInputStream input = new BufferedInputStream(http.getInputStream());
			FileOutputStream ouputfile = new FileOutputStream(fileLoc);
			BufferedOutputStream bufferOut = new BufferedOutputStream(ouputfile, 1024);
			while ((readbyte = input.read(buffer, 0, 1024)) >= 0) {
				// Writing the content onto the file.
				bufferOut.write(buffer, 0, readbyte);
				// TotalDownload is the total bytes written onto the file.
				TotalDownload += readbyte;
				// Calculating the percentage of download.
				percentOfDownload = (TotalDownload * 100) / filesize;
				// Formatting the percentage up to 2 decimal points.
				String percent = String.format("%.2f", percentOfDownload);
				System.out.println("Downloaded " + percent + "%");
			}
			System.out.println("Your download is now complete.");
			bufferOut.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
