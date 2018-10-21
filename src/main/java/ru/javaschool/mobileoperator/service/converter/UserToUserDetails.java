package ru.javaschool.mobileoperator.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.javaschool.mobileoperator.domain.User;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserToUserDetails implements Converter<User, UserDetails> {
    private static final String ROLE_PREFIX = "ROLE_";
    @Override
    public UserDetails convert(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        if(user != null){
            userDetails.setUsername(user.getUsername());
            userDetails.setPassword(user.getPassword());
            userDetails.setEnabled(user.isEnabled());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getAuthorities().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getAuthority()))
            );
            userDetails.setAuthorities(authorities);
        }
        return userDetails;
    }
}
