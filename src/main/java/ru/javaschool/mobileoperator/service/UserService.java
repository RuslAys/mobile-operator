package ru.javaschool.mobileoperator.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.javaschool.mobileoperator.domain.User;

public interface UserService extends GenericService<User, Long>, UserDetailsService {
    boolean isUserActive(Long id);
    User getUser(String username);
}
