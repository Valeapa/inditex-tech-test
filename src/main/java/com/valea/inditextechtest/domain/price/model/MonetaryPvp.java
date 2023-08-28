package com.valea.inditextechtest.domain.price.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Product's monetary info.
 * @param value product's price
 * @param currency price's currency
 */
public record MonetaryPvp(BigDecimal value, @Schema(name = "currency", example = "EUR") Currency currency) {
}
