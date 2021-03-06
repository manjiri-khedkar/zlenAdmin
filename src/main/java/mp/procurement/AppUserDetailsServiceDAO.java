package mp.procurement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mp.procurement.model.Party;

@Component(value = "userDetailsService")
@Transactional
@Service
public class AppUserDetailsServiceDAO implements UserDetailsService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private CommonDao dao ;
	
	private User buildUserForAuthentication(Party user, List<GrantedAuthority> authorities) {
        // Following code is changed once tables and spring security will be completed this need to be handle again.
		System.out.println(user.getEmail() + " " + user.getPassword());
        return new User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
    }
	
	
	private List<GrantedAuthority> buildUserAuthority() {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        setAuths.add(new SimpleGrantedAuthority("Admin"));
        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }
	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		
		Party party=null;
		try {
			party=dao.loadUserByName(username);
			
			if (party!=null){
				System.out.println("party...." + party.getId() + party.getParty_name());
				List<GrantedAuthority> authorities = buildUserAuthority();
				User user = buildUserForAuthentication(party,authorities);
				System.out.println("party....2" + user.getUsername()+"  " + user.getPassword());
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
