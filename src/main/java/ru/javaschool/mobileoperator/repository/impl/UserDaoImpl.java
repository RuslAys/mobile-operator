package ru.javaschool.mobileoperator.repository.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.repository.UserDao;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, String>
        implements UserDao {

    @Override
    public User getUser(String username) {
        return currentSession().get(User.class, username);
    }
}
