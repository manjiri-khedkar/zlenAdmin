package com.zlenadmin.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zlenadmin.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	public List<Role> findAll();
	
	Role findById(long id);
	
	Long countById(long id);
	
	Role deleteById(long id);
}
