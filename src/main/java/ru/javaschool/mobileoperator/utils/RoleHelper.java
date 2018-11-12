package ru.javaschool.mobileoperator.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.javaschool.mobileoperator.domain.Authority;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;

/**
 * Util class with method for roles
 */
public class RoleHelper {
    /**
     * Method to check if user has only user role
     * @param userDetails user details
     * @return true if role is only user, opposite false
     */
    public boolean isOnlyUser(UserDetails userDetails){
        if(userDetails.getAuthorities().size() == 1){
            for (GrantedAuthority authority: userDetails.getAuthorities()){
                if(authority.getAuthority().equals("ROLE_USER")){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to check if user has only user role
     * @param user user
     * @return true if role is only user, opposite false
     */
    public boolean isOnlyUser(User user){
        if(user.getAuthorities().size() == 1){
            for (Authority authority: user.getAuthorities()){
                if(UserRoleEnum.USER.name().equals(authority.getAuthority())){
                    return true;
                }
            }
        }
        return false;
    }
}
