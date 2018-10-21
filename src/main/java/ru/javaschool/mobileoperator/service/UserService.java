package ru.javaschool.mobileoperator.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.javaschool.mobileoperator.domain.User;

public interface UserService extends GenericService<User, String>, UserDetailsService {
    User getUser(String username);
    void addOperator(String username, String password);
}
