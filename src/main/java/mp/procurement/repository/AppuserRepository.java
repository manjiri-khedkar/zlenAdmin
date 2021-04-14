package mp.procurement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mp.procurement.model.AppUser;
import mp.procurement.model.Login;

public interface AppuserRepository extends JpaRepository<AppUser, Long> {
	
	AppUser findByUserId(String userId);
	
	AppUser findByEmail(String emailId);
   	
	AppUser validateUser(Login login);
	
   	AppUser addActivity(Integer userId, String string, long currentTimeMillis);

}
