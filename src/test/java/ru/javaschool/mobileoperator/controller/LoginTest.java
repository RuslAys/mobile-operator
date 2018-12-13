package ru.javaschool.mobileoperator.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.javaschool.mobileoperator.BaseTest;
import ru.javaschool.mobileoperator.config.H2Config;
import ru.javaschool.mobileoperator.config.WebAppConfig;
import ru.javaschool.mobileoperator.config.WebSecurityConfig;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, WebSecurityConfig.class, H2Config.class})
@ComponentScan("ru.javaschool.mobileoperator")
public class LoginTest extends BaseTest {
    @Test
    public void searchWithOutLoginTest() throws Exception{
        this.mockMvc.perform(post("/search").param("username", "a"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void correctAdminLoginTest() throws Exception{
        createAdmin();
        this.mockMvc.perform(formLogin().user("a").password("p"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void correctUserLoginTest() throws Exception{
        createUser();
        this.mockMvc.perform(formLogin().user("u").password("p"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        removeAllUsers();
    }

    @Test
    public void badCredentials() throws Exception{
        this.mockMvc.perform(post("/login")
                .param("username", "a")
                .param("password", "b"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    public void testBadRequest() throws Exception{
        this.mockMvc.perform(get("/profile/79817549091"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}
