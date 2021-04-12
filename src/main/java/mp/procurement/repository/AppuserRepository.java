package mp.procurement.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import mp.procurement.model.Appuser;
import mp.procurement.model.Login;
import mp.procurement.model.Party;

public interface AppuserRepository extends JpaRepository<Appuser, Long> {
	
	Appuser findByUserId(String userId);
	
	Appuser findByEmail(String emailId);



	Appuser validateUser(Login login);

	Appuser addActivity(Integer userId, String string, long currentTimeMillis);

	List<Map<String, String>> getTenderList(int i);

	



}
