package ru.javaschool.mobileoperator.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.javaschool.mobileoperator.domain.Authority;
import ru.javaschool.mobileoperator.service.RedirectService;

public class RedirectServiceImpl implements RedirectService {

    @Override
    public String redirectUser(UserDetails user) {

        return null;
    }
}
