package ru.javaschool.mobileoperator;

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
import ru.javaschool.mobileoperator.config.AspectConfig;
import ru.javaschool.mobileoperator.config.HibernateConfig;
import ru.javaschool.mobileoperator.config.SpringMvcInitializer;
import ru.javaschool.mobileoperator.config.SpringSecurityInitializer;
import ru.javaschool.mobileoperator.config.WebAppConfig;
import ru.javaschool.mobileoperator.config.WebSecurityConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, WebSecurityConfig.class, HibernateConfig.class, AspectConfig.class})
@ComponentScan("ru.javaschool.mobileoperator")
public class CartControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void cartPageTest() throws Exception{
        this.mockMvc.perform(get("/cart"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void cartConfirmRedirectionTest() throws Exception{
        this.mockMvc.perform(post("/cart/confirm"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart"));
    }

    @Test
    public void cartRemoveItemTest() throws Exception{
        this.mockMvc.perform(post("/cart/remove").param("itemId", "0"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
