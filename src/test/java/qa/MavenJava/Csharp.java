package qa.MavenJava;

import com.ibm.icu.util.StringTokenizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.model.RecordStream;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.restassured.internal.support.FileReader;
import io.restassured.mapper.ObjectMapper;

public class Csharp {
	// @Parameters({"URL"})
	@Test
	public void openWeb() throws InterruptedException, IOException, ParseException {
		Gson gson = new Gson();
		File file = new File("E://softwares//urls.json");
		FileInputStream inputStream = new FileInputStream(file);

		String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		DynamicURL dynamicURL = gson.fromJson(text, DynamicURL.class);
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		int index = 1;
		for (String url : dynamicURL.URLs) {
			System.out.println(url);
			driver.get(url);
			beforetest(driver, index);
			index++;
		}
	}

	public void beforetest(WebDriver driver, int index) throws InterruptedException, IOException, ParseException {

		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");

		// driver.get("https://www.knowde.com/stores/invista/products/dytek-a/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));

		if (index == 1) {
			// To sign in
			driver.findElement(By.xpath("(//p[normalize-space()='Sign In'])[1]")).click();
			driver.findElement(By.xpath("(//input[@type='email'])[1]")).sendKeys("partap.roy@biznlp.com");
			driver.findElement(By.className("registration-form__submit")).click();
			Thread.sleep(2000);
			// w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='password'])[1]")));
			driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys("Bizkonnect@123");
			// w.until(ExpectedConditions.visibilityOfElementLocated(By.className("registration-form__submit")));
			driver.findElement(By.className("registration-form__submit")).click();
		}

		Thread.sleep(2000);
		// w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//knowde-product-summary[@class='ng-star-inserted'])[1]")));
		// To scroll down
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		WebElement scroll1 = driver.findElement(By.xpath("(//knowde-product-summary[@class='ng-star-inserted'])[1]"));
		// js.executeScript("window.scrollBy(0,500)");
		js1.executeScript("arguments[0].scrollIntoView();", scroll1);

		// To extract few data details - upper section - [First Extraction]

		WebElement middledriver = driver
				.findElement(By.xpath("(//knowde-product-summary[@class='ng-star-inserted'])[1]"));
		List<WebElement> innermiddledrivers = middledriver.findElements(By.tagName("div"));

		DataExtraction dataExtraction = new DataExtraction();
		String[] innerlist = new String[2];
		String strChemical = "";

		for (WebElement ele : innermiddledrivers) {
			// Thread.sleep(3000);
			if (ele.getText() != null) {
				innerlist = ele.getText().toString().split(":");
				String LabelValue = innerlist[0].toString();

				if (LabelValue.equalsIgnoreCase("Chemical Name"))
					dataExtraction.chemicalName = innerlist[1];
				if (LabelValue.equalsIgnoreCase("Function"))
					dataExtraction.function = innerlist[1];
				if (LabelValue.equalsIgnoreCase("CAS Number"))
					dataExtraction.CASNumber = innerlist[1];
				if (LabelValue.equalsIgnoreCase("Chemical Family"))
					dataExtraction.chemicalFamily = innerlist[1];
				if (LabelValue.equalsIgnoreCase("INCI Name"))
					dataExtraction.inciName = innerlist[1];
				if (LabelValue.equalsIgnoreCase("Ingredient Origin"))
					dataExtraction.ingredientOrigin = innerlist[1];
				if (LabelValue.equalsIgnoreCase("Labeling Claims"))
					dataExtraction.labelingClaims = innerlist[1];
				if (LabelValue.equalsIgnoreCase("Polymer Name"))
					dataExtraction.PolymerName = innerlist[1];
				if (LabelValue.equalsIgnoreCase("Compatible Substrates & Surfaces"))
					dataExtraction.CompatibleSubstratesandSurfaces = innerlist[1];
				if (LabelValue.equalsIgnoreCase("End Uses"))
					dataExtraction.EndUses = innerlist[1];
				if (LabelValue.equalsIgnoreCase("Features"))
					dataExtraction.Features = innerlist[1];
				if (LabelValue.equalsIgnoreCase("Additives Included"))
					dataExtraction.AdditivesIncluded = innerlist[1];
				if (LabelValue.equalsIgnoreCase("Processing Methods"))
					dataExtraction.ProcessingMethods = innerlist[1];
				if (LabelValue.equalsIgnoreCase("Solid Content"))
					dataExtraction.SolidContent = innerlist[1];
			}
			// Thread.sleep(1000);
		}
		TDSDataExtraction tDSDataExtraction = new TDSDataExtraction();
		try {
			w.until(ExpectedConditions
					.visibilityOfElementLocated(By.className("content-block-section__content-blocks")));
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			WebElement scroll2 = driver.findElement(By.className("content-block-section__content-blocks"));
			// js1.executeScript("window.scrollBy(0,1100)");
			js2.executeScript("arguments[0].scrollIntoView();", scroll2);

			// To extract few data details - middle section [Identification and
			// Functionality]

			WebElement middledriver1 = driver.findElement(By.className("content-block-section__content-blocks"));
			List<WebElement> innermiddledrivers1 = middledriver1.findElements(By.tagName("knowde-content-block"));

			// TDSDataExtraction tDSDataExtraction = new TDSDataExtraction();

			String master = "";
			;
			String target = "";
			int startIndex = 0;
			int stopIndex = startIndex + target.length();
			StringBuilder builder = new StringBuilder();

			for (WebElement ele : innermiddledrivers1) {
				// Thread.sleep(3000);
				master = ele.getText();
				if (master.contains("Chemical Family")) {
					target = "Chemical Family";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.chemicalFamily = builder.toString();
				} else if (master.contains("Chemical Name")) {
					target = "Chemical Name";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.chemicalName = builder.toString();
				} else if (master.contains("CASE Ingredients Functions")) {
					target = "CASE Ingredients Functions";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.cASEIngredientsFunctions = builder.toString();
				} else if (master.contains("Fluids & Lubricants Functions")) {
					target = "Fluids & Lubricants Functions";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.fluidsLubricantsFunctions = builder.toString();
				} else if (master.contains("Plastics & Elastomers Functions")) {
					target = "Plastics & Elastomers Functions";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.plasticsElastomersFunctions = builder.toString();
				} else if (master.contains("CAS No.")) {
					target = "CAS No.";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.cASNo = builder.toString();
				} else if (master.contains("Technologies")) {
					target = "Technologies";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.technologies = builder.toString();
				} else if (master.contains("Product Families")) {
					target = "Product Families";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.productFamilies = builder.toString();
				} else if (master.contains("INCI Name")) {
					target = "INCI Name";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.INCIName = builder.toString();
				} else if (master.contains("Ingredient Origin")) {
					target = "Ingredient Origin";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.ingredientOrigin = builder.toString();
				} else if (master.contains("Cleaning Ingredients Functions")) {
					target = "Cleaning Ingredients Functions";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.cleaningIngredientsFunctions = builder.toString();
				} else if (master.contains("Cosmetic Ingredients Functions")) {
					target = "Cosmetic Ingredients Functions";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.cosmeticIngredientsFunctions = builder.toString();
				} else if (master.contains("Base Chemicals Functions")) {
					target = "Base Chemicals Functions";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.BaseChemicalsFunctions = builder.toString();
				} else if (master.contains("Polymer Name")) {
					target = "Polymer Name";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.Polymername = builder.toString();
				} else if (master.contains("Additives Included")) {
					target = "Additives Included";
					stopIndex = startIndex + target.length();
					builder = new StringBuilder(master);
					builder.replace(startIndex, stopIndex, "");
					tDSDataExtraction.Additivesincluded = builder.toString();
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong in identification and functionality");
		}
		// Thread.sleep(1000);
		TDSDataExtraction5 tDSDataExtraction5 = new TDSDataExtraction5();
		try {
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"/html[1]/body[1]/knowde-root[1]/knowde-main-layout[1]/section[1]/div[2]/knowde-storefront[1]/knowde-storefront-container[1]/div[1]/knowde-storefront-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/knowde-product[1]/knowde-product-general[1]/div[1]/knowde-content-block-section-list[1]/div[1]/knowde-content-block-section[2]/section[1]/div[2]")));
			JavascriptExecutor js6 = (JavascriptExecutor) driver;
			WebElement scroll6 = driver.findElement(By.xpath(
					"/html[1]/body[1]/knowde-root[1]/knowde-main-layout[1]/section[1]/div[2]/knowde-storefront[1]/knowde-storefront-container[1]/div[1]/knowde-storefront-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/knowde-product[1]/knowde-product-general[1]/div[1]/knowde-content-block-section-list[1]/div[1]/knowde-content-block-section[2]/section[1]/div[2]"));
			// js1.executeScript("window.scrollBy(0,1100)");
			js6.executeScript("arguments[0].scrollIntoView();", scroll6);

			// To extract few data details - lowerdown section [Features and Benefits]
			WebElement middledriver5 = driver.findElement(By.xpath(
					"/html[1]/body[1]/knowde-root[1]/knowde-main-layout[1]/section[1]/div[2]/knowde-storefront[1]/knowde-storefront-container[1]/div[1]/knowde-storefront-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/knowde-product[1]/knowde-product-general[1]/div[1]/knowde-content-block-section-list[1]/div[1]/knowde-content-block-section[2]/section[1]/div[2]"));
			List<WebElement> innermiddledrivers5 = middledriver5.findElements(By.tagName("knowde-content-block"));
			// TDSDataExtraction5 tDSDataExtraction5 = new TDSDataExtraction5();

			String master5 = "";
			;
			String target5 = "";
			int startIndex5 = 0;
			int stopIndex5 = startIndex5 + target5.length();
			StringBuilder builder5 = new StringBuilder();

			for (WebElement ele : innermiddledrivers5) {
				// Thread.sleep(3000);
				master5 = ele.getText();
				if (master5.contains("CASE Ingredients Features")) {
					target5 = "CASE Ingredients Features";
					stopIndex5 = startIndex5 + target5.length();
					builder5 = new StringBuilder(master5);
					builder5.replace(startIndex5, stopIndex5, "");
					tDSDataExtraction5.CASEIngredientsFeatures = builder5.toString();
				} else if (master5.contains("Benefit Claims")) {
					target5 = "Benefit Claims";
					stopIndex5 = startIndex5 + target5.length();
					builder5 = new StringBuilder(master5);
					builder5.replace(startIndex5, stopIndex5, "");
					tDSDataExtraction5.BenefitClaims = builder5.toString();
				} else if (master5.contains("Labeling Claims")) {
					target5 = "Labeling Claims";
					stopIndex5 = startIndex5 + target5.length();
					builder5 = new StringBuilder(master5);
					builder5.replace(startIndex5, stopIndex5, "");
					tDSDataExtraction5.LabelingClaims = builder5.toString();
				} else if (master5.contains("HII Features")) {
					target5 = "HII Features";
					stopIndex5 = startIndex5 + target5.length();
					builder5 = new StringBuilder(master5);
					builder5.replace(startIndex5, stopIndex5, "");
					tDSDataExtraction5.HIIFeatures = builder5.toString();
				} else if (master5.contains("Performance Highlights")) {
					target5 = "Performance Highlights";
					stopIndex5 = startIndex5 + target5.length();
					builder5 = new StringBuilder(master5);
					builder5.replace(startIndex5, stopIndex5, "");
					tDSDataExtraction5.PerformanceHighlights = builder5.toString();
				} else if (master5.contains("Materials Features")) {
					target5 = "Materials Features";
					stopIndex5 = startIndex5 + target5.length();
					builder5 = new StringBuilder(master5);
					builder5.replace(startIndex5, stopIndex5, "");
					tDSDataExtraction5.MaterialsFeatures = builder5.toString();
				} else if (master5.contains("Product Highlights")) {
					target5 = "Product Highlights";
					stopIndex5 = startIndex5 + target5.length();
					builder5 = new StringBuilder(master5);
					builder5.replace(startIndex5, stopIndex5, "");
					tDSDataExtraction5.ProductHighlights = builder5.toString();
				} else if (master5.contains("Special Properties")) {
					target5 = "Special Properties";
					stopIndex5 = startIndex5 + target5.length();
					builder5 = new StringBuilder(master5);
					builder5.replace(startIndex5, stopIndex5, "");
					tDSDataExtraction5.SpecialProperties = builder5.toString();
				} else if (master5.contains("Distinguishing Features")) {
					target5 = "Distinguishing Features";
					stopIndex5 = startIndex5 + target5.length();
					builder5 = new StringBuilder(master5);
					builder5.replace(startIndex5, stopIndex5, "");
					tDSDataExtraction5.DistinguishingFeatures = builder5.toString();
				} else if (master5.contains("Properties and Advantages")) {
					target5 = "Properties and Advantages";
					stopIndex5 = startIndex5 + target5.length();
					builder5 = new StringBuilder(master5);
					builder5.replace(startIndex5, stopIndex5, "");
					tDSDataExtraction5.PropertiesandAdvantages = builder5.toString();
				} else if (master5.contains("Benefits")) {
					target5 = "Benefits";
					stopIndex5 = startIndex5 + target5.length();
					builder5 = new StringBuilder(master5);
					builder5.replace(startIndex5, stopIndex5, "");
					tDSDataExtraction5.Benefits = builder5.toString();
				} else if (master5.contains("Key Attributes")) {
					target5 = "Key Attributes";
					stopIndex5 = startIndex5 + target5.length();
					builder5 = new StringBuilder(master5);
					builder5.replace(startIndex5, stopIndex5, "");
					tDSDataExtraction5.KeyAttributes = builder5.toString();
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong in features and benefits");
		}
		// Thread.sleep(1000);
		TDSDataExtraction2 tDSDataExtraction2 = new TDSDataExtraction2();
		try {
			JavascriptExecutor js3 = (JavascriptExecutor) driver;
			WebElement scroll3 = driver.findElement(By.xpath(
					"/html[1]/body[1]/knowde-root[1]/knowde-main-layout[1]/section[1]/div[2]/knowde-storefront[1]/knowde-storefront-container[1]/div[1]/knowde-storefront-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/knowde-product[1]/knowde-product-general[1]/div[1]/knowde-content-block-section-list[1]/div[1]/knowde-content-block-section[3]/section[1]/div[2]"));
			// js3.executeScript("window.scrollBy(0,1250)");
			js3.executeScript("arguments[0].scrollIntoView();", scroll3);
			// To extract more data details - middle section [Applications And Uses]

			WebElement middledriver2 = driver.findElement(By.xpath(
					"/html[1]/body[1]/knowde-root[1]/knowde-main-layout[1]/section[1]/div[2]/knowde-storefront[1]/knowde-storefront-container[1]/div[1]/knowde-storefront-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/knowde-product[1]/knowde-product-general[1]/div[1]/knowde-content-block-section-list[1]/div[1]/knowde-content-block-section[3]/section[1]/div[2]"));
			List<WebElement> innermiddledrivers2 = middledriver2.findElements(By.tagName("knowde-content-block"));

			// TDSDataExtraction2 tDSDataExtraction2 = new TDSDataExtraction2();

			String master2 = "";
			;
			String target2 = "";
			int startIndex2 = 0;
			int stopIndex2 = startIndex2 + target2.length();
			StringBuilder builder2 = new StringBuilder();

			for (WebElement ele : innermiddledrivers2) {
				// Thread.sleep(3000);
				master2 = ele.getText();
				if (master2.contains("Markets")) {
					target2 = "Markets";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.markets = builder2.toString();
				} else if (master2.contains("Applications")) {
					target2 = "Applications";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.applications = builder2.toString();
				} else if (master2.contains("Fluids & Lubricants Type")) {
					target2 = "Fluids & Lubricants Type";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.fluidAndLubricantsType = builder2.toString();
				} else if (master2.contains("Adhesive & Sealant Type")) {
					target2 = "Adhesive & Sealant Type";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.adhesiveAndSealant = builder2.toString();
				} else if (master2.contains("Coating Type")) {
					target2 = "Coating Type";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.coatingType = builder2.toString();
				} else if (master2.contains("Plastics & Elastomers Processing Methods")) {
					target2 = "Plastics & Elastomers Processing Methods";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.plasticsAndElastomersProcessingMethods = builder2.toString();
				} else if (master2.contains("Epoxy Curatives")) {
					target2 = "Epoxy Curatives";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.epoxyCuratives = builder2.toString();
				} else if (master2.contains("Applications & Uses")) {
					target2 = "Applications & Uses";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.applicationsAndUses = builder2.toString();
				} else if (master2.contains("Epoxy Applications")) {
					target2 = "Epoxy Applications";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.epoxyApplications = builder2.toString();
				} else if (master2.contains("Polyurethane and Polyurea")) {
					target2 = "Polyurethane and Polyurea";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.polyUrethaneAndPolyurea = builder2.toString();
				} else if (master2.contains("Hot Melt Adhesives")) {
					target2 = "Hot Melt Adhesives";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.hotMeltAdhesives = builder2.toString();
				} else if (master2.contains("High Performance Polyamides")) {
					target2 = "High Performance Polyamides";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.highPerformancePolyamides = builder2.toString();
				} else if (master2.contains("Other Applications")) {
					target2 = "Other Applications";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.otherApplications = builder2.toString();
				} else if (master2.contains("Application Format")) {
					target2 = "Application Format";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.applicationFormat = builder2.toString();
				} else if (master2.contains("Fluids & Lubricants End Use")) {
					target2 = "Fluids & Lubricants End Use";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.fluidsAndLubricantsEndUse = builder2.toString();
				} else if (master2.contains("Home Care Applications")) {
					target2 = "Home Care Applications";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.homeCareApplication = builder2.toString();
				} else if (master2.contains("I&I Cleaning Applications")) {
					target2 = "I&I Cleaning Applications";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.IAndICleaningApplications = builder2.toString();
				} else if (master2.contains("Industrial Additives End Use")) {
					target2 = "Industrial Additives End Use";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.industrialAdditivesEndUse = builder2.toString();
				} else if (master2.contains("Skin Care Applications")) {
					target2 = "Skin Care Applications";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.skinCareApplications = builder2.toString();
				} else if (master2.contains("Treatment Product Applications")) {
					target2 = "Treatment Product Applications";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.treatmentProductApplications = builder2.toString();
				} else if (master2.contains("Plastics & Elastomers End Uses")) {
					target2 = "Plastics & Elastomers End Uses";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.PlasticsAndElastomersEndUses = builder2.toString();
				} else if (master2.contains("Usage & Application")) {
					target2 = "Usage & Application";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.UsageAndApplication = builder2.toString();
				} else if (master2.contains("Suggested Uses")) {
					target2 = "Suggested Uses";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.SuggestedUses = builder2.toString();
				} else if (master2.contains("Recommended Dosage")) {
					target2 = "Recommended Dosage";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.RecommendedDosage = builder2.toString();
				} else if (master2.contains("Stability and Compatibility")) {
					target2 = "Stability and Compatibility";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.StabilityandCompatibility = builder2.toString();
				} else if (master2.contains("Recommended Uses")) {
					target2 = "Recommended Uses";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.RecommendedUses = builder2.toString();
				} else if (master2.contains("Applicable Processes")) {
					target2 = "Applicable Processes";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.ApplicableProcesses = builder2.toString();
				} else if (master2.contains("Fragrances & Perfume Applications")) {
					target2 = "Fragrances & Perfume Applications";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.FragrancesAndPerfumeApplications = builder2.toString();
				} else if (master2.contains("Recommended Uses & Known Applications")) {
					target2 = "Recommended Uses & Known Applications";
					stopIndex2 = startIndex2 + target2.length();
					builder2 = new StringBuilder(master2);
					builder2.replace(startIndex2, stopIndex2, "");
					tDSDataExtraction2.RecommendedUsesAndKnownApplications = builder2.toString();
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong in application and uses");
		}
		// Thread.sleep(1000);
		TDSDataExtraction3 tDSDataExtraction3 = new TDSDataExtraction3();
		try {
			JavascriptExecutor js4 = (JavascriptExecutor) driver;
			WebElement scroll4 = driver.findElement(By.xpath(
					"/html[1]/body[1]/knowde-root[1]/knowde-main-layout[1]/section[1]/div[2]/knowde-storefront[1]/knowde-storefront-container[1]/div[1]/knowde-storefront-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/knowde-product[1]/knowde-product-general[1]/div[1]/knowde-content-block-section-list[1]/div[1]/knowde-content-block-section[4]/section[1]/div[2]"));
			// js1.executeScript("window.scrollBy(0,1100)");
			js4.executeScript("arguments[0].scrollIntoView();", scroll4);

			// To extract few data details - lower section [Properties]
			WebElement middledriver3 = driver.findElement(By.xpath(
					"/html[1]/body[1]/knowde-root[1]/knowde-main-layout[1]/section[1]/div[2]/knowde-storefront[1]/knowde-storefront-container[1]/div[1]/knowde-storefront-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/knowde-product[1]/knowde-product-general[1]/div[1]/knowde-content-block-section-list[1]/div[1]/knowde-content-block-section[4]/section[1]/div[2]"));
			List<WebElement> innermiddledrivers3 = middledriver3.findElements(By.tagName("knowde-content-block"));

			// TDSDataExtraction3 tDSDataExtraction3 = new TDSDataExtraction3();

			String master3 = "";
			;
			String target3 = "";
			int startIndex3 = 0;
			int stopIndex3 = startIndex3 + target3.length();
			StringBuilder builder3 = new StringBuilder();

			for (WebElement ele : innermiddledrivers3) {
				// Thread.sleep(3000);
				master3 = ele.getText();
				if (master3.contains("Compatible Polymers & Resins")) {
					target3 = "Compatible Polymers & Resins";
					stopIndex3 = startIndex3 + target3.length();
					builder3 = new StringBuilder(master3);
					builder3.replace(startIndex3, stopIndex3, "");
					tDSDataExtraction3.CompatiblePolymersAndResins = builder3.toString();
				} else if (master3.contains("Compatible Substrates & Surfaces")) {
					target3 = "Compatible Substrates & Surfaces";
					stopIndex3 = startIndex3 + target3.length();
					builder3 = new StringBuilder(master3);
					builder3.replace(startIndex3, stopIndex3, "");
					tDSDataExtraction3.CompatibleSubstratesAndSurfaces = builder3.toString();
				} else if (master3.contains("Physical Form")) {
					target3 = "Physical Form";
					stopIndex3 = startIndex3 + target3.length();
					builder3 = new StringBuilder(master3);
					builder3.replace(startIndex3, stopIndex3, "");
					tDSDataExtraction3.PhysicalForm = builder3.toString();
				} else if (master3.contains("Appearance")) {
					target3 = "Appearance";
					stopIndex3 = startIndex3 + target3.length();
					builder3 = new StringBuilder(master3);
					builder3.replace(startIndex3, stopIndex3, "");
					tDSDataExtraction3.Appearance = builder3.toString();
				} else if (master3.contains("Miscible In")) {
					target3 = "Miscible In";
					stopIndex3 = startIndex3 + target3.length();
					builder3 = new StringBuilder(master3);
					builder3.replace(startIndex3, stopIndex3, "");
					tDSDataExtraction3.MiscibleIn = builder3.toString();
				} else if (master3.contains("Typical Properties")) {
					target3 = "Typical Properties";
					stopIndex3 = startIndex3 + target3.length();
					builder3 = new StringBuilder(master3);
					builder3.replace(startIndex3, stopIndex3, "");
					tDSDataExtraction3.TypicalProperties = builder3.toString();
				} else if (master3.contains("Odor")) {
					target3 = "Odor";
					stopIndex3 = startIndex3 + target3.length();
					builder3 = new StringBuilder(master3);
					builder3.replace(startIndex3, stopIndex3, "");
					tDSDataExtraction3.Odor = builder3.toString();
				} else if (master3.contains("Carrier")) {
					target3 = "Carrier";
					stopIndex3 = startIndex3 + target3.length();
					builder3 = new StringBuilder(master3);
					builder3.replace(startIndex3, stopIndex3, "");
					tDSDataExtraction3.Carrier = builder3.toString();
				} else if (master3.contains("Dilution")) {
					target3 = "Dilution";
					stopIndex3 = startIndex3 + target3.length();
					builder3 = new StringBuilder(master3);
					builder3.replace(startIndex3, stopIndex3, "");
					tDSDataExtraction3.Dilution = builder3.toString();
				} else if (master3.contains("Sales Specifications")) {
					target3 = "Sales Specifications";
					stopIndex3 = startIndex3 + target3.length();
					builder3 = new StringBuilder(master3);
					builder3.replace(startIndex3, stopIndex3, "");
					tDSDataExtraction3.SalesSpecifications = builder3.toString();
				} else if (master3.contains("Slightly Soluble In")) {
					target3 = "Slightly Soluble In";
					stopIndex3 = startIndex3 + target3.length();
					builder3 = new StringBuilder(master3);
					builder3.replace(startIndex3, stopIndex3, "");
					tDSDataExtraction3.SlightlySolubleIn = builder3.toString();

				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong in properties");
		}
		// Thread.sleep(1000);
		TDSDataExtraction4 tDSDataExtraction4 = new TDSDataExtraction4();
		try {
			JavascriptExecutor js5 = (JavascriptExecutor) driver;
			WebElement scroll5 = driver.findElement(By.xpath(
					"/html[1]/body[1]/knowde-root[1]/knowde-main-layout[1]/section[1]/div[2]/knowde-storefront[1]/knowde-storefront-container[1]/div[1]/knowde-storefront-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/knowde-product[1]/knowde-product-general[1]/div[1]/knowde-content-block-section-list[1]/div[1]/knowde-content-block-section[6]/section[1]/div[2]"));
			// js1.executeScript("window.scrollBy(0,1100)");
			js5.executeScript("arguments[0].scrollIntoView();", scroll5);

			// To extract few data details - lowerdown section [Packaging and Availability]
			WebElement middledriver4 = driver.findElement(By.xpath(
					"/html[1]/body[1]/knowde-root[1]/knowde-main-layout[1]/section[1]/div[2]/knowde-storefront[1]/knowde-storefront-container[1]/div[1]/knowde-storefront-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/knowde-product[1]/knowde-product-general[1]/div[1]/knowde-content-block-section-list[1]/div[1]/knowde-content-block-section[6]/section[1]/div[2]"));
			List<WebElement> innermiddledrivers4 = middledriver4.findElements(By.tagName("knowde-content-block"));
			// TDSDataExtraction4 tDSDataExtraction4 = new TDSDataExtraction4();

			String master4 = "";
			;
			String target4 = "";
			int startIndex4 = 0;
			int stopIndex4 = startIndex4 + target4.length();
			StringBuilder builder4 = new StringBuilder();

			for (WebElement ele : innermiddledrivers4) {
				// Thread.sleep(3000);
				master4 = ele.getText();
				if (master4.contains("Packaging Information")) {
					target4 = "Packaging Information";
					stopIndex4 = startIndex4 + target4.length();
					builder4 = new StringBuilder(master4);
					builder4.replace(startIndex4, stopIndex4, "");
					tDSDataExtraction4.PackagingInformation = builder4.toString();
				} else if (master4.contains("Country Availability")) {
					target4 = "Country Availability";
					stopIndex4 = startIndex4 + target4.length();
					builder4 = new StringBuilder(master4);
					builder4.replace(startIndex4, stopIndex4, "");
					tDSDataExtraction4.CountryAvailability = builder4.toString();
				} else if (master4.contains("Regional Availability")) {
					target4 = "Regional Availability";
					stopIndex4 = startIndex4 + target4.length();
					builder4 = new StringBuilder(master4);
					builder4.replace(startIndex4, stopIndex4, "");
					tDSDataExtraction4.RegionalAvailability = builder4.toString();
				} else if (master4.contains("Packaging Type")) {
					target4 = "Packaging Type";
					stopIndex4 = startIndex4 + target4.length();
					builder4 = new StringBuilder(master4);
					builder4.replace(startIndex4, stopIndex4, "");
					tDSDataExtraction4.PackagingType = builder4.toString();
				} else if (master4.contains("Packaging")) {
					target4 = "Packaging";
					stopIndex4 = startIndex4 + target4.length();
					builder4 = new StringBuilder(master4);
					builder4.replace(startIndex4, stopIndex4, "");
					tDSDataExtraction4.Packaging = builder4.toString();
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong in packaging and availability");
		} finally {
			System.out.println("Try catch is finished");
		}

		MainDataExtraction mainDataExtraction = new MainDataExtraction();
		mainDataExtraction.DataExtraction = dataExtraction;
		mainDataExtraction.TDSDataExtraction = tDSDataExtraction;
		mainDataExtraction.TDSDataExtraction2 = tDSDataExtraction2;
		mainDataExtraction.TDSDataExtraction3 = tDSDataExtraction3;
		mainDataExtraction.TDSDataExtraction4 = tDSDataExtraction4;
		mainDataExtraction.TDSDataExtraction5 = tDSDataExtraction5;

		generateFile(mainDataExtraction, index, driver);
	}

	public void generateFile(MainDataExtraction mainDataExtraction, int index, WebDriver driver)
			throws UnsupportedEncodingException, IOException, ParseException, InterruptedException {

//		MainDataExtraction mainDataExtraction = beforetest();
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();

		Gson gson = new Gson();
		File file = new File("E://softwares//input_file.json");
		FileInputStream inputStream = new FileInputStream(file);

		String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		ExampleClass exampleClass = gson.fromJson(text, ExampleClass.class);

		List<OutputClass> list1 = new ArrayList<OutputClass>();
		OutputClass outputClassItem = new OutputClass();
		if (exampleClass != null) {
			if (exampleClass.data != null) {
				if (exampleClass.data.browsePage != null) {
					if (exampleClass.data.browsePage.records != null
							&& exampleClass.data.browsePage.records.size() > 0) {

						OutputClass specificURLdata = generateinputjson();
						
						
						for (Record record : exampleClass.data.browsePage.records) {
							if(specificURLdata.portalUrl != record.portalUrl)
							{
								specificURLdata.name = record.name;
								specificURLdata.slug = record.slug;
								specificURLdata.companyName = record.companyName;
								specificURLdata.companySlug = record.companySlug;
							}
							
							outputClassItem = new OutputClass();
							outputClassItem.id = record.id;
							outputClassItem.portalUrl = record.portalUrl;
							outputClassItem.name = specificURLdata.name;
							outputClassItem.slug = specificURLdata.slug;
							outputClassItem.cardBannerUrl = record.cardBannerUrl;
							outputClassItem.logoUrl = record.logoUrl;
							outputClassItem.description = record.description;
							outputClassItem.brandId = record.brandId;
							outputClassItem.brandName = record.brandName;
							outputClassItem.companyId = record.companyId;
							outputClassItem.companyName = specificURLdata.companyName;
							outputClassItem.companySlug = specificURLdata.companySlug;
							if (mainDataExtraction.DataExtraction != null) {
								outputClassItem.CASNumber = mainDataExtraction.DataExtraction.CASNumber;
								outputClassItem.ChemicalFamily = mainDataExtraction.DataExtraction.chemicalFamily;
								outputClassItem.ChemicalName = mainDataExtraction.DataExtraction.chemicalName;
								outputClassItem.Function = mainDataExtraction.DataExtraction.function;
								outputClassItem.inciName = mainDataExtraction.DataExtraction.inciName;
								outputClassItem.ingredientOrigin = mainDataExtraction.DataExtraction.ingredientOrigin;
								outputClassItem.labelingClaims = mainDataExtraction.DataExtraction.labelingClaims;
								outputClassItem.PolymerName = mainDataExtraction.DataExtraction.PolymerName;
								outputClassItem.CompatibleSubstratesandSurfaces = mainDataExtraction.DataExtraction.CompatibleSubstratesandSurfaces;
								outputClassItem.EndUses = mainDataExtraction.DataExtraction.EndUses;
								outputClassItem.Features = mainDataExtraction.DataExtraction.Features;
								outputClassItem.AdditivesIncluded = mainDataExtraction.DataExtraction.AdditivesIncluded;
								outputClassItem.ProcessingMethods = mainDataExtraction.DataExtraction.ProcessingMethods;
								outputClassItem.SolidContent = mainDataExtraction.DataExtraction.SolidContent;
							}

							if (mainDataExtraction.TDSDataExtraction != null) {
								outputClassItem.chemicalName1 = mainDataExtraction.TDSDataExtraction.chemicalName;
								outputClassItem.chemicalFamily1 = mainDataExtraction.TDSDataExtraction.chemicalFamily;
								outputClassItem.cASEIngredientsFunctions = mainDataExtraction.TDSDataExtraction.cASEIngredientsFunctions;
								outputClassItem.fluidsLubricantsFunctions = mainDataExtraction.TDSDataExtraction.fluidsLubricantsFunctions;
								outputClassItem.plasticsElastomersFunctions = mainDataExtraction.TDSDataExtraction.plasticsElastomersFunctions;
								outputClassItem.cASNo = mainDataExtraction.TDSDataExtraction.cASNo;
								outputClassItem.technologies = mainDataExtraction.TDSDataExtraction.technologies;
								outputClassItem.productFamilies = mainDataExtraction.TDSDataExtraction.productFamilies;
								outputClassItem.inciName = mainDataExtraction.TDSDataExtraction.INCIName;
								outputClassItem.ingredientOrigin = mainDataExtraction.TDSDataExtraction.ingredientOrigin;
								outputClassItem.cleaningIngredientsFunctions = mainDataExtraction.TDSDataExtraction.cleaningIngredientsFunctions;
								outputClassItem.cosmeticIngredientsFunctions = mainDataExtraction.TDSDataExtraction.cosmeticIngredientsFunctions;
								outputClassItem.BaseChemicalsFunctions = mainDataExtraction.TDSDataExtraction.BaseChemicalsFunctions;
								outputClassItem.Polymername = mainDataExtraction.TDSDataExtraction.Polymername;
								outputClassItem.Additivesincluded = mainDataExtraction.TDSDataExtraction.Additivesincluded;
							}
							if (mainDataExtraction.TDSDataExtraction2 != null) {
								outputClassItem.markets = mainDataExtraction.TDSDataExtraction2.markets;
								outputClassItem.applications = mainDataExtraction.TDSDataExtraction2.applications;
								outputClassItem.fluidAndLubricantsType = mainDataExtraction.TDSDataExtraction2.fluidAndLubricantsType;
								outputClassItem.adhesiveAndSealant = mainDataExtraction.TDSDataExtraction2.adhesiveAndSealant;
								outputClassItem.coatingType = mainDataExtraction.TDSDataExtraction2.coatingType;
								outputClassItem.plasticsAndElastomersProcessingMethods = mainDataExtraction.TDSDataExtraction2.plasticsAndElastomersProcessingMethods;
								outputClassItem.epoxyCuratives = mainDataExtraction.TDSDataExtraction2.epoxyCuratives;
								outputClassItem.applicationsAndUses = mainDataExtraction.TDSDataExtraction2.applicationsAndUses;
								outputClassItem.epoxyApplications = mainDataExtraction.TDSDataExtraction2.epoxyApplications;
								outputClassItem.polyUrethaneAndPolyurea = mainDataExtraction.TDSDataExtraction2.polyUrethaneAndPolyurea;
								outputClassItem.hotMeltAdhesives = mainDataExtraction.TDSDataExtraction2.hotMeltAdhesives;
								outputClassItem.highPerformancePolyamides = mainDataExtraction.TDSDataExtraction2.highPerformancePolyamides;
								outputClassItem.otherApplications = mainDataExtraction.TDSDataExtraction2.otherApplications;
								outputClassItem.applicationFormat = mainDataExtraction.TDSDataExtraction2.applicationFormat;
								outputClassItem.fluidsAndLubricantsEndUse = mainDataExtraction.TDSDataExtraction2.fluidsAndLubricantsEndUse;
								outputClassItem.homeCareApplication = mainDataExtraction.TDSDataExtraction2.homeCareApplication;
								outputClassItem.IAndICleaningApplications = mainDataExtraction.TDSDataExtraction2.IAndICleaningApplications;
								outputClassItem.industrialAdditivesEndUse = mainDataExtraction.TDSDataExtraction2.industrialAdditivesEndUse;
								outputClassItem.skinCareApplications = mainDataExtraction.TDSDataExtraction2.skinCareApplications;
								outputClassItem.treatmentProductApplications = mainDataExtraction.TDSDataExtraction2.treatmentProductApplications;
								outputClassItem.PlasticsAndElastomersEndUses = mainDataExtraction.TDSDataExtraction2.PlasticsAndElastomersEndUses;
								outputClassItem.UsageAndApplication = mainDataExtraction.TDSDataExtraction2.UsageAndApplication;
								outputClassItem.SuggestedUses = mainDataExtraction.TDSDataExtraction2.SuggestedUses;
								outputClassItem.RecommendedDosage = mainDataExtraction.TDSDataExtraction2.RecommendedDosage;
								outputClassItem.StabilityandCompatibility = mainDataExtraction.TDSDataExtraction2.StabilityandCompatibility;
								outputClassItem.RecommendedUses = mainDataExtraction.TDSDataExtraction2.RecommendedUses;
								outputClassItem.ApplicableProcesses = mainDataExtraction.TDSDataExtraction2.ApplicableProcesses;
								outputClassItem.FragrancesAndPerfumeApplications = mainDataExtraction.TDSDataExtraction2.FragrancesAndPerfumeApplications;
								outputClassItem.RecommendedUsesAndKnownApplications = mainDataExtraction.TDSDataExtraction2.RecommendedUsesAndKnownApplications;

							}
							if (mainDataExtraction.TDSDataExtraction3 != null) {
								outputClassItem.CompatiblePolymersAndResins = mainDataExtraction.TDSDataExtraction3.CompatiblePolymersAndResins;
								outputClassItem.CompatibleSubstratesAndSurfaces = mainDataExtraction.TDSDataExtraction3.CompatibleSubstratesAndSurfaces;
								outputClassItem.PhysicalForm = mainDataExtraction.TDSDataExtraction3.PhysicalForm;
								outputClassItem.Appearance = mainDataExtraction.TDSDataExtraction3.Appearance;
								outputClassItem.MiscibleIn = mainDataExtraction.TDSDataExtraction3.MiscibleIn;
								outputClassItem.TypicalProperties = mainDataExtraction.TDSDataExtraction3.TypicalProperties;
								outputClassItem.Odor = mainDataExtraction.TDSDataExtraction3.Odor;
								outputClassItem.Carrier = mainDataExtraction.TDSDataExtraction3.Carrier;
								outputClassItem.Dilution = mainDataExtraction.TDSDataExtraction3.Dilution;
								outputClassItem.SalesSpecifications = mainDataExtraction.TDSDataExtraction3.SalesSpecifications;
								outputClassItem.SlightlySolubleIn = mainDataExtraction.TDSDataExtraction3.SlightlySolubleIn;

							}
							if (mainDataExtraction.TDSDataExtraction4 != null) {
								outputClassItem.PackagingInformation = mainDataExtraction.TDSDataExtraction4.PackagingInformation;
								outputClassItem.CountryAvailability = mainDataExtraction.TDSDataExtraction4.CountryAvailability;
								outputClassItem.RegionalAvailability = mainDataExtraction.TDSDataExtraction4.RegionalAvailability;
								outputClassItem.PackagingType = mainDataExtraction.TDSDataExtraction4.PackagingType;
								outputClassItem.Packaging = mainDataExtraction.TDSDataExtraction4.Packaging;

							}
							if (mainDataExtraction.TDSDataExtraction5 != null) {
								outputClassItem.CASEIngredientsFeatures = mainDataExtraction.TDSDataExtraction5.CASEIngredientsFeatures;
								outputClassItem.BenefitClaims = mainDataExtraction.TDSDataExtraction5.BenefitClaims;
								outputClassItem.LabelingClaims = mainDataExtraction.TDSDataExtraction5.LabelingClaims;
								outputClassItem.HIIFeatures = mainDataExtraction.TDSDataExtraction5.HIIFeatures;
								outputClassItem.PerformanceHighlights = mainDataExtraction.TDSDataExtraction5.PerformanceHighlights;
								outputClassItem.MaterialsFeatures = mainDataExtraction.TDSDataExtraction5.MaterialsFeatures;
								outputClassItem.SpecialProperties = mainDataExtraction.TDSDataExtraction5.SpecialProperties;
								outputClassItem.DistinguishingFeatures = mainDataExtraction.TDSDataExtraction5.DistinguishingFeatures;
								outputClassItem.PropertiesandAdvantages = mainDataExtraction.TDSDataExtraction5.PropertiesandAdvantages;
								outputClassItem.Benefits = mainDataExtraction.TDSDataExtraction5.Benefits;
								outputClassItem.KeyAttributes = mainDataExtraction.TDSDataExtraction5.KeyAttributes;

							}

						}
						list1.add(outputClassItem);
					}
				}
			}
		}

		// Write JSON file
		String filename = "output_file" + Integer.toString(index);
		try (FileWriter file1 = new FileWriter("E://softwares//AllJson//" + filename + ".json")) {
			file1.append(new Gson().toJson(list1));
			file1.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(list1.get(0).id);
		// driver.quit();
	}
	
	public static OutputClass generateinputjson() throws IOException {

		Gson gson = new Gson();
		File file = new File("E://softwares//input_file copy - Copy.json");
		FileInputStream inputStream = new FileInputStream(file);
		String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		InputData inputData = gson.fromJson(text, InputData.class);
		OutputClass outputClassItem = new OutputClass();
		if (inputData != null) {
			if (inputData.records != null) {
				for (Record1 record : inputData.records) {
					outputClassItem = new OutputClass();
					outputClassItem.name = record.name;
					outputClassItem.slug = record.slug;
					outputClassItem.portalUrl = record.portalUrl;
					outputClassItem.companyName = record.name;
					outputClassItem.companySlug = record.companySlug;
				}		
			}
		}
		return outputClassItem;
	}

}


class Record1
{
	public String name;
	public String slug;
	public String companyName;
	public String companySlug;
	public String portalUrl;
	
	}

class InputData
{
	public List<Record1> records ;
}