package com.krakus.bizimsemt.service;

import com.krakus.bizimsemt.data.BizimTemplate;
import com.krakus.bizimsemt.domain.Buyer;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.krakus.bizimsemt.data.DataUtils.addresses;
import static com.krakus.bizimsemt.data.DataUtils.birthDate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("it")
public class BuyerServiceIntegrationTest {

    private static final String ID_FROM_DB = "5e9b225410f90613850f25e8";

    @Autowired
    private BizimTemplate bizimTemplate;
    @Autowired
    private BuyerService buyerService;
    private ObjectId id = new ObjectId();

    @BeforeEach
    public void setUp() {
        bizimTemplate.init("buyers");
    }

    @AfterEach
    public void reset() {
        bizimTemplate.remove(new Query(Criteria.where("id").is(id)), Buyer.class);
    }

    @DisplayName("given a football player" +
            "when we add him as a buyer" +
            "then it is added to db")
    @Test
    public void testAddElvirBolic() {
        // Prepare new record
        Buyer buyer = new Buyer(id.toString(), "Elvir", "Bolic", "1234567890", birthDate(40), addresses());

        // Add buyer
        Buyer newBuyer = buyerService.add(buyer);

        // Verify
        assertThat(buyerService.find(id.toString())).isPresent();
        assertThat(newBuyer).isSameAs(buyer);
    }

    @DisplayName("given a football player" +
            "when we add him as a buyer" +
            "then it is added to db")
    @Test
    public void testAddViorelMoldovan() {
        // Prepare new record
        Buyer buyer = new Buyer(id.toString(), "Viorel", "Moldovan", "0987654321", birthDate(45), addresses());

        // Add buyer
        Buyer newBuyer = buyerService.add(buyer);

        // Verify
        assertThat(buyerService.find(id.toString())).isPresent();
        assertThat(newBuyer).isSameAs(buyer);
    }

    @DisplayName("given pre-inserted data records" +
            "when we search for one" +
            "then it is fetched and present")
    @Test
    public void testFind() {
        Optional<Buyer> buyer = buyerService.find(ID_FROM_DB);
        assertThat(buyer).isPresent();
    }

}
