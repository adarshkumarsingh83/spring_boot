/*
 * Copyright (c) 2015 Espark And ©Adarsh Development Services @copyright All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Espark nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.espark.adarsh.web.manager;


import com.espark.adarsh.persistence.entities.impl.User;
import com.espark.adarsh.persistence.entities.impl.UserRole;
import com.espark.adarsh.persistence.repositories.RoleRepository;
import com.espark.adarsh.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@Service("userManager")
final public class UserManagerImpl
        implements UserManager {

    private final static Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

    public UserManagerImpl() {
    }

    @Override
    public void refreshUser(User user) {
        userRepository.refreshUser(user);
    }

    @Transactional
    public Boolean saveUser(User user) {
        userRepository.saveUser(user);
        return null;
    }

    @Transactional
    public Boolean updateUser(User user) {
        userRepository.updateUser(user);
        return null;
    }

    @Transactional
    public Boolean deleteUser(User user) {
        userRepository.deleteUser(user);
        return null;
    }

    public User getUser(final User user) {
        return  userRepository.getUser(user);
    }

    @Override
    public Collection<User> getAllUser() {
        return userRepository.getAllUser();
    }

    @Override
    public User getUserById(User user) {
        return userRepository.getUserById(user);
    }

    @Override
    public User getUserByName(User user) {
        return userRepository.getUserByName(user);
    }

    @Override
    public UserRole getUserRole(String roleName) {
        return roleRepository.getRoleByName(roleName);
    }

    @Qualifier("userRepositoryImpl")
    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Qualifier("roleRepositoryImpl")
    @Autowired
    private RoleRepository roleRepository;

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
