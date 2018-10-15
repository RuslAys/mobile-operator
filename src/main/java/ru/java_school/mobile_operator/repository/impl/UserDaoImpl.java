package ru.java_school.mobile_operator.repository.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ru.java_school.mobile_operator.domain.User;
import ru.java_school.mobile_operator.repository.UserDao;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long>
        implements UserDao {
    @Override
    public boolean isUserActive(Long id) {
        return find(id).isActive();
    }

    @Override
    public User getUser(String username) {
        Query query = currentSession().createQuery(
                "from USR where username=:username"
        );
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }
}
