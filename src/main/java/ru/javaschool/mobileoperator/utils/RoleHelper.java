package ru.javaschool.mobileoperator.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class RoleHelper {
    public boolean isOnlyUser(UserDetails userDetails){
        for (GrantedAuthority authority: userDetails.getAuthorities()){
            if(authority.getAuthority().equals("ROLE_USER")){
                return true;
            }
        }
        return false;
    }
}
