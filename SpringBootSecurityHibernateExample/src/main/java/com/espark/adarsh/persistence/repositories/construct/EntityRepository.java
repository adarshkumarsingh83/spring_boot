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
package com.espark.adarsh.persistence.repositories.construct;


import com.espark.adarsh.persistence.entites.construct.Entity;
import org.springframework.dao.DuplicateKeyException;

import java.util.Collection;
import java.util.List;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@org.springframework.stereotype.Repository
public interface EntityRepository<R> {

    /* public R get(R entity);*/

    public List<R> getAll();

    public List<R> getAll(int limit);

    public List<R> getAll(Integer firstResult, Integer maxResults);

    public List<R> getByExample(final R example);

    public R getUniqueByExample(final R example);

    public void put(final R record) throws DuplicateKeyException;

    public void put(final Collection<R> records);

    public Entity getEntity(Entity entity);

    public Entity getEntityById(Entity entity);

    public Entity getEntityByName(Entity entity);

    public void insert(final R record);

    public void insertAll(final Collection<R> items);

    public void update(final R record);

    public void updateAll(final Collection<R> items);

    public void remove(final R entity);

    public Entity getByColumnName(String columnName, Object columnValue);

    public Collection<R> getByEntities(String columnName, Object columnValue);

    public void clear();

    public void refresh(final R entity);

    public Long size();
}
