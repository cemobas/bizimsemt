package com.krakus.bizimsemt.service;

import com.krakus.bizimsemt.domain.Buyer;
import com.krakus.bizimsemt.repository.BuyerRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static com.krakus.bizimsemt.data.DataUtils.addresses;
import static com.krakus.bizimsemt.data.DataUtils.birthDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("ut")
public class BuyerServiceUnitTest {

    @Mock
    private BuyerRepository buyerRepository;
    @InjectMocks
    private BuyerService buyerService;
    private ObjectId id = new ObjectId();

    @DisplayName("given a football player" +
            "when we save through buyer repo" +
            "then it is saved")
    @Test
    public void testAddBuyer() {
        // Prepare new record
        Buyer mockSavedBuyer = new Buyer(id.toString(), "Elvir", "Bolic", "1234567890", birthDate(40), addresses());
        when(buyerRepository.save(any(Buyer.class))).thenReturn(mockSavedBuyer);

        Buyer mockedBuyer = mock(Buyer.class);
        when(mockedBuyer.getName()).thenReturn("dummy name");

        // Add buyer
        Buyer newBuyer = buyerService.add(mockedBuyer);

        // Verify
        assertThat(newBuyer).isSameAs(mockSavedBuyer);
    }

}
