package com.espark.adarsh.web.security;


import com.espark.adarsh.persistence.entites.impl.User;
import com.espark.adarsh.persistence.entites.impl.UserRole;
import com.espark.adarsh.web.manager.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@Service(value = "userDetailsService")
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger("CustomUserDetailsService");

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        logger.debug("DbUserDetailsService loadUserByUsername() ");
        try {
            User bean = new User(username);
            bean = userManager.getUser(bean);
            userManager.updateUser(bean);
            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(bean.getUserRoles().size());
            for (UserRole role : bean.getUserRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            UserDetailsImpl details = new UserDetailsImpl(bean.getUserName(), bean.getUserPwd(), bean.getEnabled(),
                    true, true, true, authorities);
            details.setUser(bean);
            return details;
        } catch (Exception e) {
            logger.error("Unable to find record with username:=" + username, e);
        }
        return null;
    }

    @Qualifier("userManagerImpl")
    @Autowired
    private UserManager userManager;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
