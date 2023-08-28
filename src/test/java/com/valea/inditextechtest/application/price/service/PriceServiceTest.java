package com.valea.inditextechtest.application.price.service;

import com.valea.inditextechtest.domain.price.exception.PriceNotFoundException;
import com.valea.inditextechtest.domain.price.model.MonetaryPvp;
import com.valea.inditextechtest.domain.price.model.Price;
import com.valea.inditextechtest.domain.price.port.out.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Optional;

/**
 * PriceService unit test
 */
@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private PriceRepository priceRepository;



    @Test
    public void getExpectedProductPriceTest() throws PriceNotFoundException {
        Price price = new Price(1, 1, 2, new MonetaryPvp(BigDecimal.valueOf(14.99), Currency.getInstance("EUR")),
                                LocalDateTime.of(2023,1, 1, 0, 0, 0),
                                LocalDateTime.of(2023,12, 31, 23, 59, 59));

        LocalDateTime queryDateTime = LocalDateTime.of(2023, 8, 27, 14, 34);
        Mockito.when(priceRepository.getApplicablePrice(1, 1, queryDateTime))
                .thenReturn(Optional.of(price));

        Price result = priceService.getProductApplicablePrice(1, 1, queryDateTime);

        Assertions.assertEquals(price, result);
    }


    @Test
    public void throwPriceNotFoundException() {

        LocalDateTime now = LocalDateTime.now();
        Mockito.when(priceRepository.getApplicablePrice(1, 1, now))
                .thenReturn(Optional.empty());

        PriceNotFoundException priceNotFoundException = Assertions.assertThrows(PriceNotFoundException.class, () -> {
            priceService.getProductApplicablePrice(1, 1, now);
        });

        Assertions.assertNotNull(priceNotFoundException.getMessage());

    }
}
