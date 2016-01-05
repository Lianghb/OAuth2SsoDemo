package com.boxfish.lhb.security.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by boxfish on 15/12/19.
 */
public interface RolesRepository extends CrudRepository<RolesEntity,String> {

    RolesEntity findByRole(String role);

}
