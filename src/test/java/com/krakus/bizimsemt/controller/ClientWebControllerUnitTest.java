package com.krakus.bizimsemt.controller;

import com.krakus.bizimsemt.config.ApplicationSecurityConfiguration;
import com.krakus.bizimsemt.config.BizimsemtConfig;
import com.krakus.bizimsemt.config.BizimsemtProperties;
import com.krakus.bizimsemt.config.SpringSecurityWebAuxTestConfig;
import com.krakus.bizimsemt.controller.web.ClientWebController;
import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.domain.Gender;
import com.krakus.bizimsemt.service.BuyerService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.krakus.bizimsemt.data.DataUtils.addresses;
import static com.krakus.bizimsemt.data.DataUtils.birthDate;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ClientWebController.class)
@Import({BizimsemtConfig.class, BizimsemtProperties.class, ApplicationSecurityConfiguration.class, SpringSecurityWebAuxTestConfig.class})
@ActiveProfiles("ut")
public class ClientWebControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuyerService buyerService;

    @InjectMocks
    private ClientWebController clientWebController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("given a football player " +
            "when clients/fetch endpoint is invoked " +
            "then asked player is fetched")
    @Test
    @WithUserDetails("admin")
    public void testAddContactHappyPath() throws Exception {
        Buyer buyer = buyer();
        when(buyerService.find(any(String.class))).thenReturn(Optional.of(buyer));

        mockMvc
                .perform(post("/clients/fetch")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", buyer.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("client", hasProperty("id", is(buyer.getId()))))
                .andExpect(model().attribute("client", hasProperty("name", is(buyer.getName()))))
                .andExpect(model().attribute("client", hasProperty("surname", is(buyer.getSurname()))))
                .andExpect(model().attribute("client", hasProperty("addresses", is(buyer.getAddresses()))))
                .andReturn();
    }

    @DisplayName("given a football player " +
            "when unauthorized user invokes /clients/fetch endpoint " +
            "then request is rejected with 403")
    @Test
    @WithUserDetails("user")
    public void testAddContactWithUnauthorizedUser() throws Exception {
        Buyer buyer = buyer();
        when(buyerService.find(any(String.class))).thenReturn(Optional.of(buyer));

        mockMvc
                .perform(post("/clients/fetch"))
                .andExpect(status().is(403))
                .andReturn();
    }

    private Buyer buyer() {
        return new Buyer(ObjectId.get().toHexString(), "Dirk", "Kuyt", "9191919", Gender.MALE, birthDate(46), addresses());
    }

}
