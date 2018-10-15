package ru.java_school.mobile_operator.service;

import ru.java_school.mobile_operator.domain.User;

public interface UserService extends GenericService<User, Long>{
    boolean isUserActive(Long id);
    User getUser(String username);
}
