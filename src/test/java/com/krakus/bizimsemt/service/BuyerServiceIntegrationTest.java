package com.krakus.bizimsemt.service;

import com.krakus.bizimsemt.component.BuyerCollection;
import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.repository.BuyerRepository;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerServiceIntegrationTest {

    @Autowired
    private BuyerCollection buyerCollection;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private BuyerService buyerService;
    private ObjectId id = new ObjectId();

    @After
    public void reset() {
        buyerRepository.deleteById(id);
    }

    @Test
    public void testAddBuyer() {
        // Prepare new record
        List<String> addresses = new ArrayList<>();
        addresses.add("Lorem ipsum");
        addresses.add("dolor sit amet");
        Buyer buyer = new Buyer(id.toString(), "Elvir", "Bolic", "1234567890", LocalDateTime.now().minusYears(40), addresses);

        // Add buyer
        Buyer newBuyer = buyerService.add(buyer);

        // Verify
        assertThat(buyerService.find(id.toString())).isPresent();
        assertThat(newBuyer).isSameAs(buyer);
    }

    @Test
    public void testAddBuyer2() {
        // Prepare new record
        List<String> addresses = new ArrayList<>();
        addresses.add("foo");
        addresses.add("bar");
        Buyer buyer = new Buyer(id.toString(), "Viorel", "Moldovan", "0987654321", LocalDateTime.now().minusYears(45), addresses);

        // Add buyer
        Buyer newBuyer = buyerService.add(buyer);

        // Verify
        assertThat(buyerService.find(id.toString())).isPresent();
        assertThat(newBuyer).isSameAs(buyer);
    }

}
