package com.boxfish.demo.entity;

import com.boxfish.demo.entity.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by boxfish on 15/12/19.
 */
public interface RoleRepository extends CrudRepository<Role,String> {

    Role findByRole(String role);

}
