package mp.procurement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import mp.procurement.dto.UserRegistrationDto;
import mp.procurement.model.AppUser;
import mp.procurement.repository.AppuserRepository;



@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	AppuserRepository appuserRepository;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public AppUser save(UserRegistrationDto registration) {
		
		  AppUser user = new AppUser();
	        
	        user.setEmail(registration.getEmail());
	        user.setUserId(registration.getUserId());
	        user.setAddress(registration.getAddress());
	        user.setPassword(registration.getPassword());
	        user.setAge(registration.getAge());
	        user.setGender(registration.getGender());
	        user.setFirstName(registration.getFirstName());
	        user.setLastName(registration.getLastName());
	        user.setMobileNo(registration.getMobileNo());
	        user.setAadhaar(registration.getAadhaar());
	        user.setDate(registration.getDate());
	      
		
		  return appuserRepository.save(user);
	}

	@Override
	public AppUser findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AppUser> findAll() {
		// TODO Auto-generated method stub
		
		return appuserRepository.findAll();
	}

	

	

}
