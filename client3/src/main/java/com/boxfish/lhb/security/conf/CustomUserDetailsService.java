
package com.boxfish.lhb.security.conf;

import com.boxfish.lhb.security.domain.UsersEntity;
import com.boxfish.lhb.security.domain.UsersRepository;
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
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.debug("loadUserByUserName(String username)" + " username="+username);

        UsersEntity entity = usersRepository.findByUsername(username);

        if (entity == null) {
            throw new UsernameNotFoundException("用户名：" + username + " 不存在！");
        }

        return new CustomUserDetails(entity);
    }
}

