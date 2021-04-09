package mp.procurement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lot_data",schema="main")
public class LotRate {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private Integer lot_id;
	private Integer weight;
	private Integer labour;
	private Integer gst_rate;
	private Double kg_rate;
	private Double sb_rate;
	private Double kg_tax;
	private Double sb_tax;
	private Double kg_expenses;
	private Double sb_expenses;
	private Double kg_gst;
	private Double sb_gst;
	private Double kg_total;
	private Double sb_total;
	private Integer qty;
	private Double preference;
	private Integer list_id;
	
	public Double getTotal_value() {
		return total_value;
	}
	public void setTotal_value(Double total_value) {
		this.total_value = total_value;
	}
	public Double getKg_cost() {
		return kg_cost;
	}
	public void setKg_cost(Double kg_cost) {
		this.kg_cost = kg_cost;
	}
	private Double total_value;
	private Double kg_cost;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLot_id() {
		return lot_id;
	}
	public void setLot_id(Integer lot_id) {
		this.lot_id = lot_id;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getLabour() {
		return labour;
	}
	public void setLabour(Integer labour) {
		this.labour = labour;
	}
	public Integer getGst_rate() {
		return gst_rate;
	}
	public void setGst_rate(Integer gst_rate) {
		this.gst_rate = gst_rate;
	}
	public Double getKg_rate() {
		return kg_rate;
	}
	public void setKg_rate(Double kg_rate) {
		this.kg_rate = kg_rate;
	}
	public Double getSb_rate() {
		return sb_rate;
	}
	public void setSb_rate(Double sb_rate) {
		this.sb_rate = sb_rate;
	}
	public Double getKg_tax() {
		return kg_tax;
	}
	public void setKg_tax(Double kg_tax) {
		this.kg_tax = kg_tax;
	}
	public Double getSb_tax() {
		return sb_tax;
	}
	public void setSb_tax(Double sb_tax) {
		this.sb_tax = sb_tax;
	}
	public Double getKg_expenses() {
		return kg_expenses;
	}
	public void setKg_expenses(Double kg_expenses) {
		this.kg_expenses = kg_expenses;
	}
	public Double getSb_expenses() {
		return sb_expenses;
	}
	public void setSb_expenses(Double sb_expenses) {
		this.sb_expenses = sb_expenses;
	}
	public Double getKg_gst() {
		return kg_gst;
	}
	public void setKg_gst(Double kg_gst) {
		this.kg_gst = kg_gst;
	}
	public Double getSb_gst() {
		return sb_gst;
	}
	public void setSb_gst(Double sb_gst) {
		this.sb_gst = sb_gst;
	}
	public Double getKg_total() {
		return kg_total;
	}
	public void setKg_total(Double kg_total) {
		this.kg_total = kg_total;
	}
	public Double getSb_total() {
		return sb_total;
	}
	public void setSb_total(Double sb_total) {
		this.sb_total = sb_total;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Double getPreference() {
		return preference;
	}
	public void setPreference(Double preference) {
		this.preference = preference;
	}
	public Integer getList_id() {
		return list_id;
	}
	public void setList_id(Integer list_id) {
		this.list_id = list_id;
	}	
	
}
