package mp.procurement.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tender",schema="main")
public class Tender {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tender_name;
	private boolean status;
	private Integer state;
	private Integer year;
	private Integer round;
	private Date last_result_process;
	private Double min_rate;
	private Date tender_date;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTender_name() {
		return tender_name;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getRound() {
		return round;
	}
	public void setRound(Integer round) {
		this.round = round;
	}
	public void setTender_name(String tender_name) {
		this.tender_name = tender_name;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getLast_result_process() {
		return last_result_process;
	}
	public void setLast_result_process(Date last_result_process) {
		this.last_result_process = last_result_process;
	}
	public Double getMin_rate() {
		return min_rate;
	}
	public void setMin_rate(Double min_rate) {
		this.min_rate = min_rate;
	}
	public Date getTender_date() {
		return tender_date;
	}
	public void setTender_date(Date tender_date) {
		this.tender_date = tender_date;
	}



}
