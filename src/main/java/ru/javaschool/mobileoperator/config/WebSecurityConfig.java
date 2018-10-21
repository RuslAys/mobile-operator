package ru.javaschool.mobileoperator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.javaschool.mobileoperator.service.UserService;

/**
 * Security configuration
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//        auth.inMemoryAuthentication()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("a").password("p").roles("USER","ADMIN")
//                .and()
//                .withUser("u").password("p").roles("USER");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests().anyRequest().authenticated()
////                .and()
////                    .authorizeRequests()
////                    .antMatchers("/admin**")
////                    .access("hasRole('ROLE_ADMIN')")
//                .and()
//                    .authorizeRequests().antMatchers("/login**").permitAll()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("/loginAction")
//                    .permitAll()
//                .and()
//                    .logout().logoutSuccessUrl("/login").permitAll()
//                .and()
//                    .csrf().disable();
        http
//                .authorizeRequests()
//                    .anyRequest()
//                    .authenticated()
//                .and()
                .authorizeRequests()
                    .antMatchers("/login**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
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