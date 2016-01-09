package com.boxfish.demo.entity;

import com.boxfish.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by boxfish on 15/12/19.
 */
public interface UserRepository extends CrudRepository<User,String > {
    User findByUsername(String username);
}
