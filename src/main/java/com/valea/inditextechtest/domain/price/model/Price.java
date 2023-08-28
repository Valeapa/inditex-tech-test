package com.valea.inditextechtest.domain.price.model;

import java.time.LocalDateTime;

/**
 * Product's price info.
 * @param productId product identifier
 * @param brandId brand identifier
 * @param priceList price tariff identifier
 * @param pvp product final price
 * @param startDate price starting date
 * @param endDate price end date
 */
public record Price(Integer productId, Integer brandId, Integer priceList,
                    MonetaryPvp pvp, LocalDateTime startDate, LocalDateTime endDate) {
}
