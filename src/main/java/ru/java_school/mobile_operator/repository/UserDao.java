package ru.java_school.mobile_operator.repository;

import ru.java_school.mobile_operator.domain.User;

public interface UserDao extends GenericDao<User, Long> {
    boolean isUserActive(Long id);
    User getUser(String username);
}
