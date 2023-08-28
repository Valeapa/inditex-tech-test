package com.valea.inditextechtest.infrastructure.price.controller.impl;

import com.valea.inditextechtest.application.price.service.PriceService;
import com.valea.inditextechtest.domain.price.model.Price;
import com.valea.inditextechtest.domain.price.exception.PriceNotFoundException;
import com.valea.inditextechtest.infrastructure.price.controller.PriceController;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class PriceControllerImpl implements PriceController {


    private final PriceService priceService;

    public PriceControllerImpl(PriceService priceService) {
        this.priceService = priceService;
    }


    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Price getApplicablePrice(Integer productId, Integer brandId, OffsetDateTime applicableDateTime) throws PriceNotFoundException {
        return priceService.getProductApplicablePrice(productId, brandId, applicableDateTime.toLocalDateTime());
    }

}
