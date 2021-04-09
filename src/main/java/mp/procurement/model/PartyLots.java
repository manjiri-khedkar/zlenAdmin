package mp.procurement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "party_lots",schema="main")
public class PartyLots {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParty_id() {
		return party_id;
	}
	public void setParty_id(int party_id) {
		this.party_id = party_id;
	}
	public int getLot_id() {
		return lot_id;
	}
	public void setLot_id(int lot_id) {
		this.lot_id = lot_id;
	}
	public String getParty_name() {
		return party_name;
	}
	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}
	public String getLot_name() {
		return lot_name;
	}
	public void setLot_name(String lot_name) {
		this.lot_name = lot_name;
	}
	public String getLot_given_name() {
		return lot_given_name;
	}
	public void setLot_given_name(String lot_given_name) {
		this.lot_given_name = lot_given_name;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	private int party_id;
	private int lot_id;
	@Transient
	private String party_name;
	@Transient
	private String lot_name;
	@Transient
	private String lot_given_name;
	@Transient
	private String division;
}
