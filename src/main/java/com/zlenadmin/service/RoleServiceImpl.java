package com.zlenadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlenadmin.dto.RoleDto;
import com.zlenadmin.model.Role;
import com.zlenadmin.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role save(RoleDto roleDto) {
		
		Role role = new Role();
		role.setRoleName(roleDto.getRoleName());
        role.setRoleDescription(roleDto.getRoleDescription());
        role.setRoleStatus(roleDto.getRoleStatus());
		
		return roleRepository.save(role);
	}

	@Override
	public Role update(RoleDto roleDto) {
	
		Role role = roleRepository.findById(roleDto.getId());
        role.setId(roleDto.getId());
        role.setRoleName(roleDto.getRoleName());
        role.setRoleDescription(roleDto.getRoleDescription());
        role.setRoleStatus(roleDto.getRoleStatus());
        
		return roleRepository.save(role);
	}

}
