package ru.javaschool.mobileoperator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javaschool.mobileoperator.service.api.UserService;
import ru.javaschool.mobileoperator.utils.CartHelper;
import ru.javaschool.mobileoperator.utils.LockHelper;
import ru.javaschool.mobileoperator.utils.OptionHelper;
import ru.javaschool.mobileoperator.utils.RoleHelper;

/**
 * Security configuration
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"ru.javaschool.mobileoperator.service"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHelper roleHelper(){
        return new RoleHelper();
    }

    @Bean
    public OptionHelper optionHelper(){
        return new OptionHelper();
    }

    @Bean
    public CartHelper cartHelper(){
        return new CartHelper();
    }

    @Bean
    public LockHelper lockHelper(){
        return new LockHelper();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        System.out.println(passwordEncoder().encode("p"));
        http
                .authorizeRequests()
                    .antMatchers("/login**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/sale/**").hasRole("OPERATOR")
                    .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginAction")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/login")
                    .permitAll()
                .and()
                    .csrf()
                    .disable();
    }
}