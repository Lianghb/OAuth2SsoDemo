package com.boxfish.demo.entity;

import com.boxfish.demo.entity.Authority;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by boxfish on 15/12/19.
 */
public interface AuthorityRepository extends CrudRepository<Authority, String> {
    public Authority findByUsername(String username);
}
