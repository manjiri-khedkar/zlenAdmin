package com.zlenadmin.model;

public class ReportBean {
	
	public String getParty_name() {
		return party_name;
	}
	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}
	public String getTender_name() {
		return tender_name;
	}
	public void setTender_name(String tender_name) {
		this.tender_name = tender_name;
	}
	public String getLot_name() {
		return lot_name;
	}
	public void setLot_name(String lot_name) {
		this.lot_name = lot_name;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Boolean isIs_predicted() {
		return is_predicted;
	}
	public void setIs_predicted(Boolean is_predicted) {
		this.is_predicted = is_predicted;
	}
	public Boolean isIs_canceled() {
		return is_canceled;
	}
	public void setIs_canceled(Boolean is_canceled) {
		this.is_canceled = is_canceled;
	}
	private String party_name;
	private String tender_name;
	private String lot_name;
	private String lot_given_name;
	private Double rate;
	private Integer qty;
	private Double amount; 
	private Boolean is_predicted;
	private Boolean is_canceled;
	private Integer tender_id;
	private Integer lot_id;
	private Integer rnk;
	private Integer priority;
	private Integer rank;
	private String division;
	private Double capacity;
	private Double purchase_capacity;
	private Double emd;
	private Double consumed_capacity;
	private Double bid_capacity;
	
	public Double getPurchase_capacity() {
		return purchase_capacity;
	}
	public void setPurchase_capacity(Double purchase_capacity) {
		this.purchase_capacity = purchase_capacity;
	}
	public Double getEmd() {
		return emd;
	}
	public void setEmd(Double emd) {
		this.emd = emd;
	}
	public Double getConsumed_capacity() {
		return consumed_capacity;
	}
	public void setConsumed_capacity(Double consumed_capacity) {
		this.consumed_capacity = consumed_capacity;
	}
	public Double getBid_capacity() {
		return bid_capacity;
	}
	public void setBid_capacity(Double bid_capacity) {
		this.bid_capacity = bid_capacity;
	}
	public Integer getTender_id() {
		return tender_id;
	}
	public void setTender_id(Integer tender_id) {
		this.tender_id = tender_id;
	}
	public Integer getLot_id() {
		return lot_id;
	}
	public void setLot_id(Integer lot_id) {
		this.lot_id = lot_id;
	}
	public Integer getParty_id() {
		return party_id;
	}
	public void setParty_id(Integer party_id) {
		this.party_id = party_id;
	}
	public Integer getRnk() {
		return rnk;
	}
	public void setRnk(Integer rnk) {
		this.rnk = rnk;
	}
	public String getLot_given_name() {
		return lot_given_name;
	}
	public void setLot_given_name(String lot_given_name) {
		this.lot_given_name = lot_given_name;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	private Integer party_id;

	public Double getCapacity() {
		return capacity;
	}
	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	
}
