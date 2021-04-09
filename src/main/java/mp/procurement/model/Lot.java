package mp.procurement.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lot",schema="main")
public class Lot  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String lot_name;
	private String lot_given_name;
	private Integer qty;
	private Integer serial_number;
	private String division;
	private Integer state;
	
	public String getDivision() {
		return division;
	}
	
	public void setDivision(String division) {
		this.division = division;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLot_name() {
		return lot_name;
	}
	public void setLot_name(String lot_name) {
		this.lot_name = lot_name;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(Integer serial_number) {
		this.serial_number = serial_number;
	}
	public String getLot_given_name() {
		return lot_given_name;
	}
	public void setLot_given_name(String lot_given_name) {
		this.lot_given_name = lot_given_name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
