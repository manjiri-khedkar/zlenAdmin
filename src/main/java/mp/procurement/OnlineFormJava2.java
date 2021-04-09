package mp.procurement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import ch.qos.logback.core.net.SyslogOutputStream;
import mp.procurement.model.Party;
import mp.procurement.model.TradeRecord;
public class OnlineFormJava2 {
	
	@Autowired
	CommonService common;
	
	WebDriver dr=null;
	public static void main(String[] args) throws Throwable {
		OnlineFormJava2 obj = new OnlineFormJava2();
		obj.startBrowser();
		//obj.getData();
		obj.getIPCData();
	}
	
	public void getIPCData() {
		StringBuffer sb = new StringBuffer();

		List<String> data = new ArrayList<String>();
		try {
			BufferedWriter fw = new BufferedWriter(new FileWriter(new File("/tmp/out")));
			dr.switchTo().frame("showframe");

			boolean first = true;
			int x=0;
			while (x==0) {
				System.out.println(1);
				for (int itr = 3; itr < 17; itr++) {
					String tmp = "";
					if (itr<10){
						tmp="0"+itr;
					}else{
						tmp=itr+"";
					}
					WebElement we = dr.findElement(By.id("SearchWMDatagrid_ctl" + tmp + "_lnkbtnappNumber"));
					we.click();

					WebElement div = dr.findElement(By.id("panelgetdetail"));
					WebElement div_details = div.findElement(By.id("panelgetdetail"));
					String text = div_details.getText();
					TradeRecord  tr = populateTradeBean(text);
					common.saveTradeRecord(tr);
					data.add(text);
					fw.write(text);
					WebElement btn_exit = dr.findElement(By.id("btnExit"));
					btn_exit.click();
				}
			}
			fw.close();
		} catch (Exception ex) {
		}
		for (String cur_text : data) {
			System.out.println(cur_text);
		}
	}
	
	public TradeRecord populateTradeBean(String data){
		String[] rows = data.split("\n");
		TradeRecord r = new TradeRecord();
		for (String cur_row : rows) {
			if (cur_row.startsWith("Class ")) {
				String a = cur_row.replace("Class", "");
				r.setClass1(a);
				////sb.append(r.class1 + "\n");
			} else if (cur_row.startsWith("Certificate Detail Certificate No. ")) {
				String b = cur_row.replace("Certificate Detail Certificate No.", "");
				r.setCerti_no(b);
				////sb.append(r.certi_no + "\n");
			} else if (cur_row.startsWith("Date of Application ")) {
				String c = cur_row.replace("Date of Application", "");
				r.setDate_appli(c);
				////sb.append(r.date_appli + "\n");
			} else if (cur_row.startsWith("Country ")) {
				String d = cur_row.replace("Country", "");
				r.setCountry(d);
				////sb.append(r.country + "\n");
			} else if (cur_row.startsWith("User Detail ")) {
				String e = cur_row.replace("User Detail", "");
				r.setUser_detail(e);
				////sb.append(r.user_detail + "\n");
			} else if (cur_row.startsWith("Appropriate Office ")) {
				String f = cur_row.replace("Appropriate Office", "");
				r.setAppro_office(f);
				////sb.append(r.appro_office + "\n");
			} else if (cur_row.startsWith("State")) {
				String g = cur_row.replace("State", "");
				r.setState(g);
				////sb.append(r.state + "\n");
			} else if (cur_row.startsWith("As on Date :")) {
				String h = cur_row.replace("As on Date :", "");
				String[] hh = h.split(" View Registration Certificate");
				String hhh = hh[0];
				r.setAs_on_date(hhh);
				////sb.append(r.as_on_date + "\n");
			} else if (cur_row.startsWith("TM Application No.")) {
				String i = cur_row.replace("TM Application No.", "");
				r.setTm_appli_no(i);
				////sb.append(r.tm_appli_no + "\n");
			} else if (cur_row.startsWith("Status         :")) {
				String j = cur_row.replace("Status         :", "");
				String[] jj = j.split(" View TM Application | View Examination Report\r\n");
				String jjj = jj[0];
				r.setStatus(jjj);
				////sb.append(r.status + "\n");
			} else if (cur_row.startsWith("WARNING/DISCLAIMER :")) {
				String k = cur_row.replace("WARNING/DISCLAIMER :", "");
				String[] kk = k.split(
						" THE DATA OF TRADE MARKS REGISTRY IS UNDER THE PROCESS OF DIGITISATION, IF ANY DISCREPANCY IS OBSERVED IN THE DATA PLEASE CONTACT OR SUBMIT AT APPROPRIATE TRADE MARKS REGISTRY ALONGWITH SUPPORTING DOCUMENTS. THIS WILL HELP IN UPDATION OF ELECTRONIC RECORDS.");
				String kkk = kk[0];
				r.setWarning_or_disclamer(kkk);
				////sb.append(r.warning_or_disclamer + "\n");
			} else if (cur_row.startsWith("TM Applied For")) {
				String l = cur_row.replace("TM Applied For", "");
				r.setTm_applied(l);
				////sb.append(r.tm_applied + "\n");
			} else if (cur_row.startsWith("TM Category")) {
				String m = cur_row.replace("TM Category", "");
				r.setTm_category(m);
				////sb.append(r.tm_category + "\n");
			} else if (cur_row.startsWith("Trade Mark Type")) {
				String n = cur_row.replace("Trade Mark Type", "");
				r.setTrade_mark(n);
				////sb.append(r.trade_mark + "\n");
			} else if (cur_row.startsWith(" Dated :")) {
				String o = cur_row.replace(" Dated :", "");
				r.setDated(o);
				////sb.append(r.dated + "\n");
			} else if (cur_row.startsWith("Notified in Journal No :")) {
				String p = cur_row.replace("Notified in Journal No :", "");
				r.setNote_janrnal_no(p);
				////sb.append(r.note_janrnal_no + "\n");
			} else if (cur_row.startsWith("Valid upto/ Renewed upto")) {
				String q = cur_row.replace("Valid upto/ Renewed upto", "");
				r.setValid_or_renewed(q);
				////sb.append(r.valid_or_renewed + "\n");
			} else if (cur_row.startsWith(" Filing Mode")) {
				String s = cur_row.replace("Filing Mode", "");
				r.setFil_mode(s);
				////sb.append(r.fil_mode + "\n");
			} else if (cur_row.startsWith("Proprietor name ")) {
				String t = cur_row.replace("Proprietor name (1)", "");
				r.setProp_name(t);
				////sb.append(r.prop_name + "\n");
			} else if (cur_row.startsWith("Trading As :")) {
				String u = cur_row.replace("Trading As :", "");
				r.setTrad_as(u);
				////sb.append(r.trad_as + "\n");
			} else if (cur_row.startsWith("Body")) {
				String v = cur_row.replace("Body", "");
				r.setBody(v);
				////sb.append(r.body + "\n");
			} else if (cur_row.startsWith("Proprietor Address")) {
				String w = cur_row.replace("Proprietor Address", "");
				r.setProp_add(w);
				////sb.append(r.prop_add + "\n");
			} else if (cur_row.startsWith("Email Id")) {
				String x = cur_row.replace("Email Id", "");
				r.setEmail_id(x);
				////sb.append(r.email_id + "\n");
			} else if (cur_row.startsWith("Agent name")) {
				String y = cur_row.replace("Agent name", "");
				r.setAgent_name(y);
				////sb.append(r.agent_name + "\n");
			} else if (cur_row.startsWith("Agent Address")) {
				String z = cur_row.replace("Agent Address", "");
				r.setAgent_add(z);
				////sb.append(r.agent_add + "\n");
			} else if (cur_row.startsWith("Goods & Service Details ")) {
				String abc = cur_row.replace("Goods & Service Details", "");
				r.setGood_service(abc);
				////sb.append(r.good_service + "\n");
			} else if (cur_row.startsWith("BIDIES")) {
				String def = cur_row.replace("BIDIES", "");
				r.setBidies(def);
				////sb.append(r.bidies + "\n");
			} else if (cur_row.startsWith("Associated Trademarks")) {
				String ghi = cur_row.replace("Associated Trademarks", "");
				r.setAsso_trademark(ghi);
				////sb.append(r.asso_trademark + "\n");
			} else if (cur_row.startsWith("Publication Details Published in Journal No. :")) {
				String jkl = cur_row.replace("Publication Details Published in Journal No. :", "");
				r.setPublish_janrnal_no(jkl);
				////sb.append(r.publish_janrnal_no + "");
			} else if (cur_row.startsWith("Dated :")) {
				String mno = cur_row.replace("Dated :", "");
				r.setDated1(mno);
				////sb.append(r.dated1 + "\n");
			} else if (cur_row.startsWith("History/PR Details")) {
				String pqr = cur_row.replace("History/PR Details", "");
				r.setHis_pr_detail(pqr);
				////sb.append(r.his_pr_detail + "\n");
			} else if (cur_row.startsWith("Trade Mark Image :")) {
				String stu = cur_row.replace("Trade Mark Image :", "");
				r.setTrade_mrk_img(stu);
				////sb.append(r.trade_mrk_img + "\n");
			}
		}
		return r;
	}
	
	public void startBrowser() throws Throwable {
	
		OnlineFormJava2 onlineForm = new OnlineFormJava2();
	
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");

        dr = new ChromeDriver(chromeOptions);
        
        
        //IE code
		/*System.setProperty("webdriver.ie.driver", "c:/IEDriverServer.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 5000);*/
        //dr = new  InternetExplorerDriver(capabilities);
		
		//browserVersion
		
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dr.manage().deleteAllCookies();
		dr.manage().window().maximize();
		
		dr.navigate().to("https://ipindiaonline.gov.in/eregister/eregister.aspx");
		System.out.println("Broser Started.....");
        
	}
	
	private boolean addParty(String name){
		try{
			Party party = new Party();
			party.setParty_name(name);
			if (party!=null){
				/*if (common.checkPartyExists(party)){
					return true;
				}*/
				if (party.getId()==0){
					common.saveParty(party);
					return true;
				}
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	
	
	public void getData(){
		try{
			WebElement we =dr.findElement(By.xpath(".//*[@id='print_area1']/table[3]"));
			we =dr.findElement(By.xpath(".//*[@id='print_area1']/table[3]"));
			//System.out.println(we.toString());
			List<WebElement> list_rows =  we.findElements(By.tagName("a"));
			//System.out.println(list_rows.size());
			String mainWindow = dr.getWindowHandle();
			String str =","+ mainWindow+",";
			boolean firstTime=true;
			for (WebElement tmp_we: list_rows){
				try{
					System.out.println(tmp_we.getText());
					addParty(tmp_we.getText());
					//System.out.println("Url visibled1: "+tmp_we.isDisplayed());
					tmp_we.click();
					if (firstTime){
						tmp_we.click();
						firstTime=false;
					}
					Set<String> handles = dr.getWindowHandles();

					for (String handle : handles) {
						if (!str.contains(","+handle+",")) {
							str+=handle+",";
							dr.switchTo().window(handle);
							break;
						}
					}

					we =dr.findElement(By.xpath(".//*[contains(@id, 'print_area')]"));
					String tender_text =we.getText();
					tender_text = tender_text.replace("Lot No. &", "Lot No &");
					//System.out.println(tender_text);
					String[] lines = tender_text.split("\n");
					String companyName,emd,purCapacity,bidCapacity;
					boolean header=true;
					for (String cur_line : lines ){

						if (header){
							if (cur_line.contains("Company Name : ")){
								companyName=cur_line.replace("Company Name : ", "");
							}else if (cur_line.contains("EMD : ")){
								emd=cur_line.replace("EMD : ", "");
							}else if (cur_line.contains("Purchase Capacity : ")){
								purCapacity=cur_line.replace("Purchase Capacity : ", "");
							}else if (cur_line.contains("Bidding Capacity : ")){
								bidCapacity=cur_line.replace("Bidding Capacity : ", "");
								header=false;
							}
						}

						if (cur_line.contains("Lot No.")){
							cur_line = cur_line.replace("  ", " ");
							String[] arr_col = cur_line.split(" ");
							/*System.out.println( "Priority: "+arr_col[0]);
	        				System.out.print( "Lot No: "+arr_col[2]);
	        				System.out.print( "Qty: "+arr_col[4]);
	        				System.out.print( "rate: "+arr_col[6]);
	        				System.out.print( "Amt: "+arr_col[7]);*/

						}
					}
					dr.close();
					dr.switchTo().window(mainWindow);

				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
}


