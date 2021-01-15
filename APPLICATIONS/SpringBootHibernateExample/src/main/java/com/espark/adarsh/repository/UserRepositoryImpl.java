package com.espark.adarsh.repository;

import com.espark.adarsh.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("userRepository")
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    @Override
    public void save(User user) {
        getSession().save(user);
        return;
    }

    @Override
    public void delete(User user) {
        getSession().delete(user);
        return;
    }

    @Override
    public List<User> getAll() {
        return getSession().createQuery("from User").list();
    }

    @Override
    public User getByEmail(String email) {
        return (User) getSession().createQuery(
                "from User where email = :email")
                .setParameter("email", email)
                .uniqueResult();
    }

    @Override
    public User getById(long id) {
        return (User) getSession().load(User.class, id);
    }

    @Override
    public void update(User user) {
        getSession().update(user);
        return;
    }

}
