
package com.boxfish.demo.security;

import com.boxfish.demo.entity.User;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * Created by boxfish on 15/12/19.
 */

public class CustomUserDetails extends User implements UserDetails {

    Logger logger = LoggerFactory.getLogger(getClass());

    public CustomUserDetails(User user) {
        try {
            BeanUtils.copyProperties(this, user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        logger.debug("getAuthorities()");

        StringBuilder builder = new StringBuilder();
        super.getAuthoritiesByUsername().forEach(
                authorities -> builder.append(authorities.getRole()).append(","));

        logger.debug("获取到：" + builder.substring(0,builder.length()-1));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(
                builder.substring(0, builder.length() - 1));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

