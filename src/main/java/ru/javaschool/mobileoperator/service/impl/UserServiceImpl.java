package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.repository.GenericDao;
import ru.javaschool.mobileoperator.repository.UserDao;
import ru.javaschool.mobileoperator.service.UserService;
import ru.javaschool.mobileoperator.service.converter.UserToUserDetails;

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User, String>
        implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserToUserDetails userUserDetailsConverter;

    public UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImpl") GenericDao<User, String> genericDao){
        super(genericDao);
        this.userDao = (UserDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User getUser(String username) {
        User user = userDao.getUser(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userUserDetailsConverter.convert(getUser(username));
    }
}
