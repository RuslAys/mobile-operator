package ru.javaschool.mobileoperator;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.javaschool.mobileoperator.domain.Authority;
import ru.javaschool.mobileoperator.domain.User;
import ru.javaschool.mobileoperator.domain.enums.UserRoleEnum;
import ru.javaschool.mobileoperator.service.api.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@Configuration
@ComponentScan("ru.javaschool.mobileoperator")
@EnableWebMvc
public class BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

//    @Before
//    public void setup(){
//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
//    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    protected static final String ADMIN_USERNAME = "a";
    protected static final String USER_USERNAME = "u";
    protected static final String PASSWORD = "p";


    public void createAdmin(){
        User user = new User();
        user.setUsername(ADMIN_USERNAME);
        user.setPassword(passwordEncoder.encode(PASSWORD));
        user.setEnabled(true);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(user, UserRoleEnum.OPERATOR.name()));
        authorities.add(new Authority(user, UserRoleEnum.ADMIN.name()));
        user.setAuthorities(authorities);
        userService.add(user);
    }

    public void createUser(){
        User user = new User();
        user.setUsername(USER_USERNAME);
        user.setPassword(passwordEncoder.encode(PASSWORD));
        user.setEnabled(true);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(user, UserRoleEnum.USER.name()));
        user.setAuthorities(authorities);
        userService.add(user);
    }
}
