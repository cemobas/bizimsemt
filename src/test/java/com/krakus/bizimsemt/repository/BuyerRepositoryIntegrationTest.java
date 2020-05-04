package com.krakus.bizimsemt.repository;

import com.krakus.bizimsemt.config.BizimsemtConfig;
import com.krakus.bizimsemt.config.BizimsemtProperties;
import com.krakus.bizimsemt.domain.Buyer;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.krakus.bizimsemt.data.DataUtils.addresses;
import static com.krakus.bizimsemt.data.DataUtils.birthDate;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Import({BizimsemtConfig.class, BizimsemtProperties.class})
@RunWith(SpringRunner.class)
@DataMongoTest
public class BuyerRepositoryIntegrationTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BuyerRepository buyerRepository;
    private ObjectId id = ObjectId.get();
    private Buyer buyer;

    @Before
    public void setUp() {
        buyer = new Buyer(id.toString(), "Dirk", "Kuyt", "9191919", birthDate(46), addresses());
        mongoTemplate.save(buyer, "buyers");
    }

    @After
    public void reset() {
        mongoTemplate.remove(buyer, "buyers");
    }

    @Test
    public void testFindById() {
        Optional<Buyer> foundBuyer = buyerRepository.findById(id);

        assertThat(foundBuyer.isPresent(), is(equalTo(Boolean.TRUE)));
        assertThat(foundBuyer.get().getKimlikNo(), is(equalTo("9191919")));
    }

}
