package mp.procurement.model;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Component("sessionUser")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionUser implements Serializable {
	
	public SessionUser(){
		System.out.println("Session Object Created:");
	}

    private static final Logger LOG = LoggerFactory.getLogger(SessionUser.class);

    private static final long serialVersionUID = 1L;
    
    private Integer userId;
    private String user_name;
    private Integer lot_allowed;
    private Integer lot_consumed;
    private String role;
    private List<PartyLots> party_lots;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getLot_allowed() {
		return lot_allowed;
	}
	public void setLot_allowed(Integer lot_allowed) {
		this.lot_allowed = lot_allowed;
	}
	public Integer getLot_consumed() {
		return lot_consumed;
	}
	public void setLot_consumed(Integer lot_consumed) {
		this.lot_consumed = lot_consumed;
	}
	public List<PartyLots> getParty_lots() {
		return party_lots;
	}
	public void setParty_lots(List<PartyLots> party_lots) {
		this.party_lots = party_lots;
	}

}
