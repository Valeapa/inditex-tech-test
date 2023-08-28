package com.valea.inditextechtest.application.price.service;

import com.valea.inditextechtest.application.price.service.util.ExceptionMessageBuilder;
import com.valea.inditextechtest.domain.price.exception.PriceNotFoundException;
import com.valea.inditextechtest.domain.price.model.Price;
import com.valea.inditextechtest.domain.price.port.in.GetProductApplicablePrice;
import com.valea.inditextechtest.domain.price.port.out.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Facade to orchestrate price use cases.
 */
@Service
public class PriceService implements GetProductApplicablePrice {

    private static final Logger logger = LoggerFactory.getLogger(PriceService.class);

    private final PriceRepository priceRepository;


    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Price getProductApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicableDateTime) throws PriceNotFoundException {
        logger.info("Looking for product's price with params [productId={}, brandId={}, applicableDateTime={}]", productId, brandId, applicableDateTime);
        Price result = priceRepository.getApplicablePrice(productId, brandId, applicableDateTime)
                                      .orElseThrow(() -> {
                                          String message= ExceptionMessageBuilder.buildPriceNotFoundException(productId,
                                                                                                              brandId,
                                                                                                              applicableDateTime);
                                          logger.info(message);
                                          return new PriceNotFoundException(message);
                                      });

        logger.info("Product's price found => {}", result);
        return result;
    }
}
