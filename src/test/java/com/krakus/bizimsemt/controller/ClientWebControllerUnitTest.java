package com.krakus.bizimsemt.controller;

import com.krakus.bizimsemt.config.BizimsemtConfig;
import com.krakus.bizimsemt.config.BizimsemtProperties;
import com.krakus.bizimsemt.controller.web.ClientWebController;
import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.service.BuyerService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
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


@RunWith(SpringRunner.class)
@WebMvcTest(ClientWebController.class)
@Import({BizimsemtConfig.class, BizimsemtProperties.class})
public class ClientWebControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuyerService buyerService;

    @InjectMocks
    private ClientWebController clientWebController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddContactHappyPath() throws Exception {
        String id = ObjectId.get().toString();
        Buyer buyer = new Buyer(id, "Dirk", "Kuyt", "9191919", birthDate(46), addresses());

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

}
