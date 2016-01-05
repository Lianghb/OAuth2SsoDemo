
package com.boxfish.lhb.security.conf;

import com.boxfish.lhb.security.domain.UsersEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * Created by boxfish on 15/12/19.
 */

public class CustomUserDetails extends UsersEntity implements UserDetails {

    Logger logger = LoggerFactory.getLogger(getClass());

    public CustomUserDetails(UsersEntity usersEntity) {

        try {
            BeanUtils.copyProperties(this, usersEntity);
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
                authoritiesEntity -> builder.append(authoritiesEntity.getRole()).append(","));

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

