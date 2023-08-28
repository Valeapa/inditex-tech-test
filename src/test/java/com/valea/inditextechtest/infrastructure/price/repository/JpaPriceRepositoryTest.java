package com.valea.inditextechtest.infrastructure.price.repository;

import com.valea.inditextechtest.infrastructure.price.entity.PriceEntity;
import com.valea.inditextechtest.infrastructure.price.repository.JpaPriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


/**
 * DB integration test. Hooked with H2 DB.
 */
@DataJpaTest
public class JpaPriceRepositoryTest {

    @Autowired
    private JpaPriceRepository priceRepository;


    @Test
    public void findApplicablePriceTest() {
        Optional<PriceEntity> opPrice = priceRepository
                .findApplicablePriceOrderedByPriorityDesc(35455,
                                                          1,
                                                          LocalDateTime.of(2020, 6, 14, 18, 0));

        Assertions.assertTrue(opPrice.isPresent());
        PriceEntity priceEntity = opPrice.get();
        Assertions.assertEquals(35455, priceEntity.getProductId());
        Assertions.assertEquals(1, priceEntity.getBrandId());
        Assertions.assertEquals(2, priceEntity.getPriceList());
        Assertions.assertEquals(1, priceEntity.getPriority());
        Assertions.assertEquals(BigDecimal.valueOf(25.45), priceEntity.getPrice());
        Assertions.assertEquals("EUR", priceEntity.getCurr());
        Assertions.assertEquals(LocalDateTime.of(2020, 6, 14, 15, 0), priceEntity.getStartDate());
        Assertions.assertEquals(LocalDateTime.of(2020, 6, 14, 18, 30), priceEntity.getEndDate());
    }


    @Test
    public void noResultTest() {
        Optional<PriceEntity> opPrice = priceRepository
                .findApplicablePriceOrderedByPriorityDesc(35455,
                                                          2,    // No register for this brand
                                                          LocalDateTime.of(2020, 6, 14, 18, 0));

        Assertions.assertFalse(opPrice.isPresent());
    }
}
