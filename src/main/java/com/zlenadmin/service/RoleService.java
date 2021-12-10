package com.zlenadmin.service;

import com.zlenadmin.dto.RoleDto;
import com.zlenadmin.model.Role;

public interface RoleService {

	Role save(RoleDto roleDto);
	
	Role update(RoleDto roleDto);
}
