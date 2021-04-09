package mp.procurement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trade",schema="main")
public class TradeRecord {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String state;
	private String appro_office;
	private String class1;
	private String as_on_date;
	private String certi_no;
	private String date_appli;
	private String tm_appli_no;
	private String status;
	private String warning_or_disclamer;
	private String country;
	private String fil_mode;
	private String tm_applied;
	private String tm_category;
	private String trade_mark;
	private String user_detail;
	private String dated;
	private String note_janrnal_no;
	private String valid_or_renewed;
	private String prop_name;
	private String trad_as;
	private String body;
	private String prop_add;
	private String email_id;
	private String agent_name;
	private String agent_add;
	private String good_service;
	private String bidies;
	private String asso_trademark;
	private String publish_janrnal_no;
	private String dated1;
	private String his_pr_detail;
	private String trade_mrk_img;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAppro_office() {
		return appro_office;
	}

	public void setAppro_office(String appro_office) {
		this.appro_office = appro_office;
	}

	public String getClass1() {
		return class1;
	}

	public void setClass1(String class1) {
		this.class1 = class1;
	}

	public String getAs_on_date() {
		return as_on_date;
	}

	public void setAs_on_date(String as_on_Date) {
		this.as_on_date = as_on_Date;
	}

	public String getCerti_no() {
		return certi_no;
	}

	public void setCerti_no(String certi_no) {
		this.certi_no = certi_no;
	}

	public String getDate_appli() {
		return date_appli;
	}

	public void setDate_appli(String date_appli) {
		this.date_appli = date_appli;
	}

	public String getTm_appli_no() {
		return tm_appli_no;
	}

	public void setTm_appli_no(String tm_appli_no) {
		this.tm_appli_no = tm_appli_no;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWarning_or_disclamer() {
		return warning_or_disclamer;
	}

	public void setWarning_or_disclamer(String warning_or_disclamer) {
		this.warning_or_disclamer = warning_or_disclamer;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFil_mode() {
		return fil_mode;
	}

	public void setFil_mode(String fil_mode) {
		this.fil_mode = fil_mode;
	}

	public String getTm_applied() {
		return tm_applied;
	}

	public void setTm_applied(String tm_applied) {
		this.tm_applied = tm_applied;
	}

	public String getTm_category() {
		return tm_category;
	}

	public void setTm_category(String tm_category) {
		this.tm_category = tm_category;
	}

	public String getTrade_mark() {
		return trade_mark;
	}

	public void setTrade_mark(String trade_mark) {
		this.trade_mark = trade_mark;
	}

	public String getUser_detail() {
		return user_detail;
	}

	public void setUser_detail(String user_detail) {
		this.user_detail = user_detail;
	}

	public String getDated() {
		return dated;
	}

	public void setDated(String dated) {
		this.dated = dated;
	}

	public String getNote_janrnal_no() {
		return note_janrnal_no;
	}

	public void setNote_janrnal_no(String note_janrnal_no) {
		this.note_janrnal_no = note_janrnal_no;
	}

	public String getValid_or_renewed() {
		return valid_or_renewed;
	}

	public void setValid_or_renewed(String valid_or_renewed) {
		this.valid_or_renewed = valid_or_renewed;
	}

	public String getProp_name() {
		return prop_name;
	}

	public void setProp_name(String prop_name) {
		this.prop_name = prop_name;
	}

	public String getTrad_as() {
		return trad_as;
	}

	public void setTrad_as(String trad_as) {
		this.trad_as = trad_as;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getProp_add() {
		return prop_add;
	}

	public void setProp_add(String prop_add) {
		this.prop_add = prop_add;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getAgent_name() {
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public String getAgent_add() {
		return agent_add;
	}

	public void setAgent_add(String agent_add) {
		this.agent_add = agent_add;
	}

	public String getGood_service() {
		return good_service;
	}

	public void setGood_service(String good_service) {
		this.good_service = good_service;
	}

	public String getBidies() {
		return bidies;
	}

	public void setBidies(String bidies) {
		this.bidies = bidies;
	}

	public String getAsso_trademark() {
		return asso_trademark;
	}

	public void setAsso_trademark(String asso_trademark) {
		this.asso_trademark = asso_trademark;
	}

	public String getPublish_janrnal_no() {
		return publish_janrnal_no;
	}

	public void setPublish_janrnal_no(String publish_janrnal_no) {
		this.publish_janrnal_no = publish_janrnal_no;
	}

	public String getDated1() {
		return dated1;
	}

	public void setDated1(String dated1) {
		this.dated1 = dated1;
	}

	public String getHis_pr_detail() {
		return his_pr_detail;
	}

	public void setHis_pr_detail(String his_pr_detail) {
		this.his_pr_detail = his_pr_detail;
	}

	public String getTrade_mrk_img() {
		return trade_mrk_img;
	}

	public void setTrade_mrk_img(String trade_mrk_img) {
		this.trade_mrk_img = trade_mrk_img;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}