package ru.javaschool.mobileoperator.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface RedirectService {
    String redirectUser(UserDetails user);
}
