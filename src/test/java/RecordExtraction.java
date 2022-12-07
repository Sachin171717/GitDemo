import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import com.google.gson.Gson;
import qa.MavenJava.OutputClass;
public class RecordExtraction {
	

	public static OutputClass generateinputjson() throws IOException {

		Gson gson = new Gson();
		File file = new File("E://softwares//input_file copy - Copy.json");
		FileInputStream inputStream = new FileInputStream(file);
		String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		InputData inputData = gson.fromJson(text, InputData.class);
		OutputClass outputClassItem = new OutputClass();
		if (inputData != null) {
			if (inputData.records != null) {
				for (Record record : inputData.records) {
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

class Record
{
	public String name;
	public String slug;
	public String companyName;
	public String companySlug;
	public String portalUrl;
	
	}

class InputData
{
	public List<Record> records ;
}
		