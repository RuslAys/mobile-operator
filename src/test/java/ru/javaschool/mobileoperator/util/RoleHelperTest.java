package ru.javaschool.mobileoperator.util;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.javaschool.mobileoperator.domain.Authority;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;
import ru.javaschool.mobileoperator.service.converter.UserDetailsImpl;
import ru.javaschool.mobileoperator.utils.RoleHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RoleHelperTest {

    RoleHelper roleHelper = new RoleHelper();

    @Ignore
    @Test
    public void isOnlyUserRoleUserDetails(){
        UserDetails userDetails = new UserDetailsImpl();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE" + UserRoleEnum.USER.name()));
        ((UserDetailsImpl) userDetails).setAuthorities(authorities);
        Assert.assertTrue(roleHelper.isOnlyUser(userDetails));
    }

    @Test
    public void isOnlyUserRoleUser(){
        User user = new User();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(user, UserRoleEnum.USER.name()));
        user.setAuthorities(authorities);
        Assert.assertTrue(roleHelper.isOnlyUser(user));
    }

    @Test
    public void isNotOnlyUserRoleUserRoleOperator(){
        User user = new User();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(user, UserRoleEnum.USER.name()));
        authorities.add(new Authority(user, UserRoleEnum.OPERATOR.name()));
        user.setAuthorities(authorities);
        Assert.assertFalse(roleHelper.isOnlyUser(user));
    }

    @Test
    public void isNotOnlyUserRoleOperator(){
        User user = new User();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(user, UserRoleEnum.OPERATOR.name()));
        user.setAuthorities(authorities);
        Assert.assertFalse(roleHelper.isOnlyUser(user));
    }
}
