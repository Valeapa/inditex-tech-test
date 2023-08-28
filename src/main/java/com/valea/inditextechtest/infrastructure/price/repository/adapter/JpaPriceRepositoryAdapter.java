package com.valea.inditextechtest.infrastructure.price.repository.adapter;

import com.valea.inditextechtest.domain.price.model.Price;
import com.valea.inditextechtest.domain.price.port.out.PriceRepository;
import com.valea.inditextechtest.infrastructure.price.repository.JpaPriceRepository;
import com.valea.inditextechtest.infrastructure.price.repository.util.PriceEntityMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Db adapter
 */
@Service
public class JpaPriceRepositoryAdapter implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;

    private final PriceEntityMapper priceEntityMapper;


    public JpaPriceRepositoryAdapter(JpaPriceRepository jpaPriceRepository, PriceEntityMapper priceEntityMapper) {
        this.jpaPriceRepository = jpaPriceRepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    @Override
    public Optional<Price> getApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicableDateTime) {
        return jpaPriceRepository.findApplicablePriceOrderedByPriorityDesc(productId, brandId, applicableDateTime)
                                 .map(priceEntityMapper::toPrice);
    }
}
