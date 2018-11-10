package ru.javaschool.mobileoperator.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.javaschool.mobileoperator.domain.Authority;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;

public class RoleHelper {
    public boolean isOnlyUser(UserDetails userDetails){
        for (GrantedAuthority authority: userDetails.getAuthorities()){
            if(authority.getAuthority().equals("ROLE_USER")){
                return true;
            }
        }
        return false;
    }

    public boolean isOnlyUser(User user){
        for (Authority authority: user.getAuthorities()){
            if(UserRoleEnum.USER.name().equals(authority.getAuthority())){
                return true;
            }
        }
        return false;
    }
}
