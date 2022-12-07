//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//
//import org.apache.poi.xwpf.usermodel.Document;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.w3c.dom.NodeList;
//
//import com.google.gson.Gson;
//
//import qa.MavenJava.ExampleClass;
//
//public class sampleDUrls {
//
//	 public static void main(String[] args) {
//		 System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
//			WebDriver driver = new ChromeDriver();
//         List<String> Url = getData("Urls", 100);
//        driver.get(st[i]);
//         // further test case coding
//    }
//
//    public static List<String> getData(String Data, int size) throws IOException {
//
//        String[] st = null;
//        Gson gson = new Gson();
//		File jsonfile = new File("E://softwares//input_file.json");
//		FileInputStream inputStream = new FileInputStream(jsonfile);
//
//		String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
//		ExampleClass exampleClass = gson.fromJson(text, ExampleClass.class);
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        try {
//            DocumentBuilder dbuilder = dbFactory.newDocumentBuilder();
////            Document doc = dbuilder.parse(jsonfile);
////
//            NodeList list = doc.getElementsByTagName(Data);
//
//            System.out.println("length of  : " + list.getLength());
//            st = new String[list.getLength()];
//            for (int i = 0; i < list.getLength(); i++) {
//
//                st[i] =  doc.getElementsByTagName(Data).item(i).getTextContent();
//                System.out.println(st[i]);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        List<String> data = new ArrayList<String>();
//        return data;
//    }
//	
//}
