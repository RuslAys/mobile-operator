package ru.javaschool.mobileoperator.service.api;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;

import java.util.List;

public interface UserService extends GenericService<User, String>, UserDetailsService {
    /**
     * Method to get user by username
     * @param username username
     * @return user
     */
    User getUser(String username);

    /**
     * Method to find all users which have only one role
     * @param role user role
     * @return list with users
     */
    List<User> findAll(UserRoleEnum role);

    /**
     * Method to add new operator
     * @param username operator username
     * @param password operator password
     */
    void addOperator(String username, String password);

    /**
     * Method to activate / deactivate operator
     * @param username operator username
     * @param status operator status
     */
    void setOperatorActiveStatus(String username, boolean status);
}
