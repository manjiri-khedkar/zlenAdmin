package mp.procurement;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;
import mp.procurement.email.ApplicationMailer;
import mp.procurement.model.AppUser;
import mp.procurement.model.SessionUser;
import mp.procurement.repository.AppuserRepository;

@Controller
@Component
@SessionScope
public class MainPage {

	@Autowired
	ApplicationMailer mailer;
	WebDriver dr = null;

	@Autowired
	private SessionUser sessionUser;

	@Autowired
	private AppuserRepository appuserRepository;
	
	@GetMapping(value = "/")
	public String indexView(@ModelAttribute AppUser users) 
	{
		return "redirect:/login";
	} 
	
	@RequestMapping("/adminHomeDashboard")
	public ModelAndView showMessage() {

		// appuserRepository.addActivity(sessionUser.getUserId(), "adminHomeDashboard",
		// System.currentTimeMillis());
		if (true) {
			ModelAndView model = new ModelAndView("home");
			// model.addObject("tenderList", common.getAllTender());
			//// model.addObject("divisionList", common.getAllDivision());
			// .addObject("partyList", common.getAllParty());
			// model.addObject("yearList", common.getYearList());

			// common.getAllTender()
			return model;
		} else {
			ModelAndView model = new ModelAndView("licenseError");
			return model;
		}
	}

	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		//appuserRepository.addActivity(sessionUser.getUserId(), "dashboard", System.currentTimeMillis());
		ModelAndView model = new ModelAndView("dashboard");
		return model;
	}

	/*
	 * @RequestMapping("/tenderMpDashboard") public ModelAndView tenderMpDashboard()
	 * { appuserRepository.addActivity(sessionUser.getUserId(), "tenderMpDashboard",
	 * System.currentTimeMillis());
	 * 
	 * List<Map<String, String>> tenderList = (List<Map<String, String>>)
	 * appuserRepository.getTenderList(1);
	 * 
	 * ModelAndView model = new ModelAndView("tenderDashboard");
	 * 
	 * model.addObject("tenderList", tenderList); model.addObject("state", 1);
	 * return model; }
	 */

	/*
	 * @RequestMapping("/tenderCgDashboard") public ModelAndView tenderCgDashboard()
	 * { appuserRepository.addActivity(sessionUser.getUserId(), "tenderCgDashboard",
	 * System.currentTimeMillis());
	 * 
	 * List<Map<String, String>> tenderList = (List<Map<String, String>>)
	 * appuserRepository.getTenderList(2);
	 * 
	 * ModelAndView model = new ModelAndView("tenderDashboard");
	 * 
	 * model.addObject("tenderList", tenderList); model.addObject("state", 2);
	 * return model; }
	 */

	/*
	 * @RequestMapping("/lotMpDashboard") public ModelAndView lotMpDashboard() {
	 * appuserRepository.addActivity(sessionUser.getUserId(), "lotMpDashboard",
	 * System.currentTimeMillis());
	 * 
	 * List<Lot> lotList = (List<Lot>) appuserRepository.getAllLot(1);
	 * 
	 * ModelAndView model = new ModelAndView("lotDashboard");
	 * 
	 * model.addObject("lotList", lotList); model.addObject("state", 1); return
	 * model; }
	 */

	/*
	 * @RequestMapping("/changePassword")
	 * 
	 * @ResponseBody public String changePassword(String pwd, String newPwd) {
	 * appuserRepository.addActivity(sessionUser.getUserId(), "changePassword",
	 * System.currentTimeMillis());
	 * 
	 * try { // Appuser user =appuserRepository.getAppuser(sessionUser.getUserId());
	 * if (newPwd == null || "".equals(newPwd)) { return
	 * "New password can not be blank"; } else if (user.getPassword().equals(pwd)) {
	 * user.setPassword(newPwd); appuserRepository.updateParty(user); } else {
	 * return "Old password is wrong!"; }
	 * 
	 * return "Pass changes successfully."; } catch (Exception ex) { return
	 * "There is some error while updating password."; } }
	 */

	/*
	 * @RequestMapping("/lotCgDashboard") public ModelAndView lotCgDashboard() {
	 * appuserRepository.addActivity(sessionUser.getUserId(), "lotCgDashboard",
	 * System.currentTimeMillis());
	 * 
	 * List<Lot> lotList = (List<Lot>) appuserRepository.getAllLot(2);
	 * 
	 * ModelAndView model = new ModelAndView("lotDashboard");
	 * 
	 * model.addObject("lotList", lotList); model.addObject("state", 2); return
	 * model; }
	 */

	/*
	 * @RequestMapping("/chartData") public @ResponseBody Object chartData(int
	 * state_id) {
	 * 
	 * //List<Map<String, Integer>> yearWiseRates = (List<Map<String, Integer>>)
	 * appuserRepository.getYearWiseMinMaxAvgRate(state_id); //List<Map<String,
	 * Double>> yearWiseAmount = (List<Map<String, Double>>)
	 * appuserRepository.getYearWiseAmount(state_id); HashMap<String, Object> resp =
	 * new HashMap<String, Object>();
	 * 
	 * //resp.put("yearWiseRates", yearWiseRates); //resp.put("yearWiseAmount",
	 * yearWiseAmount); return resp; }
	 */
	/*
	 * @RequestMapping(value = "/lotReport", method = RequestMethod.GET) public
	 * ModelAndView getLotReport(HttpServletRequest request) { String chk =
	 * request.getParameter("chk"); String to_email =
	 * request.getParameter("emailId"); String[] arr_ids = chk.split(":");
	 * List<ReportBean> list_report = appuserRepository.getReport("LOT", arr_ids,
	 * -1);
	 * 
	 * HSSFWorkbook workbook = new HSSFWorkbook(); new
	 * ExcelHelper().getLotReport(workbook, list_report); try { // Creating the
	 * directory to store file String rootPath =
	 * System.getProperty("catalina.home"); File dir = new File(rootPath +
	 * File.separator + "tmpFiles"); if (!dir.exists()) dir.mkdirs();
	 * 
	 * // Create the file on server File serverFile = new File(dir.getAbsolutePath()
	 * + File.separator + System.nanoTime() + ".xls"); BufferedOutputStream stream =
	 * new BufferedOutputStream(new FileOutputStream(serverFile));
	 * workbook.write(stream); stream.close(); if (to_email != null &&
	 * !"".equals(to_email)) { mailer.sendMail(to_email, "Lot Wise Report",
	 * "Please find the attachment for Lot wise data. ", serverFile);
	 * 
	 * }
	 * 
	 * } catch (Exception ex) { System.out.println(ex); } return new
	 * ModelAndView("lotReportExcel", "reportBean", list_report); }
	 */

	/*
	 * @RequestMapping(value="/emailSend",method= RequestMethod.GET ) public String
	 * emailSend() {
	 * 
	 * List<Party> list_party = (List<Party>) appuserRepository.getAllParty();
	 * String body =
	 * "<html> <head></head> <body> Hello<br><br> Thank you for your feedback and support, we have designed a dedicated web portal for Tendu Patta Tenders.   <br><br> To login on portal please use the following url and user id and password. <BR> URL: <a href=\"http://infosane.co.in:8080/tender/login\"> http://infosane.co.in:8080/tender/login </a>"
	 * +
	 * "<br> login Id: ##login_id## <br> password: ##password##  <br><br> We request you to please change the password once you login with given login id and password.  <br><br> Thanks & Regards <br> Infosane,Nagpur<br>Vivek Agrawal<br>cell: 8989000400,7000384248<br>Email:vivek.agrawal@infosane.co.in </body></html> "
	 * ; boolean start=false; List<String> list_to = new ArrayList<String>();
	 * 
	 * int count=0; for (Party p : list_party){ try{ list_to= new
	 * ArrayList<String>(); if (p.getEmail()!=null && !"".equals(p.getEmail())){
	 * list_to.add(p.getEmail()); count++;
	 * 
	 * 
	 * //if (count==50){ HashMap<String,String> map = new HashMap<String, String>();
	 * map.put("login_id", p.getEmail()); map.put("password", p.getPassword());
	 * mailer.sendHTMLMail(list_to, "Tendu Patta: Dedicated Web Portal ", body,null,
	 * map); count=0; }
	 * 
	 * }catch(Exception ex){
	 * 
	 * } } list_to.add("vivekraj.agrawal@gmail.com"); mailer.sendHTMLMail(list_to,
	 * "Tendu Patta: Dedicated Web Portal ", body,null);
	 * 
	 * return "0"; }
	 */

}
