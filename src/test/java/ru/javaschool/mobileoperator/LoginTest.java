package ru.javaschool.mobileoperator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.javaschool.mobileoperator.config.AspectConfig;
import ru.javaschool.mobileoperator.config.HibernateConfig;
import ru.javaschool.mobileoperator.config.SpringMvcInitializer;
import ru.javaschool.mobileoperator.config.SpringSecurityInitializer;
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
@ContextConfiguration(classes = {SpringMvcInitializer.class, SpringSecurityInitializer.class,
        WebAppConfig.class, WebSecurityConfig.class, HibernateConfig.class, AspectConfig.class})
@ComponentScan("ru.javaschool.mobileoperator")
public class LoginTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testRedirection() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void correctAdminLoginTest() throws Exception{
        this.mockMvc.perform(formLogin().user("a").password("p"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void correctUserLoginTest() throws Exception{
        this.mockMvc.perform(formLogin().user("79817549091").password("p"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile/79817549091"));
    }

    @Test
    public void badCredentials() throws Exception{
        this.mockMvc.perform(post("/login")
                .param("username", "a")
                .param("password", "b"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testBadRequest() throws Exception{
        this.mockMvc.perform(get("/profile/79817549091"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
