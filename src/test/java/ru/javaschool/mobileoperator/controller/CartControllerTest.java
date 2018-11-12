package ru.javaschool.mobileoperator.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.javaschool.mobileoperator.BaseTest;
import ru.javaschool.mobileoperator.config.AspectConfig;
import ru.javaschool.mobileoperator.config.HibernateConfig;
import ru.javaschool.mobileoperator.config.WebAppConfig;
import ru.javaschool.mobileoperator.config.WebSecurityConfig;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, WebSecurityConfig.class, HibernateConfig.class, AspectConfig.class})
@ComponentScan("ru.javaschool.mobileoperator")
public class CartControllerTest extends BaseTest {

    @Test
    public void cartPageTest() throws Exception{
        this.mockMvc.perform(get("/cart").with(user("a").password("p")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void cartConfirmRedirectionTest() throws Exception{
        this.mockMvc.perform(post("/cart/confirm").with(user("a").password("p")))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart"));
    }

    @Test
    public void cartRemoveItemTest() throws Exception{
        this.mockMvc.perform(post("/cart/remove")
                    .param("itemId", "0")
                    .with(user("a")
                            .password("p")))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
