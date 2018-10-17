package ru.javaschool.mobileoperator.repository;

import ru.javaschool.mobileoperator.domain.User;

public interface UserDao extends GenericDao<User, Long> {
    boolean isUserActive(Long id);
    User getUser(String username);
}
