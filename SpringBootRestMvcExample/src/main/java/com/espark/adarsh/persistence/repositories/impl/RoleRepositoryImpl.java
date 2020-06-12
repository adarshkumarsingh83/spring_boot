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
package com.espark.adarsh.persistence.repositories.impl;

import com.espark.adarsh.persistence.entites.impl.User;
import com.espark.adarsh.persistence.entites.impl.UserRole;
import com.espark.adarsh.persistence.repositories.RoleRepository;
import com.espark.adarsh.persistence.repositories.construct.AbstractRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@Repository
public class RoleRepositoryImpl extends AbstractRepository<UserRole>
        implements RoleRepository<UserRole> {

    public RoleRepositoryImpl() {
        super(UserRole.class);
    }

    @Override
    @Transactional
    public Long size() {
        return super.size();
    }

    @Override
    public void refreshUser(UserRole userRole) {
        super.refresh(userRole);
    }

    @Override
    @Transactional
    public Boolean saveUserRole(UserRole userRole) {
        super.put(userRole);
        return null;
    }

    @Override
    @Transactional
    public UserRole getRoleByName(String roleName) {
         UserRole userRole=new UserRole();
          userRole.setName(roleName);
        return (UserRole) super.getUniqueByExample(userRole);
    }

    @Override
    @Transactional
    public Boolean updateUserRole(UserRole userRole) {
        super.update(userRole);
        return null;
    }

    @Override
    @Transactional
    public Boolean dropUserRole(UserRole userRole) {
        super.remove(userRole);
        return null;
    }

    @Override
    @Transactional
    public UserRole getUserRole(User user) {
        UserRole UserUserRole = new UserRole();
        UserUserRole.setId(1L);
        UserUserRole.setName("User");
        return null;
    }

    @Override
    public List<UserRole> getAllUserRole(User user) {
        return super.getAll();
    }
}
