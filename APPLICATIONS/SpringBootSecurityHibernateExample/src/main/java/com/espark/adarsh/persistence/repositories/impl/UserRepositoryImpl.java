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
import com.espark.adarsh.persistence.repositories.UserRepository;
import com.espark.adarsh.persistence.repositories.construct.AbstractRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@Repository
public class UserRepositoryImpl extends AbstractRepository<User>
        implements UserRepository<User> {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    @Transactional
    public Boolean saveUser(User user) {
        super.put(user);
        return null;
    }

    @Override
    @Transactional
    public User getUser(User user) {
        return getUniqueByExample(user);
    }

    @Override
    @Transactional
    public User getUserById(User user) {
        return (User) super.getEntityById(user);
    }

    @Override
    @Transactional
    public User getUserByName(User user) {
        return (User) super.getByColumnName("userName",user.getUserName());
    }

    @Override
    @Transactional
    public Boolean deleteUser(User user) {
        super.remove(user);
        return null;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        super.update(user);
        return null;
    }


    @Override
    @Transactional
    public Collection<User> getAllUser() {
        return getAll();
    }


    @Transactional
    public User getFacebookUser(String facebookUserEmailId){
        Criteria criteria=getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("userEmail",facebookUserEmailId)) ;
        Object object=criteria.uniqueResult();
        if(object!=null){
            return (User)object;
        }
        return (User)object;
    }

    @Override
    @Transactional
    public Long size() {
        return super.size();
    }

    @Override
    public void refreshUser(User user) {
        super.refresh(user);
    }
}