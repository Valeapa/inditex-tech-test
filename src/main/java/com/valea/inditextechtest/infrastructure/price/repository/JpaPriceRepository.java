package com.valea.inditextechtest.infrastructure.price.repository;

import com.valea.inditextechtest.infrastructure.price.entity.PriceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


/**
 * Price entity repository.
 */
@Repository
public interface JpaPriceRepository extends PagingAndSortingRepository<PriceEntity, Long> {

    /**
     * Searches a product with the given parameters and orders them to return the highest priority one.
     * @param productId product identifier
     * @param brandId brand identifier
     * @param applicableDateTime price applicable date-time
     * @return highest priority product price or empty Optional in case there isn't a register that meets the
     *         query parameters
     */
    @Query("from PriceEntity where productId = :productId and brandId = :brandId and startDate <= :applicableDateTime and endDate >= :applicableDateTime order by priority desc limit 1")
    Optional<PriceEntity> findApplicablePriceOrderedByPriorityDesc(Integer productId,
                                                                   Integer brandId,
                                                                   LocalDateTime applicableDateTime);
}
