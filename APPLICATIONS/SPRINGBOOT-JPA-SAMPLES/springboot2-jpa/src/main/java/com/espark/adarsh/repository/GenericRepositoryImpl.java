package com.espark.adarsh.repository;


import com.espark.adarsh.bean.InBean;
import com.espark.adarsh.bean.RangeCriteria;
import com.espark.adarsh.bean.SearchBean;
import com.espark.adarsh.bean.SearchCriteria;
import com.espark.adarsh.entity.DbEntity;
import com.espark.adarsh.entity.Employee;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Repository
public class GenericRepositoryImpl implements GenericRepository{

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<DbEntity> search(SearchBean searchBean) throws Exception {
        List<SearchCriteria> andSearchCriteria=searchBean.getAndSearchCriteria();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DbEntity> builderQuery = builder.createQuery(searchBean.getClassType());
        Root root = builderQuery.from(searchBean.getClassType());
        Predicate predicate = builder.conjunction();
        for (SearchCriteria param : andSearchCriteria) {
            if (param.getOperation().equalsIgnoreCase(">")) {
                predicate = builder.and(predicate,
                        builder.greaterThan(root.get(param.getKey()),
                                param.getValue().toString()));
            }else  if (param.getOperation().equalsIgnoreCase(">=")) {
                predicate = builder.and(predicate,
                        builder.greaterThanOrEqualTo(root.get(param.getKey()),
                                param.getValue().toString()));
            }else if (param.getOperation().equalsIgnoreCase("<")) {
                predicate = builder.and(predicate,
                        builder.lessThan(root.get(param.getKey()),
                                param.getValue().toString()));
            }else if (param.getOperation().equalsIgnoreCase("=<")) {
                predicate = builder.and(predicate,
                        builder.lessThanOrEqualTo(root.get(param.getKey()),
                                param.getValue().toString()));
            }else if (param.getOperation().equalsIgnoreCase(":lk:")||
                    param.getOperation().equalsIgnoreCase("::")) {
                if (root.get(param.getKey()).getJavaType() == String.class) {
                    predicate = builder.and(predicate,
                            builder.like(root.get(param.getKey()),
                                    "%" + param.getValue() + "%"));
                } else {
                    predicate = builder.and(predicate,
                            builder.equal(root.get(param.getKey()), param.getValue()));
                }
            } else if(param.getOperation().equalsIgnoreCase(":neq:")){
                predicate = builder.and(predicate,
                        builder.notEqual(root.get(param.getKey()),
                                param.getValue().toString()));
            }else if(param.getOperation().equalsIgnoreCase(":eq:")){
                predicate = builder.and(predicate,
                        builder.equal(root.get(param.getKey()),
                                param.getValue().toString()));
            }
        }
        List<SearchCriteria> orSearchCriteria=searchBean.getOrSearchCriteria();
        for (SearchCriteria param : orSearchCriteria) {
            if (param.getOperation().equalsIgnoreCase(">")) {
                predicate = builder.or(predicate,
                        builder.greaterThanOrEqualTo(root.get(param.getKey()),
                                param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase("<")) {
                predicate = builder.or(predicate,
                        builder.lessThanOrEqualTo(root.get(param.getKey()),
                                param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase("lk")||
                    param.getOperation().equalsIgnoreCase("::")) {
                if (root.get(param.getKey()).getJavaType() == String.class) {
                    predicate = builder.or(predicate,
                            builder.like(root.get(param.getKey()),
                                    "%" + param.getValue() + "%"));
                } else {
                    predicate = builder.or(predicate,
                            builder.equal(root.get(param.getKey()), param.getValue()));
                }
            }else if(param.getOperation().equalsIgnoreCase("neq")){
                predicate = builder.and(predicate,
                        builder.notEqual(root.get(param.getKey()),
                                param.getValue().toString()));
            }else if(param.getOperation().equalsIgnoreCase("eq")){
                predicate = builder.and(predicate,
                        builder.equal(root.get(param.getKey()),
                                param.getValue().toString()));
            }
        }

            List<RangeCriteria> rangeCriteria = searchBean.getRangeCriteria();
            for (RangeCriteria criteria : rangeCriteria) {
                if (criteria.isDate()) {
                    predicate = builder.between(root.get(criteria.getFieldName())
                            , this.getDate(criteria.getLowerRange())
                            , this.getDate(criteria.getUpperRange()));
                }
                if (criteria.isNumber()) {
                    predicate = builder.between(root.get(criteria.getFieldName())
                            , Long.valueOf(criteria.getLowerRange().toString())
                            , Long.valueOf(criteria.getUpperRange().toString()));
                }
            }


        List<InBean> inList=searchBean.getInList();
         for(InBean inBean:inList) {
             final String fieldName=inBean.getFieldName();
             CriteriaBuilder.In<Object> inPredicate = builder.in(root.get(fieldName));
             for(Object value :inBean.getData()) {
                 inPredicate.value(value);
             }
         }

        builderQuery.where(predicate);
        Query query =entityManager.createQuery(builderQuery);
        if(searchBean.getPageNumber()!=null && searchBean.getPageSize()!=null) {
            query.setFirstResult((searchBean.getPageNumber() - 1) * searchBean.getPageSize());
            query.setMaxResults(searchBean.getPageSize());
        }
        List<DbEntity> result = query.getResultList();
        return result;
    }


    // 2018-04-14 00:00:00
    private Date getDate(Object planPubTime) throws ParseException {
        Date planPubDate = null;
        if (!StringUtils.isEmpty(planPubTime)) {
            planPubDate = DateUtils.parseDate(planPubTime.toString(), new String[]{"yyyy-MM-dd HH:mm:ss"});
        }
        return planPubDate;
    }

    public List<Employee> getAllEmployee() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> q = cb.createQuery(Employee.class);
        Root<Employee> c = q.from(Employee.class);
        q.select(c);
        TypedQuery<Employee> query = entityManager.createQuery(q);
        List<Employee> results = query.getResultList();
        return results;
    }

    Employee getStudentByName(String name){
        Criteria crit = entityManager.unwrap(Session.class).createCriteria(Employee.class);
        crit.add(Restrictions.eq("name", name));
        List<Employee> students = crit.list();
        return students.get(0);
    }
}
