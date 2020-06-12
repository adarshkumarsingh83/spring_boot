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
import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
public abstract class AbstractRepository<R>
        implements EntityRepository<R> {

    protected final Class<? extends Entity> entityClass;
    protected final String entityClassName;
    protected final String entityTableName;


    public AbstractRepository(final Class<? extends Entity> entityClass) {
        this.entityClass = entityClass;
        this.entityClassName = this.entityClass.getSimpleName();
        this.entityTableName = this.entityClass.getAnnotation(Table.class).name();
    }

    @Override
    public Entity getEntity(Entity entity) {
        return (Entity) getSession().load(entityClass, entity.getId());
    }

    @Override
    public Entity getEntityByName(Entity entity) {
        final Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq("userName", entity));
        return (Entity) criteria.uniqueResult();
    }

    @Override
    public Entity getEntityById(Entity entity) {
        final Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq("id", entity.getId()));
        return (Entity) criteria.uniqueResult();
    }

    @Override
    public List<R> getAll() {
        return getSession().createCriteria(entityClass).list();
    }


    public List<R> getAll(int limit) {
        return getSession().createCriteria(entityClass).list().subList(0, limit);
    }

    @Override
    public List<R> getAll(final Integer firstResult, final Integer maxResults) {
        final Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        final List<R> list = criteria.list();
        return list;
    }

    @Override
    public Long size() {
        final Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setProjection(Projections.rowCount()).uniqueResult();
        return (Long) criteria.uniqueResult();
    }

    @Override
    public void remove(final R entity) {
        try {
            getSession().delete(entity);
        } catch (final Exception e) {
            System.out.println("Unable to delete entity : " + this.entityClassName + ", :" + entity + e.getMessage());
        }
    }


    @Override
    public Entity getByColumnName(String columnName, Object columnValue) {
        final Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq(columnName, columnValue));
        return (Entity) criteria.uniqueResult();
    }

    @Override
    public Collection<R> getByEntities(String columnName, Object columnValue) {
        final Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq(columnName, columnValue));
        return (List<R>) criteria.list();
    }

    @Override
    public void clear() {
        getSession().createQuery("DELETE FROM " + entityClassName).executeUpdate();
    }

    @Override
    public void refresh(R entity) {
        getSession().refresh(entity);
    }


    @Override
    public List<R> getByExample(final R example) {
        final Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Example.create(example));
        final List<R> list = criteria.list();
        return list;
    }

    @Override
    public R getUniqueByExample(final R example) {
        final Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Example.create(example));
        R result = (R) criteria.uniqueResult();
        return result;
    }


    @Override
    public void insert(R record) {
        getSession().save(record);
    }

    @Override
    public void insertAll(final Collection<R> items) {
        for (final R record : items) {
            getSession().save(record);
        }
    }

    @Override
    public void put(final R record) {
        getSession().saveOrUpdate(record);
    }

    @Override
    public void put(final Collection<R> records) {
        Session session = getSession();
        for (R record : records) {
            session.saveOrUpdate(record);
        }
    }

    @Override
    public void update(final R record) throws HibernateException {
        getSession().update(record);
    }

    @Override
    public void updateAll(final Collection<R> items) {
        for (final R record : items) {
            getSession().update(record);
        }
    }


    /**
     * @param queryOrQueryName the HQL query, or the named query ID.
     * @param parameters       is a Object[] array identified by parameterPosition.
     * @return a list or an object based on the sql.
     * @throws IllegalArgumentException is thrown if the queryOrQueryName or firstResult is invalid.
     */
    protected final List<R> findByNamedQuery(final String queryOrQueryName, final Object... parameters) throws IllegalArgumentException {
        return findByNamedQuery(queryOrQueryName, 0, 0, parameters);
    }

    /**
     * @param queryOrQueryName the HQL query, or the named query ID.
     * @param firstResult      a non "0" number for the first result for any kinda of pagination.
     * @param maxResults       the maximum results we want to see per page. if this is "0" all results are returned.
     * @param parameters       is a Object[] array identified by parameterPosition.
     * @return a list or an object based on the sql.
     * @throws IllegalArgumentException is thrown if the queryOrQueryName or firstResult is invalid.
     */
    protected final List<R> findByNamedQuery(final String queryOrQueryName, final int firstResult, final int maxResults, final Object... parameters)
            throws IllegalArgumentException {
        return executeQuery(queryOrQueryName, true, false, false, firstResult, maxResults, parameters);
    }

    /**
     * @param queryOrQueryName the HQL query, or the named query ID.
     * @param parameters       is a Object[] array identified by parameterPosition.
     * @return a list or an object based on the sql.
     * @throws IllegalArgumentException is thrown if the queryOrQueryName or firstResult is invalid.
     */
    protected final List<R> findByQuery(final String queryOrQueryName, final Object... parameters) throws IllegalArgumentException {
        return findByQuery(queryOrQueryName, 0, 0, parameters);
    }

    /**
     * @param queryOrQueryName the HQL query, or the named query ID.
     * @param firstResult      a non "0" number for the first result for any kinda of pagination.
     * @param maxResults       the maximum results we want to see per page. if this is "0" all results are returned.
     * @param parameters       is a Object[] array identified by parameterPosition.
     * @return a list or an object based on the sql.
     * @throws IllegalArgumentException is thrown if the queryOrQueryName or firstResult is invalid.
     */
    protected final List<R> findByQuery(final String queryOrQueryName, final int firstResult, final int maxResults, final Object... parameters) throws IllegalArgumentException {
        return executeQuery(queryOrQueryName, false, false, false, firstResult, maxResults, parameters);
    }

    /**
     * @param query      the HQL query, or the named query ID.
     * @param parameters is a Object[] array identified by parameterPosition.
     * @return a list or an object based on the sql.
     * @throws IllegalArgumentException is thrown if the queryOrQueryName or firstResult is invalid.
     */
    protected final Integer executeQuery(final String query, final Object... parameters) throws IllegalArgumentException {
        return executeQuery(query, false, false, true, 0, 0, parameters);
    }

    /**
     * @param queryOrQueryName the HQL query, or the named query ID.
     * @param parameters       is a Object[] array identified by parameterPosition.
     * @return a list or an object based on the sql.
     * @throws IllegalArgumentException is thrown if the queryOrQueryName or firstResult is invalid.
     */
    protected final Integer executeNamedQuery(final String queryOrQueryName, final Object... parameters) throws IllegalArgumentException {
        return executeQuery(queryOrQueryName, true, false, true, 0, 0, parameters);
    }

    /**
     * Master execute query method. this does not support named parameter queries at this movement.
     *
     * @param <REZ_TYPE>       result type of the query we are expecting.
     * @param queryOrQueryName the HQL query, or the named query ID.
     * @param namedQuery       true if its a named query.
     * @param singleResult     true if we are expecting a single result.
     * @param isExecuteUpdate  true if we are executing a update statement.
     * @param firstResult      a non "0" number for the first result for any kinda of pagination.
     * @param maxResults       the maximum results we want to see per page. if this is "0" all results are returned.
     * @param parameters       is a Object[] array identified by parameterPosition.
     * @return a list or an object based on the sql.
     * @throws IllegalArgumentException is thrown if the queryOrQueryName or firstResult is invalid.
     */
    protected final <REZ_TYPE> REZ_TYPE executeQuery(final String queryOrQueryName, final boolean namedQuery, final boolean singleResult, final boolean isExecuteUpdate,
                                                     final int firstResult, final int maxResults, final Object... parameters) throws IllegalArgumentException {

        Assert.hasLength(queryOrQueryName, "Query for executing cannot be null");
        Assert.isTrue(firstResult > -1, "First result cannot be less than zero");

        try {
            Session session = getSession();
            Object result = null;

            Query query;
            if (namedQuery) {
                query = session.getNamedQuery(queryOrQueryName);
            } else {
                query = session.createQuery(queryOrQueryName);
            }
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    query.setParameter(i, parameters[i]);
                }
            }
            if (firstResult >= 0) {
                query.setFirstResult(firstResult);
            }
            if (maxResults >= 0) {
                query.setMaxResults(maxResults);
            }
            if (singleResult) {
                result = query.uniqueResult();
            } else if (!isExecuteUpdate) {
                result = query.list();
            } else {
                result = new Integer(query.executeUpdate());
            }
            if (result != null && !isExecuteUpdate) {
                if (singleResult) {
                    onFindForObject((R) result);
                } else {
                    onFindForList((List<R>) result);
                }
            }
            return (REZ_TYPE) result;
        } catch (final Exception e) {
            System.out.println("Unable to execute query :" + queryOrQueryName + ", namedQuery:" + namedQuery + ", singleResult" + singleResult + ", firstResult" + firstResult
                    + ", maxResults" + maxResults + ",parameters" + parameters + " " + e);
        }
        return null;
    }


    protected R onFindForObject(final R record) {
        return record;
        // can be overridden.
    }


    protected List<R> onFindForList(final List<R> records) {
        for (final R record : records) {
            onFindForObject(record);
        }
        // can be overridden.
        return records;
    }


    protected final Class<?> getEntityClass() {
        return entityClass;
    }

    /**
     * @return the entityClassName
     */
    protected final String getEntityClassName() {
        return entityClassName;
    }

    /**
     * @return the entityTableName
     */
    protected final String getEntityTableName() {
        return entityTableName;
    }

    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
