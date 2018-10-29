package ru.javaschool.mobileoperator.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class RoleHelper {
    public boolean isOnlyUser(UserDetails userDetails){
        return userDetails.getAuthorities().stream()
                .noneMatch(authority ->
                        ((GrantedAuthority) authority)
                                .getAuthority()
                                .equals("ROLE_OPERATOR")
                                || ((GrantedAuthority) authority)
                                .getAuthority()
                                .equals("ROLE_ADMIN")
                );
    }
}
