package org.jetsoft.web.jssystemapp.configuration.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.jetsoft.web.jssystemapp.configuration.security.CommonSecurityConfig.CUSTOMER_ROLE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "dev")
class EndpointsSecurityTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "customer", password = "pass", authorities = CUSTOMER_ROLE)
    void shouldNotLetCustomerToEmployeePortal() throws Exception {

        mvc.perform(get("/employee/profileView")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "customer", password = "pass", authorities = CUSTOMER_ROLE)
    void shouldNotLetCustomerToPilotList() throws Exception {

        mvc.perform(get("/employee/flightList")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "tcolqueran1@narod.ru", password = "AcRnktrJ4", authorities = CUSTOMER_ROLE)
    void shouldLetCustomerToProfile() throws Exception {

        mvc.perform(get("/customer/profileView")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "tcolqueran1@narod.ru", password = "AcRnktrJ4", authorities = CUSTOMER_ROLE)
    void shouldLetCustomerToCustomerFLightList() throws Exception {

        mvc.perform(get("/customerPublic/flightList")).andExpect(status().isOk());
    }

    @Test
    void shouldLetAnyone() throws Exception {

        mvc.perform(get("/customerPublic/flightList")).andExpect(status().isOk());
    }
}