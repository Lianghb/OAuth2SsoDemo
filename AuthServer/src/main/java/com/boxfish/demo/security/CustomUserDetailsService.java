
package com.boxfish.demo.security;

import com.boxfish.demo.entity.User;
import com.boxfish.demo.entity.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by boxfish on 15/12/19.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.debug("loadUserByUserName(String username)" + " username="+username);

        User entity = userRepository.findByUsername(username);

        if (entity == null) {
            throw new UsernameNotFoundException("用户名：" + username + " 不存在！");
        }

        return new CustomUserDetails(entity);
    }
}

