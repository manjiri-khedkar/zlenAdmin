package com.zlenadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zlenadmin.model.AppUser;

@Repository
public interface AppuserRepository extends JpaRepository<AppUser, Long> {
	
	public List<AppUser> findAll();
	
	AppUser findById(Integer id);
	
     
	@Query("select a from AppUser a where  email = :email")
	AppUser findByEmail(@Param("email") String email);

	Long countById(long id);
	
	AppUser deleteById(long id);
	
//	AppUser findByEmailIdIgnoreCase(String emailId);
	
	//AppUser validateUser(Login login);
	
   	//AppUser addActivity(Integer userId, String string, long currentTimeMillis);

}
