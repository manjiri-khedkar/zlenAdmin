package mp.procurement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import ch.qos.logback.core.net.SyslogOutputStream;
import javafx.scene.control.Cell;
import mp.procurement.model.Party;
import mp.procurement.model.TradeRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FillForm {

	@Autowired
	CommonService common;

	// WebDriver dr=null;
	public static void main(String[] args) throws Throwable, IOException {
		FillForm obj = new FillForm();

		System.out.println(System.getProperty("user.dir"));
		WebDriver dr = obj.startBrowser(2);

		String filePath = "/home/vivek/Downloads/";
		//String filePath = "D:\\";

		// Call read file method of the class to read data

		//List data = obj.readExcel(filePath, "data.xls", "Sheet2");

		Thread.sleep(10000);

		obj.fillData(new ArrayList(), dr);
		// obj.getIPCData();
		// fillData

	}

	public void start() {

	}

	public void fillData(List data, WebDriver dr) {

		try {
			
			String tableId= "";
			for (int i = -2; i < data.size() - 1; i++) {
				WebElement btn_Add = dr.findElement(By.id("bttn0"));
				tableId = btn_Add.getAttribute("name").replace("btn", "");
				btn_Add.click();
				Thread.sleep(1000);
			}

			Thread.sleep(5000);
			System.out.println("rows added...");
			WebElement we = dr.findElement(By.xpath("//table[@id='"+tableId+"']"));

			List<WebElement> arr_tr = we.findElements(By.tagName("tr"));
			System.out.println("total rows ..." + arr_tr.size());
			
			for (WebElement row : arr_tr){
				System.out.println("#####################################3");
				//System.out.println(row.getText());
			}
			System.out.println("********************************************");
			for (int i = 1; i < data.size(); i++) {
				System.out.println("filling up :" + i);
				WebElement cur_row = arr_tr.get(i + 1);

				// System.out.println(cur_row.getText());
				String[] str_arr = (String[]) data.get(i);

				System.out.println(Arrays.toString(str_arr));
				List<WebElement> arr_td = cur_row.findElements(By.tagName("td"));

				Select select_priority = new Select(arr_td.get(0).findElement(By.tagName("select")));
				Select select_lot = new Select(arr_td.get(1).findElement(By.tagName("select")));
				arr_td.get(2).findElement(By.tagName("input")).sendKeys(str_arr[2]);
				;

				if (i==1){
					System.out.println(arr_td.get(1).findElement(By.tagName("select")).getText());
				}
				
				select_priority.selectByVisibleText(((int) Double.parseDouble(str_arr[0])) + "");
				select_lot.selectByVisibleText(str_arr[1]);
				Thread.sleep(1000);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public WebDriver startBrowser(int type) throws Throwable {

		WebDriver dr;
		if (type == 1) {

			System.setProperty("webdriver.ie.driver", "c:/IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 60000);
			capabilities.setCapability("logFile", "C:\\ielog.log");
			//capabilities.setCapability("logLevel", "TRACE");

			// browserVersion
			dr = new InternetExplorerDriver(capabilities);

			System.out.println("before Maximize");
			dr.manage().window().maximize();
			dr.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			dr.manage().deleteAllCookies();
			dr.manage().window().maximize();
		} else {
			// chrome-//////
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--silent");
			chromeOptions.addArguments("log-level=3");
			// chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--no-sandbox");

			dr = new ChromeDriver(chromeOptions);

			// IE code
			/*
			 * System.setProperty("webdriver.ie.driver",
			 * "c:/IEDriverServer.exe"); DesiredCapabilities capabilities =
			 * DesiredCapabilities.internetExplorer();
			 * 
			 * capabilities.setCapability(InternetExplorerDriver.
			 * INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			 * capabilities.setJavascriptEnabled(true);
			 * capabilities.setCapability(InternetExplorerDriver.
			 * BROWSER_ATTACH_TIMEOUT, 5000);
			 */
			// dr = new InternetExplorerDriver(capabilities);

			// browserVersion

			dr.manage().window().maximize();
			dr.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			dr.manage().deleteAllCookies();
			dr.manage().window().maximize();
		}
		dr.navigate().to("file:///home/vivek/Downloads/abc.htm");
		// dr.navigate().to("https://mfpfederation.abcprocure.com");
		System.out.println("Broser Started.....");
		return dr;

	}

	private boolean addParty(String name) {
		try {
			Party party = new Party();
			party.setParty_name(name);
			if (party != null) {
				/*
				 * if (common.checkPartyExists(party)){ return true; }
				 */
				if (party.getId() == 0) {
					common.saveParty(party);
					return true;
				}
			}
		} catch (Exception ex) {
			return false;
		}
		return false;
	}

	public void getData() {
	}

	public List readExcel(String filePath, String fileName, String sheetName) throws IOException {

		// Create an object of File class to open xlsx file

		System.out.println(filePath + "\\" + fileName);
		File file = new File(filePath + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;

		// Find the file extension by splitting file name in substring and
		// getting only extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			guru99Workbook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of HSSFWorkbook class

			guru99Workbook = new HSSFWorkbook(inputStream);

		}

		// Read sheet inside the workbook by its name

		Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);

		// Find number of rows in excel file

		int rowCount = guru99Sheet.getLastRowNum() - guru99Sheet.getFirstRowNum();

		// Create a loop over all the rows of excel file to read it
		List row_data = new ArrayList();
		for (int i = 0; i < rowCount + 1; i++) {

			Row row = guru99Sheet.getRow(i);

			// Create a loop to print cell values in a row
			int cno = row.getPhysicalNumberOfCells();

			if (cno > 0) {
				String arr[] = new String[cno];
				for (int j = 0; j < row.getLastCellNum(); j++) {

					// Print Excel data in console
					org.apache.poi.ss.usermodel.Cell c = row.getCell(j);
					String value = "";
					if (c.getCellType() == c.CELL_TYPE_STRING) {
						value = c.getStringCellValue();
					} else if (c.getCellType() == c.CELL_TYPE_NUMERIC) {
						value = c.getNumericCellValue() + "";
					} else if (c.getCellType() == c.CELL_TYPE_BLANK) {
						value = c.getBooleanCellValue() + "";
					}
					arr[j] = value;
				}
				row_data.add(arr);
			}

		}
		return row_data;
	}

}
