package mp.procurement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bid",schema="main")
public class Bid {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Integer tender_id;
	private Integer party_id;
	private Integer lot_id;
	private Integer round_number;
	private Integer priority;
	private Integer qty;
	private Double rate;
	private Double amount;
	private Boolean is_selected;
	private Boolean is_predicted;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getTender_id() {
		return tender_id;
	}
	public void setTender_id(Integer tender_id) {
		this.tender_id = tender_id;
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
	public Integer getRound_number() {
		return round_number;
	}
	public void setRound_number(Integer round_number) {
		this.round_number = round_number;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
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
	public Boolean getIs_selected() {
		return is_selected;
	}
	public void setIs_selected(Boolean is_selected) {
		this.is_selected = is_selected;
	}
	public Boolean getIs_predicted() {
		return is_predicted;
	}
	public void setIs_predicted(Boolean is_predicted) {
		this.is_predicted = is_predicted;
	}
	
}
