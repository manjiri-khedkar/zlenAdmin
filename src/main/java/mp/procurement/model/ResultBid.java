package mp.procurement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class ResultBid {

	private int id;
	private Integer party_id;
	private Integer lot_id;
	private Integer rank;
	private String lot_name;
	private String party_name;
	private Integer priority;
	private Double rate;
	private Double amount;
	private Boolean is_selected;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getParty_id() {
		return party_id;
	}
	public void setParty_id(Integer party_id) {
		this.party_id = party_id;
	}
	public Integer getLot_id() {
		return lot_id;
	}
	public void setLot_id(Integer lot_id) {
		this.lot_id = lot_id;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getLot_name() {
		return lot_name;
	}
	public void setLot_name(String lot_name) {
		this.lot_name = lot_name;
	}
	public String getParty_name() {
		return party_name;
	}
	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Boolean isIs_selected() {
		return is_selected;
	}
	public void setIs_selected(Boolean is_selected) {
		if (is_selected==null){
			is_selected=false;
		}else{
			this.is_selected = is_selected;
		}
	}
	
}
