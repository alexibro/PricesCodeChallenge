package com.kairosds.pricescodechallenge.prices.persistance;

import com.kairosds.pricescodechallenge.prices.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Optional<Price> findFirstByProductIdAndBrandIdAndEndDateGreaterThanEqualAndStartDateLessThanEqualOrderByPriorityDesc(
            long productId, long brandId, LocalDateTime startDate, LocalDateTime endDate);

}
