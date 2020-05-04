package com.krakus.bizimsemt.service;

import com.krakus.bizimsemt.data.BuyerCollection;
import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.repository.BuyerRepository;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import static com.krakus.bizimsemt.data.DataUtils.addresses;
import static com.krakus.bizimsemt.data.DataUtils.birthDate;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerServiceIntegrationTest {

    @Autowired
    private BuyerCollection buyerCollection;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BuyerService buyerService;
    private ObjectId id = new ObjectId();

    @After
    public void reset() {
        mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Buyer.class);
    }

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

}
