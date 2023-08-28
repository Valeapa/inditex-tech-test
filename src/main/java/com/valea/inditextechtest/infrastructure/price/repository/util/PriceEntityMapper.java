package com.valea.inditextechtest.infrastructure.price.repository.util;

import com.valea.inditextechtest.infrastructure.price.entity.PriceEntity;
import com.valea.inditextechtest.domain.price.model.MonetaryPvp;
import com.valea.inditextechtest.domain.price.model.Price;
import org.springframework.stereotype.Component;

import java.util.Currency;

/**
 * Mapper to convert from adapter to domain.
 */
@Component
public class PriceEntityMapper {


    public Price toPrice(PriceEntity priceEntity) {
        return new Price(priceEntity.getProductId(),
                         priceEntity.getBrandId(),
                         priceEntity.getPriceList(),
                         new MonetaryPvp(priceEntity.getPrice(), parseCurrency(priceEntity.getCurr())),
                         priceEntity.getStartDate(),
                         priceEntity.getEndDate());
    }


    private Currency parseCurrency(String currencyCode) {
        return Currency.getInstance(currencyCode);
    }
}
