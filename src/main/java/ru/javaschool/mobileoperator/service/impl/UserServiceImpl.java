package ru.javaschool.mobileoperator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.mobileoperator.domain.Authority;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;
import ru.javaschool.mobileoperator.repository.api.GenericDao;
import ru.javaschool.mobileoperator.repository.api.UserDao;
import ru.javaschool.mobileoperator.service.api.UserService;
import ru.javaschool.mobileoperator.service.converter.UserToUserDetails;
import ru.javaschool.mobileoperator.service.exceptions.UserDisabledException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User, String>
        implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserToUserDetails userUserDetailsConverter;

    public UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImpl") GenericDao<User, String> genericDao) {
        super(genericDao);
        this.userDao = (UserDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User getUser(String username) {
        User user = userDao.getUser(username);
        if (user == null) {
            //TODO написать обработчик ошибки
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addOperator(String username, String password) {
        username = username.toLowerCase();
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(user, UserRoleEnum.OPERATOR.name()));
        user.setAuthorities(authorities);
        add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userUserDetailsConverter.convert(getUser(username));
        if (userDetails == null) throw new UsernameNotFoundException("User not found");
        if (!userDetails.isEnabled()) throw new UserDisabledException("User is disabled");
        return userDetails;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll(UserRoleEnum role) {
        return findAll()
                .stream()
                .filter(user -> user.getAuthorities()
                        .stream()
                        .allMatch(
                                authority -> authority.getAuthority().equals(role.name())
                        )
                )
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void setOperatorActiveStatus(String username, boolean status) {
        User user = userDao.getUser(username);
        if (user.isEnabled() != status) {
            user.setEnabled(status);
            userDao.update(user);
        }
    }
}
