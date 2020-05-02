package com.krakus.bizimsemt.service;

import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.repository.BuyerRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BuyerServiceUnitTest {

    @Mock
    private BuyerRepository buyerRepository;
    @InjectMocks
    private BuyerService buyerService;
    private ObjectId id = new ObjectId();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddBuyer() {
        // Prepare new record
        List<String> addresses = new ArrayList<>();
        addresses.add("Lorem ipsum");
        addresses.add("dolor sit amet");
        Buyer mockSavedBuyer = new Buyer(id.toString(), "Elvir", "Bolic", "1234567890", LocalDateTime.now().minusYears(40), addresses);
        when(buyerRepository.save(any(Buyer.class))).thenReturn(mockSavedBuyer);

        Buyer mockedBuyer = mock(Buyer.class);
        when(mockedBuyer.getName()).thenReturn("dummy name");

        // Add buyer
        Buyer newBuyer = buyerService.add(mockedBuyer);

        // Verify
        assertThat(newBuyer).isSameAs(mockSavedBuyer);
    }

}
