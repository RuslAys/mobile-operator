package ru.javaschool.mobileoperator.repository.api;

import ru.javaschool.mobileoperator.domain.User;

public interface UserDao extends GenericDao<User, String> {
    /**
     * Method to find user by username
     * @param username username
     * @return user
     */
    User getUser(String username);
}
