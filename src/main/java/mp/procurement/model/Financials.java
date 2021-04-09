package mp.procurement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="financial",schema="main")
public class Financials {
	
	private static final long serialVersionUID = 1L; 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Integer party_id;
	private Integer tender_id;
	private Double emd;
	private Double purchase_capacity;
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
	public Integer getTender_id() {
		return tender_id;
	}
	public void setTender_id(Integer tender_id) {
		this.tender_id = tender_id;
	}
	public Double getEmd() {
		return emd;
	}
	public void setEmd(Double emd) {
		this.emd = emd;
	}
	public Double getPurchase_capacity() {
		return purchase_capacity;
	}
	public void setPurchase_capacity(Double purchase_capacity) {
		this.purchase_capacity = purchase_capacity;
	}
	public Double getBid_capacity() {
		return bid_capacity;
	}
	public void setBid_capacity(Double bid_capacity) {
		this.bid_capacity = bid_capacity;
	}
	private Double bid_capacity;
	

}
