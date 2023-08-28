package com.valea.inditextechtest.domain.price.port.in;

import com.valea.inditextechtest.domain.price.model.Price;
import com.valea.inditextechtest.domain.price.exception.PriceNotFoundException;

import java.time.LocalDateTime;


public interface GetProductApplicablePrice {

    /**
     * Get the product's applicable price.
     *
     * @param productId searched product (required)
     * @param brandId group chain (1 = ZARA) (required)
     * @param applicableDateTime price applicable date-time (required)
     * @return Product's applicable price found
     * @throws PriceNotFoundException in case a price wasn't found with the given parameters
     */
    Price getProductApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicableDateTime) throws PriceNotFoundException;
}
