package mp.procurement;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.StringReader;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import mp.procurement.email.ApplicationMailer;
import mp.procurement.model.Bid;
import mp.procurement.model.Financials;
import mp.procurement.model.Lot;
import mp.procurement.model.LotData;
import mp.procurement.model.LotList;
import mp.procurement.model.LotRate;
import mp.procurement.model.Party;
import mp.procurement.model.PartyLots;
import mp.procurement.model.ReportBean;
import mp.procurement.model.ResultBid;
import mp.procurement.model.SessionUser;
import mp.procurement.model.Tender;



@Controller
@Component
@SessionScope
public class MainPage {

	@Autowired
	CommonService common;
	@Autowired
	ApplicationMailer mailer; 
	WebDriver dr=null;	
	private static OnlineFormJava2 onlineForm = null;
	
	@Autowired
    private SessionUser sessionUser;
	
	//private String requiredMac="E8-39-DF-3E-EE-77";
	
	
	//kalpesh bhai
	//private String requiredMac="78-84-3C-EC-43-28";
	
	//kalpesh bhai
	//private String requiredMac="34-97-F6-9E-9B-4E";
	
	//private String requiredMac="00:15:5d:7b:ff:4d";
	//Kalpesh bhai Dell
	//private String requiredMac= "58-8A-5A-4A-70-6D";

	//Dell
	private String requiredMac= "8C-70-5A-73-D9-AC";
	
	//karan bhai..
	//private String requiredMac= "28-C6-3F-DE-43-4C"; 
 
	private HashMap<String,Integer> map_lot = new HashMap<String,Integer>();
	private HashMap<Integer,Integer> map_party = new HashMap<Integer,Integer>();
	private static int stateId;
	
	
	
	@RequestMapping("/adminHomeDashboard")
	public ModelAndView showMessage() {
		
		common.addActivity(sessionUser.getUserId(), "adminHomeDashboard", System.currentTimeMillis());
		if ((checkLiscence() && checkMac())  || true){
			ModelAndView model = new ModelAndView("home");
			model.addObject("tenderList", common.getAllTender());
			model.addObject("divisionList", common.getAllDivision());
			model.addObject("partyList", common.getAllParty());
			model.addObject("yearList", common.getYearList());
			
			//common.getAllTender()
			return model;
		}else{
			ModelAndView model = new ModelAndView("licenseError");
			return model;
		}
	}
	
	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		common.addActivity(sessionUser.getUserId(), "dashboard", System.currentTimeMillis());
			ModelAndView model = new ModelAndView("dashboard");
			return model;
	}
	
	@RequestMapping("/tenderMpDashboard")
	public ModelAndView tenderMpDashboard() {
		common.addActivity(sessionUser.getUserId(), "tenderMpDashboard", System.currentTimeMillis());

		List<Map<String, String>> tenderList = (List<Map<String, String>>) common.getTenderList(1);
		
		ModelAndView model = new ModelAndView("tenderDashboard");
		
		model.addObject("tenderList", tenderList);
		model.addObject("state", 1);
		return model;
	}
	
	@RequestMapping("/tenderCgDashboard")
	public ModelAndView tenderCgDashboard() {
		common.addActivity(sessionUser.getUserId(), "tenderCgDashboard", System.currentTimeMillis());

		List<Map<String, String>> tenderList = (List<Map<String, String>>) common.getTenderList(2);
		
		ModelAndView model = new ModelAndView("tenderDashboard");
		
		model.addObject("tenderList", tenderList);
		model.addObject("state", 2);
		return model;
	}
	
	@RequestMapping("/lotMpDashboard")
	public ModelAndView lotMpDashboard() {
		common.addActivity(sessionUser.getUserId(), "lotMpDashboard", System.currentTimeMillis());
		
		List<Lot> lotList = (List<Lot>) common.getAllLot(1);
		
		ModelAndView model = new ModelAndView("lotDashboard");
		
		model.addObject("lotList", lotList);
		model.addObject("state", 1);
		return model;
	}
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public String changePassword(String pwd, String newPwd) {
		common.addActivity(sessionUser.getUserId(), "changePassword", System.currentTimeMillis());

		try{
			Party cur_party = common.getParty(sessionUser.getUserId());
			if (newPwd ==null || "".equals(newPwd)){
				return "New password can not be blank";
			}else if ( cur_party.getPassword().equals(pwd) ){
				cur_party.setPassword(newPwd);
				common.updateParty(cur_party);
			}else{
				return "Old password is wrong!";
			}
			
			return "Pass changes successfully.";
		}catch(Exception ex){
			return "There is some error while updating password.";
		}
	}
	
	@RequestMapping("/lotCgDashboard")
	public ModelAndView lotCgDashboard() {
		common.addActivity(sessionUser.getUserId(), "lotCgDashboard", System.currentTimeMillis());

		List<Lot> lotList = (List<Lot>) common.getAllLot(2);
		
		ModelAndView model = new ModelAndView("lotDashboard");
		
		model.addObject("lotList", lotList);
		model.addObject("state", 2);
		return model;
	}
	
	@RequestMapping("/chartData")
	public @ResponseBody Object chartData(int state_id) {
		
		List<Map<String, Integer>> yearWiseRates = (List<Map<String, Integer>>) common.getYearWiseMinMaxAvgRate(state_id);
		List<Map<String, Double>> yearWiseAmount = (List<Map<String, Double>>) common.getYearWiseAmount(state_id);
		HashMap<String,Object> resp = new HashMap<String,Object>();
	
		resp.put("yearWiseRates", yearWiseRates);
		resp.put("yearWiseAmount", yearWiseAmount);
		return resp;
	}
	
	@RequestMapping("/tenderData")
	public @ResponseBody Object tenderData(int tender_id) {
		
		Map<String, Integer> rates = common.getMinMaxAvgRate(tender_id);
		List<Map<String, Double>> divisionWiseRates = (List<Map<String, Double>>) common.getDivisionWiseRates(tender_id);
		List<Map<String, Double>> top5Bidder = (List<Map<String, Double>>) common.getTop5Bidder(tender_id);
		List<Map<String, Double>> top5Lot = (List<Map<String, Double>>) common.getTop5Lot(tender_id);
		List<Map<String, Integer>> lotCounts = (List<Map<String, Integer>>) common.getLotCountRangeWise(tender_id);
		List<Map<String, String>> partyList = (List<Map<String, String>>) common.getPartyByTender(tender_id);
		HashMap<String,Object> resp = new HashMap<String,Object>();
		resp.put("divisionWiseRates", divisionWiseRates);
		resp.put("top5Bidder", top5Bidder);
		resp.put("top5Lot", top5Lot);
		resp.put("lotCounts", lotCounts);
		resp.put("partyList", partyList);
		resp.put("minRate", rates.get("minrate"));
		resp.put("maxRate", rates.get("maxrate"));
		resp.put("avgRate", rates.get("avgrate"));
		resp.put("lotCount", rates.get("lotcount"));
		return resp;
	}
	
	@RequestMapping("/lotData")
	public @ResponseBody Object lotData(int lot_id) {
		Map<String, Integer> rates = common.getLotMinMaxAvgRate(lot_id);
		List<Map<String, Integer>> yearWiseRates = (List<Map<String, Integer>>) common.getLotYearWiseMinMaxAvgRate(lot_id);
		List<Map<String, Integer>> yearWiseLot = (List<Map<String, Integer>>) common.getLotYearWiseCount(lot_id);
		HashMap<String,Object> resp = new HashMap<String,Object>();
		resp.put("yearWiseRates", yearWiseRates);
		resp.put("yearWiseLot", yearWiseLot);
		resp.put("minRate", rates.get("minrate"));
		resp.put("maxRate", rates.get("maxrate"));
		resp.put("avgRate", rates.get("avgrate"));
		resp.put("lotCount", rates.get("lotcount"));
		return resp;
	}
	
	
	@RequestMapping("/lotRate")
	public ModelAndView displayLotRate() {
		ModelAndView model = new ModelAndView("lotRate");
		List<Lot> lot_name = common.getAllLot(1);
		List<LotList> lot_list = common.getLotList();
		model.addObject("lot_name", lot_name);
		model.addObject("lot_list", lot_list);
		return model;
	}
	
	@RequestMapping("/lotBids")
	@ResponseBody
	public List<LotData> getLotBids(int lotId) {
		
		return common.getLotData(lotId);
	}
	
	@RequestMapping("/tenderLotDetails")
	@ResponseBody
	public List<ResultBid> getTenderLotDetails(int lotId, int tenderId) {
		
		return common.getLotDetailsTenderWise(lotId, tenderId);
	}
	
	@RequestMapping("/tenderBids")
	@ResponseBody
	public List<ResultBid> getTenderBids(int tenderId) {
		return common.getTederBids(tenderId);
	}
	
	@RequestMapping("/tenderYearBids")
	@ResponseBody
	public  List<Map<String,Object>> getTenderYearBids(int year, int state) {
		return common.getTederYearBids(year, state);
	}
	
	@RequestMapping("/partyBids")
	@ResponseBody
	public  List<Map<String,Object>> getPartyBids(int tenderId, int partyId) {
		return common.getPartyBids(tenderId, partyId);
	}
	
	@RequestMapping("/showTenderResult")
	@ResponseBody
	public  List<Map<String,Object>> showTenderResult(int tenderId) {
		return common.getTenderResultDisplay(tenderId);
	}
	
	@RequestMapping("/myBids")
	@ResponseBody
	public  List<Map<String,Object>> myBids(int tenderId) {
		return common.getPartyBids(tenderId,sessionUser.getUserId());
	}
	
	@RequestMapping("/myLots")
	@ResponseBody
	public  List<Map<String,Object>> myLots(int tenderId) {
		return common.getTenderResultDisplay(tenderId);
	}
	
	
	@RequestMapping(value="/lotRates")
	@ResponseBody
	public List<LotRate> getLotRates(int lotId) {
		return common.getLotRate(lotId);
	}
	
	@RequestMapping(value="/updateTenderResult",method=RequestMethod.POST)
	@ResponseBody
	public void updateTenderResult(@RequestBody String json) {
		JsonObject json_obj = new Gson().fromJson(json, JsonObject.class); 
		JsonElement ele_bids =  json_obj.get("bid_ids");
		int  year =  json_obj.get("year").getAsInt();
		int  state =  json_obj.get("state").getAsInt();
		Integer[] bid_ids  = new Gson().fromJson(ele_bids, Integer[].class);
		common.updateResult(bid_ids, year,state);
		//return common.getLotRate(lotId);
	}
	
	
	
	@RequestMapping("/ExportLotRates")
	@ResponseBody
	public ModelAndView getExportLotRates(int lotId) {
		List<LotRate> list = common.getLotRate(lotId);
		List<Lot> lot_list = common.getAllLot(1);
		HashMap<Integer,String> hashMap = new HashMap<Integer,String>();
		
		for (Lot l : lot_list){
			hashMap.put(l.getId(), l.getLot_given_name());
		}
		ModelAndView model = new ModelAndView("lotDataExcel");
		model.addObject("listRate", list);
		model.addObject("lot_map", hashMap);
		return model; 
		 
	}
	
	@RequestMapping(value="/saveLotData",method=RequestMethod.POST )
	@ResponseBody
	public String saveLotData(@RequestBody String json) {
		try{
			List<LotRate> list_lotRate = new Gson().fromJson(json,new TypeToken<List<LotRate>>(){}.getType());
			if (list_lotRate!=null){
				boolean isDeleted=false;
				for (LotRate lotRate : list_lotRate){
					if (!isDeleted){
						common.deleteLotDataByList(lotRate.getList_id());
						isDeleted=true;
					}
					common.saveLotData(lotRate);
				}
			}
		}catch(Exception ex){
			System.out.println("exception: "+ ex);
			return "Exception";
		}
        return "{status:success}";
	}
	
	@RequestMapping(value="/saveListName",method=RequestMethod.POST)
	@ResponseBody
	public int saveListName(String listName) {
		try{
		if (listName!=null){
			LotList lotList = new LotList();
			lotList.setName(listName);
			return common.saveLotList(lotList);	
		}
		}catch(Exception ex){
			System.out.println(ex);
		}
		return 0;
	}
	
	@RequestMapping("/tenderResult")
	public ModelAndView getTenderResult(int tenderId) {
		ModelAndView mv = new ModelAndView("tenderResult");
		Tender tender = common.getTender(tenderId);
		List<ReportBean> list_report= new ArrayList<ReportBean>();
		try {
			list_report = common.getTenderResult(tenderId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tenderName = String.format("%s", tender.getTender_name()); 
		mv.addObject("tenderName", tenderName);
		mv.addObject("reportBean", list_report);
		return mv;
	}
	
	@RequestMapping("/result")
	public ModelAndView resultProcessing(int tenderId, @RequestParam(required=false, defaultValue="0") double minRate,
			@RequestParam(required=false, defaultValue="orderBy") String orderBy,
			@RequestParam(required=false, defaultValue="false") boolean resetCancel,
			@RequestParam(required=false, defaultValue="100") double capingRate) {
		Tender tender = new Tender();
		tender.setId(tenderId);
		try {
			//common.resultProcessByTender_new(tender);
			common.resultProcessByTender(tender, minRate, orderBy,resetCancel, capingRate);
			
			Tender tender_1 = common.getTender(tenderId);
			tender_1.setLast_result_process(new Date());
			tender_1.setMin_rate(minRate);
			common.updateTender(tender_1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return new ModelAndView("home");
	}
	

	@RequestMapping("/startBrowser")
	public ModelAndView startBrowser(int type) {
		if ((checkLiscence() && checkMac())   || true){
			stateId=type;
			try {
				startBrow(type);
			} catch (Throwable e) {
				System.out.println("Start Browser: " + e);
			}
			return new ModelAndView("dataCollection");
		}
		else{
			return new ModelAndView("licenseError");
		}
	}
	
	@RequestMapping("/partyReport")
	public ModelAndView getPartyReport(int stateNo,String json) {
		JsonObject json_obj = new Gson().fromJson(json, JsonObject.class); 
		JsonElement ele_party =  json_obj.get("party_id");
		JsonElement ele_tender =  json_obj.get("tender_id");
		Integer[] party_ids  = new Gson().fromJson(ele_party, Integer[].class);
		Integer[] tender_ids  = new Gson().fromJson(ele_tender, Integer[].class);
		List<ReportBean> list_report = common.getReport("PARTY",party_ids,tender_ids, stateNo);
		return new ModelAndView("excelView", "reportBean", list_report);
	}
	
	@RequestMapping(value="/lotReport",method=RequestMethod.GET)
	public ModelAndView getLotReport(HttpServletRequest request) {
		/*if (!checkLiscence() || !checkMac()  ){
			return new ModelAndView("licenseError");
		}*/
		String chk = request.getParameter("chk");
		String to_email = request.getParameter("emailId");
		String[] arr_ids = chk.split(":");
		List<ReportBean> list_report = common.getReport("LOT",arr_ids,-1);
		
		HSSFWorkbook workbook = new HSSFWorkbook(); 
		new ExcelHelper().getLotReport(workbook, list_report);
		try{
			// Creating the directory to store file
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator + "tmpFiles");
			if (!dir.exists())
				dir.mkdirs();
	
			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath()
					+ File.separator + System.nanoTime()+".xls");
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			workbook.write(stream);
			stream.close();
			if (to_email!=null && !"".equals(to_email)){
				mailer.sendMail(to_email, "Lot Wise Report", "Please find the attachment for Lot wise data. ",serverFile);
				
			}
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		return new ModelAndView("lotReportExcel", "reportBean", list_report);
	}
	
	@RequestMapping(value="/yearWiseLotRate",method=RequestMethod.GET)
	public ModelAndView getYearWiseLotRate(HttpServletRequest request,@RequestParam Integer state) {
		/*if (!checkLiscence() || !checkMac()  ){
			return new ModelAndView("licenseError");
		}*/
		
		List<Map<String,Object>> list_report = common.getLotRatesYearWise(state);
		
		/*HSSFWorkbook workbook = new HSSFWorkbook(); 
		new ExcelHelper().getLotReport(workbook, list_report);
		try{
			// Creating the directory to store file
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator + "tmpFiles");
			if (!dir.exists())
				dir.mkdirs();
	
			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath()
					+ File.separator + System.nanoTime()+".xls");
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			workbook.write(stream);
			stream.close();
			if (to_email!=null && !"".equals(to_email)){
				mailer.sendMail(to_email, "Lot Wise Report", "Please find the attachment for Lot wise data. ",serverFile);
				
			}
			
		}catch(Exception ex){
			System.out.println(ex);
		}*/
		try{
		return new ModelAndView(new LotRateYearWiseExcelBuilder(), "data",list_report );
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/lotReportAllowed")
	@ResponseBody
	public boolean lotReportAllowed(HttpServletRequest request, int lot_id) {
		try{
			if (common.checkPartyLotExists(sessionUser.getUserId(), lot_id)){
				return true;
			} else {
				if (sessionUser.getLot_allowed() > sessionUser.getLot_consumed()) {
					return true;
				} else {
					return false;
				}
			}
		}catch(Exception ex){
			
		}
		return false;
	}
	
	@RequestMapping(value="/lotReportForYear")
	public ModelAndView lotReportForYear(HttpServletRequest request) {
		List<ReportBean> list_report =new ArrayList<ReportBean>();
		try{
		String chk = request.getParameter("chk");
		String to_email = request.getParameter("emailId");
		String[] arr_ids = chk.split(":");
		int state= Integer.parseInt(request.getParameter("state"));
		int yearcount = Integer.parseInt(request.getParameter("yearCount"));
		
		boolean reportAllowed=false;
		if (common.checkPartyLotExists(sessionUser.getUserId(), Integer.parseInt(arr_ids[0]))){
			reportAllowed=true;
		}else{
			if (sessionUser.getLot_allowed() > sessionUser.getLot_consumed()){
				PartyLots partyLots = new PartyLots();
				partyLots.setParty_id(sessionUser.getUserId());
				partyLots.setLot_id( Integer.parseInt(arr_ids[0]));
				common.savePartyLots(partyLots);
				List<PartyLots> list_partyLots =  common.getPartyLots(sessionUser.getUserId());
				sessionUser.setParty_lots(list_partyLots);
				sessionUser.setLot_consumed(list_partyLots.size());
				reportAllowed=true;
			}
		}
		if (reportAllowed){
		 list_report = common.getReport("LOT",arr_ids,state,yearcount);
			return new ModelAndView("lotReportExcel", "reportBean", list_report);
		}else{
			return new ModelAndView("errorPage");
		}
		
		/*HSSFWorkbook workbook = new HSSFWorkbook(); 
		new ExcelHelper().getLotReport(workbook, list_report);
		try{
			// Creating the directory to store file
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator + "tmpFiles");
			if (!dir.exists())
				dir.mkdirs();
	
			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath()
					+ File.separator + System.nanoTime()+".xls");
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			workbook.write(stream);
			stream.close();
			if (to_email!=null && !"".equals(to_email)){
				mailer.sendMail(to_email, "Lot Wise Report", "Please find the attachment for Lot wise data. ",serverFile);
			}
			
		}catch(Exception ex){
			System.out.println(ex);
		}*/
		}catch(Exception ex){
			
		}
								 
		return new ModelAndView("errorPage");
	}
	
	@RequestMapping(value="/emailSend",method= RequestMethod.GET )
	public String emailSend() {
		
		List<Party> list_party = common.getAllParty();
		String body = "<html> <head></head> <body> Hello<br><br> Thank you for your feedback and support, we have designed a dedicated web portal for Tendu Patta Tenders.   <br><br> To login on portal please use the following url and user id and password. <BR> URL: <a href=\"http://infosane.co.in:8080/tender/login\"> http://infosane.co.in:8080/tender/login </a>"+
				"<br> login Id: ##login_id## <br> password: ##password##  <br><br> We request you to please change the password once you login with given login id and password.  <br><br> Thanks & Regards <br> Infosane,Nagpur<br>Vivek Agrawal<br>cell: 8989000400,7000384248<br>Email:vivek.agrawal@infosane.co.in </body></html> ";
		boolean start=false;
		List<String> list_to = new ArrayList<String>();
		
		int count=0;
		for (Party p : list_party){
			try{
				list_to= new ArrayList<String>();
				if (p.getEmail()!=null && !"".equals(p.getEmail())){
							list_to.add(p.getEmail());
							count++;
				
				
					//if (count==50){
					HashMap<String,String> map = new HashMap<String, String>();
					map.put("login_id", p.getEmail());
					map.put("password", p.getPassword());
						mailer.sendHTMLMail(list_to, "Tendu Patta: Dedicated Web Portal ", body,null, map);
						count=0;
				}
				
			}catch(Exception ex){
				
			}
		}
		/*list_to.add("vivekraj.agrawal@gmail.com");
		mailer.sendHTMLMail(list_to, "Tendu Patta: Dedicated Web Portal ", body,null);*/
		
		return "0";
	}
	
	@RequestMapping("/dataCollection")
	public ModelAndView dataCollection(Integer type, @RequestParam(required=false) Boolean partyData) {
		
		if (type==null){
			type=stateId;
		}
		try {
			if (type==1){
				if (partyData!=null && partyData){
					getPatyDetails();
				}else{
					getMPData();
				}
					
			}else if (type==2){
				if (partyData!=null && partyData){
					getPatyDetails();
				}else{
					getMPData();
				}
			}else if (type==3){
				getEtrad();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("dataCollection");
	}
	
	@RequestMapping("/fillForm")
	public ModelAndView fillForm() {
		System.out.println("started fill form");
		try {
			FillForm obj = new FillForm();

			//WebDriver dr;
				//dr = obj.startBrowser();

			// String filePath = "/home/vivek/Downloads/";
			String filePath = "D:\\";

			// Call read file method of the class to read data

			List data = obj.readExcel(filePath, "data.xls", "Sheet2");
			System.out.println("data sizee" + data.size());
			Thread.sleep(10000);

			obj.fillData(data, dr);
			System.out.println("form fillup completed....");
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("dataCollection");
	}
	
	
	
	private void startBrow(int stateId) throws Throwable {
		System.out.println("#####" + System.currentTimeMillis());
		try{
			
		System.setProperty("webdriver.ie.driver", "c:/IEDriverServer.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	
		
		
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 30000);
		capabilities.setCapability("logFile", "C:\\ielog.log");
		capabilities.setCapability("logLevel", "TRACE");
		
		//browserVersion
		dr = new  InternetExplorerDriver(capabilities);
		
		
		System.out.println("before Maximize");
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dr.manage().deleteAllCookies();
		dr.manage().window().maximize();
		System.out.println("post Maximize");
		if (stateId==1){
			dr.navigate().to("https://mfpfederation.abcprocure.com/");
		}else if (stateId==2){
			dr.navigate().to("https://cgmfpfed.abcprocure.com/");
		}
		System.out.println("driver created");
		Set<String> handles_1 = dr.getWindowHandles();
		for (String handle : handles_1) {
				System.out.println(System.nanoTime()+"  " + handle);
				dr.switchTo().window(handle);
		}
		
		
		System.out.println("Broser Started.....");
		}catch (Exception ex){
			ex.printStackTrace();
			System.out.println(System.currentTimeMillis() + "  :" + ex.getStackTrace());
			
		}
	}
	
	private int addParty(String name,int bidder_id){
		int party_id;
		try{
			if (!map_party.containsKey(bidder_id)){
				Party party = new Party();
				party.setParty_name(name);
				party.setBidder_id(bidder_id);
				party.setStatus(true);
				
				party_id = common.checkPartyExists(party);
				if (party_id==-1){
					party_id=common.saveParty(party);
				}
				map_party.put(bidder_id, party_id);
				return party_id;
			}else{
				return map_party.get(bidder_id);
			}
		}catch(Exception ex){
			return 0;
		}
	}
	
	public void getEtrad(){
		String mainWindow = dr.getWindowHandle();
		String str =","+ mainWindow+",";
		for (int itr =0 ; itr<=50;itr++){
			WebElement we =dr.findElement(By.id("comp"+itr));
			if (we!=null){
				String val = we.getAttribute("value");
				System.out.println(val);
			}
		}

	}
	
	public void getCGData(){
		String mainWindow = dr.getWindowHandle();
		String str =","+ mainWindow+",";
		boolean firstTime=true;
		boolean tmp_skip=true;
		boolean skip_close=true;
		try{
			
			int tender_id=1;
			int party_id;
			WebElement we =dr.findElement(By.xpath("//a[text()='(HTML)']"));
			String page_text = we.getText();
			
			
			Set<String> handles_1 = dr.getWindowHandles();
			for (String handle : handles_1) {
				if (!handle.equals(mainWindow)){
					dr.switchTo().window(handle);
					dr.close();
				}
			}
			
			dr.switchTo().window(mainWindow);
			
			we.click();
			
			Set<String> handles = dr.getWindowHandles();

			for (String handle : handles) {
				if (!str.contains(","+handle+",")) {
					str+=handle+",";
					dr.switchTo().window(handle);
					skip_close=true;
					break;
				}
			}

			we =dr.findElement(By.tagName("Body"));
			page_text = we.getText();
			System.out.println(page_text);
			
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public void getMPData(){
		try{
			
			int tender_id=1;
			int party_id;
			WebElement we =dr.findElement(By.xpath(".//*[@id='print_area1']"));
			String page_text = we.getText();
			
			
			int startIdx = page_text.indexOf("Tender No : ");
			int endIdx = page_text.indexOf("\n", startIdx);
			String titleName = page_text.substring(startIdx, endIdx);
			titleName=titleName.replace("Tender No : ", "");			
			startIdx = page_text.indexOf("Tender Id : ");
			endIdx = page_text.indexOf(" Due date & time ", startIdx);
			String tenderId = page_text.substring(startIdx, endIdx);
			tenderId=tenderId.replace("Tender Id : ", "");
			Tender tender = new Tender();
			tender.setTender_name(titleName+"-"+tenderId);
			tender.setStatus(true);
			tender.setState(stateId);
			tender_id = common.checkTenderExist(tender);
			if (tender_id==-1){
				tender_id= common.saveTender(tender);
				
			}
			tender.setId(tender_id);
			HashMap<Integer, Integer> party_bids = common.getPartyBidCount(tender_id);
			/*if (common.getBidCount(tender)>0){
				common.deleteBidByTender(tender);
			}*/
			
			we =dr.findElement(By.xpath(".//*[@id='print_area1']/table[3]"));
			we =dr.findElement(By.xpath(".//*[@id='print_area1']/table[3]"));
			//System.out.println(we.toString());
			List<WebElement> list_rows =  we.findElements(By.tagName("a"));
			System.out.println(we.getText());
			//System.out.println(list_rows.size());
			String mainWindow = dr.getWindowHandle();
			String str =","+ mainWindow+",";
			boolean firstTime=true;
			boolean tmp_skip=true;
			for (WebElement tmp_we: list_rows){
				int count=1;
				while(count<=3 ){
				boolean skipclose=false;
				/*if (tmp_skip){
					if (tmp_we.getText().equals("MOHD.ISMAIL&SONS")){
						tmp_skip=false;
					}
					count=4;
					continue;
				}*/
				try{
					//close all child window for safety....
					Set<String> handles_1 = dr.getWindowHandles();
					for (String handle : handles_1) {
						if (!handle.equals(mainWindow)){
							dr.switchTo().window(handle);
							dr.close();
						}
					}
					dr.switchTo().window(mainWindow);
					String url = tmp_we.getAttribute("href");
					
					int idx_bidder = url.indexOf("&BidderId=");
					int idx_fromWhere = url.indexOf("&FromWhere=");
					
					int bidder_id = Integer.parseInt( url.substring(idx_bidder, idx_fromWhere).replace("&BidderId=", ""));

					//System.out.println(tmp_we.getText());
					
					party_id = addParty(tmp_we.getText(),bidder_id);
					
					/*if (tmp_skip){
						if (party_id==1251){
							tmp_skip=false;
						}
						continue;
					}*/
					Party party = new Party();
					party.setId(party_id);
					if (party_bids.containsKey(party.getId()) && party_bids.get(party.getId())>0){
					//if (common.getBidCount(tender,party)>0 ){
						System.out.println("Data Found for Party: " );
						count=4;
						continue;
					}
					
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
							skipclose=true;
							break;
						}
					}
					
					
					we =dr.findElement(By.xpath(".//*[contains(@id, 'print_area')]"));
					String tender_text =we.getText();
					tender_text = tender_text.replace("Lot No. &", "Lot No &");
					tender_text = tender_text.replace("Lot. No.", "Lot No");
					
					System.out.println(tender_text);
					String[] lines = tender_text.split("\n");
					String companyName;
					Double emd=0.0,purCapacity=0.0,bidCapacity=0.0;
					boolean header=true;
					List<Bid> list_bids = new ArrayList<Bid>();
					Financials financials = new Financials();
					boolean processlot=false;
					boolean restart=true;
					for (String cur_line : lines ){

						if (header){
							processlot=false;
							restart=true;
							if (cur_line.contains("Company Name : ")){
								companyName=cur_line.replace("Company Name : ", "");
							}else if (cur_line.contains("EMD : ")){
								emd=Double.parseDouble(cur_line.replace("EMD : ", ""));
							}else if (cur_line.contains("Purchase Capacity : ")){
								purCapacity=Double.parseDouble(cur_line.replace("Purchase Capacity : ", ""));
							}else if (cur_line.contains("Bidding Capacity : ")){
								bidCapacity=Double.parseDouble(cur_line.replace("Bidding Capacity : ", ""));
								financials.setParty_id(party_id);
								financials.setTender_id(tender_id);
								financials.setEmd(emd);
								financials.setPurchase_capacity(purCapacity);
								financials.setBid_capacity(bidCapacity);
								header=false;
							}
						}

						if (cur_line.contains("(In Standard Bags)*Purchase Rate per S.B. (In Rs.)") ){
							processlot=true;
							continue;
						}
						if (cur_line.contains("TOTAL") ){
							processlot=false;
							restart=false;
							continue;
						}
						if (processlot && restart ){
							cur_line = cur_line.replace("2017 - Lot No.", " ");
							cur_line = cur_line.replace("Lot. No.", " ");
							cur_line = cur_line.replace("Lot No.", " ");
							cur_line = cur_line.replace("S B.", "SB.");
							cur_line = cur_line.replace("S.B.", " ");
							cur_line = cur_line.replace("SB.", " ");
							cur_line = cur_line.replace("SB", " ");
							cur_line = cur_line.replace("S.B", " ");
							cur_line = cur_line.replace("Quantity", " ");
							cur_line = cur_line.replace("-", " ");
							cur_line = cur_line.replace("    ", " ");
							cur_line = cur_line.replace("   ", " ");
							cur_line = cur_line.replace("  ", " ");
							String[] arr_col = cur_line.split(" ");
							if (arr_col[0].equals("NIL")){
								continue;
							}
							int priority = Integer.parseInt(arr_col[0]);
							String lotName =  arr_col[1];
							int qty = (int)Double.parseDouble(arr_col[2]);
							double rate = Double.parseDouble(arr_col[3]);
							double amount =Double.parseDouble(arr_col[4]);
							int lot_id=0;
							if (!map_lot.containsKey(lotName)){
								Lot lot = new Lot();
								lot.setLot_name(lotName);
								lot.setLot_given_name(lotName);
								lot.setQty(qty);
								lot.setSerial_number(0);
								lot.setState(stateId);
								lot_id=common.checkLotExists(lot);
								if (lot_id==-1){
									lot_id=common.saveLot(lot);	
								}
								map_lot.put(lotName, lot_id);
							}else{
								lot_id=map_lot.get(lotName);
							}
							Bid bid = new Bid();
							bid.setTender_id(tender_id);
							bid.setLot_id(lot_id);
							bid.setParty_id(party_id);
							bid.setRound_number(1);
							bid.setPriority(priority);
							bid.setQty(qty);
							bid.setRate(rate);
							bid.setAmount(amount);
							list_bids.add(bid);
						}
					}
					common.saveFinancials(financials);
					common.saveMultipleBids(list_bids);
					
					count=4;
				}catch(Exception ex){
					ex.printStackTrace();
					System.out.println(ex);
					count++;
				}finally{
					if (skipclose){
						dr.close();
						dr.switchTo().window(mainWindow);
					}
				}
				}
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public void getPatyDetails(){
		try{
			
			int tender_id=1;
			int party_id;
			WebElement we =dr.findElement(By.xpath(".//*[@id='print_area1']"));
			String page_text = we.getText();

			int startIdx = page_text.indexOf("Tender No : ");
			int endIdx = page_text.indexOf("\n", startIdx);
			String titleName = page_text.substring(startIdx, endIdx);
			titleName=titleName.replace("Tender No : ", "");			
			startIdx = page_text.indexOf("Tender Id : ");
			endIdx = page_text.indexOf(" Due date & time ", startIdx);
			String tenderId = page_text.substring(startIdx, endIdx);
			tenderId=tenderId.replace("Tender Id : ", "");
			Tender tender = new Tender();
			tender.setTender_name(titleName+"-"+tenderId);
			tender.setStatus(true);
			tender.setState(stateId);
			tender_id = common.checkTenderExist(tender);
			if (tender_id==-1){
				tender_id= common.saveTender(tender);
				
			}
			tender.setId(tender_id);
			
			/*if (common.getBidCount(tender)>0){
				common.deleteBidByTender(tender);
			}*/
			
			we =dr.findElement(By.xpath(".//*[@id='print_area1']/table[3]"));
			we =dr.findElement(By.xpath(".//*[@id='print_area1']/table[3]"));
			//System.out.println(we.toString());
			List<WebElement> list_rows =  we.findElements(By.tagName("a"));
			System.out.println(we.getText());
			//System.out.println(list_rows.size());
			String mainWindow = dr.getWindowHandle();
			String str =","+ mainWindow+",";
			boolean firstTime=true;
			boolean tmp_skip=true;
			for (WebElement tmp_we: list_rows){
				int count=1;
				while(count<=3 ){
				boolean skipclose=false;
				/*if (tmp_skip){
					if (tmp_we.getText().equals("MOHD.ISMAIL&SONS")){
						tmp_skip=false;
					}
					count=4;
					continue;
				}*/
				try{
					//close all child window for safety....
					Set<String> handles_1 = dr.getWindowHandles();
					for (String handle : handles_1) {
						if (!handle.equals(mainWindow)){
							dr.switchTo().window(handle);
							dr.close();
						}
					}
					dr.switchTo().window(mainWindow);
					String url = tmp_we.getAttribute("href");
					
					int idx_bidder = url.indexOf("&BidderId=");
					int idx_fromWhere = url.indexOf("&FromWhere=");
					
					int bidder_id = Integer.parseInt( url.substring(idx_bidder, idx_fromWhere).replace("&BidderId=", ""));

					//System.out.println(tmp_we.getText());
					
					party_id = addParty(tmp_we.getText(),bidder_id);
					
					/*if (tmp_skip){
						if (party_id==1251){
							tmp_skip=false;
						}
						continue;
					}*/
					Party party = new Party();
					party.setId(party_id);
					
					
					
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
							skipclose=true;
							break;
						}
					}
					
					Party tmp_party = common.getParty(party_id);
					if (tmp_party!=null && tmp_party.getEmail()==null){
						we =dr.findElement(By.xpath(".//*[contains(@id, 'print_area')]/table[1]/tbody[1]/tr[1]/td[1]/b/a"));
						String tmp_Text = we.getText();
						//System.out.println("####: "+ tmp_Text);
						we.click();
						Set<String> handles_11 = dr.getWindowHandles();
						for (String handle : handles_11) {
							if (!str.contains(","+handle+",")) {
								str+=handle+",";
								dr.switchTo().window(handle);
								skipclose=true;
								break;
							}
						}
						we =dr.findElement(By.xpath(".//*[contains(@id, 'myTable')]"));
						String tmp_details = we.getText().replace("User and Company Detail","").replace("Company Information","").replace("Company Name","").replace("Email Address",";").replace("Permanent Address",";").replace("State",";").replace("City",";").replace("Phone No1",";").replace("Phone No2",";").replace("\n", "");
						String[] tmp_array= tmp_details.split(";");
						tmp_party.setEmail(tmp_array[1]);
						tmp_party.setAddress(tmp_array[2]);
						tmp_party.setState(tmp_array[3]);
						tmp_party.setCity(tmp_array[4]);
						if (tmp_array.length>=6){
						tmp_party.setPhone1(tmp_array[5]);
						}
						if (tmp_array.length>=7){
							tmp_party.setPhone2(tmp_array[6]);
						}
						
						common.updateParty(tmp_party);

						System.out.println(tmp_details);
					}
					count=4;
				}catch(Exception ex){
					System.out.println(ex);
					count++;
				}finally{
					if (skipclose){
						dr.close();
						dr.switchTo().window(mainWindow);
					}
				}
				}
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	private boolean checkLiscence(){
		Date dt = new Date(2019,06,30);
		
		if (System.currentTimeMillis() < dt.getTime() ){
			System.out.println("valid Liscence");
			return true;
		}else{
			System.out.println("invalid Liscence");
			return false;
		}
		
	}
	
	@RequestMapping("/csvResultUpdate")
	@ResponseBody
	public String csvResultUpdate(@RequestParam(required=true) int year,@RequestParam(required=true) int col,@RequestParam(required=true) int count,@RequestParam(required=true) int state) {
		try{
			File f = new File("/home/vivek/Result_20_1_csv.csv");
			String cur_line = null;
			BufferedReader br = new BufferedReader(new FileReader(f));
			while ((cur_line = br.readLine()) != null) {
				String[] arr = cur_line.split(";");
				String lot_name = arr[1].replace("\"", "");
				String party_name = arr[0].replace("\"", "");
				lot_name = Integer.parseInt(lot_name)+ "";
				//String division = arr[2];
				//String lot_given_name = arr[3];
				//common.updateLotDetails(lot_name, lot_given_name.replace("\"", ""), division.replace("\"", ""), state);
				
				for (int itr =0;itr<count;itr++){
					int rate=-1;
					try{
						if (!"".equals(arr[col+itr])){
							rate = (int)Double.parseDouble( arr[col+itr]);
						}
					}catch(Exception Ex){
						
					}
					
					//System.out.println(lot_name + rate);
					if (rate!=-1){
						common.updateResultByYearRate(year+itr, lot_name, rate,state,party_name);
						
					}
					
				}
				
				
			}
			return "Completed..";
		}catch(Exception ex){
			ex.printStackTrace();
			
			return "Error.." + ex.getMessage();
		}
		
	}
	
	

	@RequestMapping("/manualRead")
	public ModelAndView manualRead(@RequestParam(required=true) String tenderName, @RequestParam(required=true) int state, @RequestParam(required=true) boolean save) {
		try {
			
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
			ChromeOptions chromeOptions = new ChromeOptions();
			// chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--no-sandbox");

			WebDriver webdriver = new ChromeDriver(chromeOptions);
			
			
			//WebDriver webdriver = new  InternetExplorerDriver(capabilities);;
			webdriver.get("file:///tmp/tender.html");
			
			String text = webdriver.findElement(By.tagName("body")).getText();
			
			//File f = new File("/tmp/cg_2021_r1");
			stateId=state;
			BufferedReader br = new BufferedReader(new StringReader(text));
			String cur_line = null;
			String companyName="";
			Double emd = 0.0, purCapacity = 0.0, bidCapacity = 0.0;
			List<Bid> list_bids = new ArrayList<Bid>();
			Financials financials = new Financials();
			int party_id = -1;
			//int tender_id = 22;
			boolean lotProcess=false;
			boolean restart=true;
			List<Integer> party_processed = new ArrayList<Integer>();
			Tender tender = new Tender();
			tender.setTender_name(tenderName);
			tender.setState(stateId);
			int tender_id = common.saveTender(tender);
			
			while ((cur_line = br.readLine()) != null) {

				if (cur_line.contains("Company Name : ") || cur_line.contains("Comapny Name : ")) {
					lotProcess=false;
					restart=true;
					companyName = cur_line.replace("Company Name : ", "").replace("Comapny Name : ", "");
					companyName = companyName.replace("EMD Amount : ", ",");
					companyName = companyName.replace("Purchase Capacity : ", ",");
					companyName = companyName.replace("Bidding Capacity : ", ",");
					String[] arr = companyName.split(",");
					
					Party party = new Party();
					party.setParty_name(arr[0].trim());
					party.setStatus(true);
					party_id = common.checkPartyExists_name(party);
					if (party_id==-1){
						System.out.println("party not FOUND#####" + companyName);
						party_id=common.saveParty(party);
					}
					if (arr.length>1){
						emd = Double.parseDouble(arr[1].trim());
						purCapacity = Double.parseDouble(arr[2].trim());
						bidCapacity = Double.parseDouble(arr[3].trim());
					}
					financials.setParty_id(party_id);
					financials.setTender_id(tender_id);
					financials.setEmd(emd);
					financials.setPurchase_capacity(purCapacity);
					financials.setBid_capacity(bidCapacity);
				} else if (cur_line.contains("EMD : ") || cur_line.contains("EMD Amount : ") ) {
					emd = Double.parseDouble(cur_line.replace("EMD : ", "").replace("EMD Amount : ",""));
				} else if (cur_line.contains("Purchase Capacity : ")) {
					purCapacity = Double.parseDouble(cur_line.replace("Purchase Capacity : ", ""));
				} else if (cur_line.contains("Bidding Capacity : ")) {
					bidCapacity = Double.parseDouble(cur_line.replace("Bidding Capacity : ", ""));
					
					// header=false;
				}
				if (cur_line.contains("Priority")){
					lotProcess=true;
					continue;
				}
				if (cur_line.toLowerCase().contains("total")){
					lotProcess=false;
				}
				if (cur_line.toLowerCase().contains("nil") || cur_line.contains("(In Standard Bags)") || cur_line.contains("(Quantity * Rate)") ) {
					continue;
				}
				if (lotProcess && restart) {
					cur_line = cur_line.replace("Lot. No.", " ");
					cur_line = cur_line.replace("Lot No.", " ");
					cur_line = cur_line.replace("S.B.", " ");
					cur_line = cur_line.replace("S.B", " ");
					cur_line = cur_line.replace(" B - ", "B - ");
					cur_line = cur_line.replace(" A - ", "A - ");
					cur_line = cur_line.replace(" C - ", "C - ");
					cur_line = cur_line.replace("Quantity", " ");
					cur_line = cur_line.replace("-", " ");
					cur_line = cur_line.replace("\t", " ");
					cur_line = cur_line.replace("    ", " ");
					cur_line = cur_line.replace("   ", " ");
					cur_line = cur_line.replace("  ", " ");
					
					String[] arr_col = cur_line.split(" ");
					if (arr_col[0].equals("")){
						System.out.println(123);
					}
					int priority = Integer.parseInt(arr_col[0]);
					String lotName = arr_col[1];
					int qty = (int) Double.parseDouble(arr_col[2]);
					double rate = Double.parseDouble(arr_col[3]);
					double amount = Double.parseDouble(arr_col[4]);

					int lot_id=-1;
					if (!map_lot.containsKey(lotName)){
						Lot lot = new Lot();
						lot.setLot_name(lotName);
						lot.setLot_given_name(lotName);
						lot.setQty(qty);
						lot.setSerial_number(0);
						lot.setState(stateId);
						lot_id=common.checkLotExists(lot);
						if (lot_id==-1){
							lot_id=common.saveLot(lot);	
						}
						map_lot.put(lotName, lot_id);
					}else{
						lot_id=map_lot.get(lotName);
					}
					if (lot_id==-1){
						System.out.println("LOT not FOUND#####" + lotName);
					}
					Bid bid = new Bid();
					bid.setTender_id(tender_id);
					bid.setLot_id(lot_id);
					bid.setParty_id(party_id);
					bid.setRound_number(1);
					bid.setPriority(priority);
					bid.setQty(qty);
					bid.setRate(rate);
					bid.setAmount(amount);
					list_bids.add(bid);
				}
				
				
				if (cur_line.toLowerCase().contains("total")) {
					if (party_processed.contains(party_id)){
						continue;
					}else{
						party_processed.add(party_id);
					}
					financials.setParty_id(party_id);
					financials.setTender_id(tender_id);
					financials.setEmd(emd);
					financials.setPurchase_capacity(purCapacity);
					financials.setBid_capacity(bidCapacity);
					if (save){
						common.saveFinancials(financials);
						common.saveMultipleBids(list_bids);
					}
					System.out.println("Party: "+ companyName +" : bid count:" + list_bids.size() +" : purchase capacity :" + financials.getPurchase_capacity());
					for (Bid b : list_bids){
						System.out.println("lotId:- "+b.getLot_id()+" rate:"+ b.getRate() + "amount: "+ b.getAmount() );
					}
					System.out.println("######################################################################");
					list_bids = new ArrayList<Bid>();
					financials = new Financials();
					party_id=-1;
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}
		return new ModelAndView("home");
	}
	
	
	public static void main(String sd[]){
		String a="a SB. b";
		System.out.println(a.replace("SB.", " "));
	}
	
	private boolean checkMac(){
		try{
			Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
			while (networks.hasMoreElements()){
				//NetworkInterface cur_network = 
				final NetworkInterface netInf = networks.nextElement();
				final byte[] mac = netInf.getHardwareAddress();
				if (mac!=null){
					final StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
					}
					System.out.println(requiredMac+"checking.... " + sb.toString());
					if (requiredMac.equals(sb.toString())){
						return true;
					}
				}
			}
		}catch(Exception ex){
			System.out.println("checkMac : "+ ex);
			return false;
		}
		System.out.println("mac doesnot match: " );
		return false ;
	}
}
