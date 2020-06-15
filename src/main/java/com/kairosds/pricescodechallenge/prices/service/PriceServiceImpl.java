package com.kairosds.pricescodechallenge.prices.service;

import com.kairosds.pricescodechallenge.prices.Price;
import com.kairosds.pricescodechallenge.prices.persistance.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Optional<Price> getPrice(long productId, String date, long brandId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        List<Price> prices = priceRepository.findAll();
        return prices.stream()
                .filter(p -> (p.getProductId() == productId)
                        && (p.getBrandId() == brandId)
                        && (p.getStartDate().compareTo(localDateTime) < 0)
                        && (p.getEndDate().compareTo(localDateTime) > 0))
                .max(Comparator.comparingInt(Price::getPriority));
    }

    // Alternative: Find price using Spring Data methods
    /*public Optional<Price> getPrice(long productId, String date, long brandId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        return priceRepository
                .findFirstByProductIdAndBrandIdAndEndDateGreaterThanEqualAndStartDateLessThanEqualOrderByPriorityDesc(
                        productId, brandId, localDateTime, localDateTime);
    }*/
}
