package com.valea.inditextechtest.domain.price.port.out;

import com.valea.inditextechtest.domain.price.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    /**
     * Gets a product's price with the given parameters.
     * @param productId product identifier
     * @param brandId brand identifier
     * @param applicableDateTime price applicable date-time
     * @return highest priority product price or empty Optional in case there isn't a register that meets the
     *         query parameters
     */
    Optional<Price> getApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicableDateTime);
}
