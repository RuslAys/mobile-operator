package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.User;

public interface UserDao extends GenericDao<User, String> {
    User getUser(String username);
}
