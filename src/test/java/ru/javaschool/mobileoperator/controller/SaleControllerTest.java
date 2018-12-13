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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, WebSecurityConfig.class, H2Config.class, AspectConfig.class})
@ComponentScan("ru.javaschool.mobileoperator")
public class SaleControllerTest extends BaseTest {
    @Test
    public void salePageCorrectGetTest() throws Exception {
        mockMvc.perform(get("/sale").with(user("a").password("p").roles("OPERATOR")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void salePageNotOperatorTest() throws Exception {
        mockMvc.perform(get("/sale").with(user("b").password("p")))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}