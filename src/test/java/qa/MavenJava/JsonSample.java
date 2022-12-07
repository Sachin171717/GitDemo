//package qa.MavenJava;
//
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.concurrent.TimeUnit;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import io.restassured.internal.support.FileReader;
//
//import java.io.*;
//import java.util.*;
//import org.json.simple.*;
//import org.json.simple.parser.*;
//
//public class JsonSample {
//
//	@BeforeTest
//	public void beforeTest() throws IOException {
//		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//	  driver.get("https://www.knowde.com/stores/invista/products/dytek-a/");
//	driver.manage().window().maximize();
//	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//	}
//
//	@Test
//	public void testAut() throws InterruptedException, IOException, ParseException {
//	readWriteJSON();
//	}
//
//	@AfterTest
//	public void afterTest() {
//	driver.close();
//	}
//	WebDriver driver = new ChromeDriver();
////	public String login(String username, String password) throws InterruptedException {
////	driver.findElement(By.linkText("Log in")).click();
////	driver.findElement(By.name("Email")).sendKeys(username);
////	driver.findElement(By.name("Password")).sendKeys(password);
////	driver.findElement(By.xpath("//input[@class='button-1 login-button' and @value='Log in']")).click();
////
////	if(driver.findElements(By.xpath("//input[@id='vote-poll-1']")).size()>0)
////	{
////	String uname = driver.findElement(By.xpath("//a[@href='/customer/info']")) .getText();
////	if(uname.equals(username))
////	driver.findElement(By.xpath("//a[@href='/logout']")).click();
////	}
////	else 
////	{
////	driver.findElement(By.xpath("//a[@href='/login']")).click();
////	return "Invalid User";
////	}
////	return "Valid User";
////	}
//
//	@SuppressWarnings("unchecked")
////	public void readWriteJSON() throws InterruptedException, IOException, ParseException {
////	JSONParser jsonParser = new JSONParser();
////	try  {
//	//FileReader reader = new FileReader("E://softwares//input_file.json");
//	//Read JSON file
//		
//	          //  Object obj = jsonParser.parse(inputStream);
//	            //JSONArray dataList = (JSONArray) obj;
////	            System.out.println(dataList); //This prints the entire json file
////	            for(int i=0;i<dataList.size();i++) {
////	            JSONObject data = (JSONObject) dataList.get(i);
////	            System.out.println(data);//This prints every block - one json object
////	            JSONObject user = (JSONObject) data.get("data");
////	            System.out.println(user); //This prints each data in the block
////	            String title = (String) data.get("title");
////	            String totalRecords = (String) data.get("totalRecords");
////	            String result = output (title,totalRecords);
////	            data.put("result", result);
//
//	            //Write JSON file
////	                try (FileWriter file = new FileWriter("E://softwares//Outputfile.json")) {
////	                    file.append(dataList.toJSONString());
////	                    file.flush();
////	                } catch (IOException e) {
////	                    e.printStackTrace();
////	                }
////	            System.out.println(data);
////	             }
////	         } catch (FileNotFoundException e) {
////	e.printStackTrace();
////	}
//	//}
//
//	private String output(String title, String totalRecords) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	}
//	
//
