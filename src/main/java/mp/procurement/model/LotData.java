package mp.procurement.model;

public class LotData {

	private String party_name;
	private String tender_name;
	private Double rate;
	private Integer bid_count;
	private Integer priority;
	private Integer rank;
	private Integer tender_id;
	
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
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getBid_count() {
		return bid_count;
	}
	public void setBid_count(Integer bid_count) {
		this.bid_count = bid_count;
	}
	public Integer getTender_id() {
		return tender_id;
	}
	public void setTender_id(Integer tender_id) {
		this.tender_id = tender_id;
	}
	
	
	
}
