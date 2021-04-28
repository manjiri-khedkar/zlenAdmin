package mp.procurement.service;


import mp.procurement.dto.UserRegistrationDto;
import mp.procurement.model.AppUser;

public interface RegistrationService {
	
	   AppUser save(UserRegistrationDto registration);

		AppUser findByEmail(String email);
	    
	}
