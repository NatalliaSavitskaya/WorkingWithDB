package myapp.services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public User getUserById(String id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Transactional
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Transactional
    public void updateUserNameByID(User user, String newName) {
        user.setName(newName);
        sessionFactory.getCurrentSession().update(user);
    }
}