package ru.javaschool.mobileoperator.service.api;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;

import java.util.List;

public interface UserService extends GenericService<User, String>, UserDetailsService {
    User getUser(String username);
    List<User> findAll(UserRoleEnum role);
    void addOperator(String username, String password);
    void setOperatorActiveStatus(String username, boolean status);
}
