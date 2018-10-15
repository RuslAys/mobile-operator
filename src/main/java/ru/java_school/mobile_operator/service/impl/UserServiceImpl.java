package ru.java_school.mobile_operator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.java_school.mobile_operator.domain.User;
import ru.java_school.mobile_operator.repository.GenericDao;
import ru.java_school.mobile_operator.repository.UserDao;
import ru.java_school.mobile_operator.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long>
        implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImpl") GenericDao<User, Long> genericDao){
        super(genericDao);
        this.userDao = (UserDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean isUserActive(Long id) {
        return userDao.isUserActive(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User getUser(String username) {
        return userDao.getUser(username);
    }
}
