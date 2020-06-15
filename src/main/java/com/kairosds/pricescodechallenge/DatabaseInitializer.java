package com.kairosds.pricescodechallenge;

import com.kairosds.pricescodechallenge.prices.Price;
import com.kairosds.pricescodechallenge.prices.persistance.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.Month;

@Component
public class DatabaseInitializer {

    @Autowired
    private PriceRepository pricesRepository;

    private static final long PRODUCT_ID = 35455;
    private static final long BRAND_ID = 1;

    @PostConstruct
    public void init() {

        LocalDateTime startDate1 =  LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0);
        LocalDateTime endDate1 =    LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59);
        Price price1 = new Price(BRAND_ID, startDate1, endDate1, 1, PRODUCT_ID, 0, 35.50, "EUR");

        LocalDateTime startDate2 =  LocalDateTime.of(2020, Month.JUNE, 14, 15, 0, 0);
        LocalDateTime endDate2 =    LocalDateTime.of(2020, Month.JUNE, 14, 18, 30, 0);
        Price price2 = new Price(BRAND_ID, startDate2, endDate2, 2, PRODUCT_ID, 1, 25.45, "EUR");

        LocalDateTime startDate3 =  LocalDateTime.of(2020, Month.JUNE, 15, 0, 0, 0);
        LocalDateTime endDate3 =    LocalDateTime.of(2020, Month.JUNE, 15, 11, 0, 0);
        Price price3 = new Price(BRAND_ID, startDate3, endDate3, 3, PRODUCT_ID, 1, 30.50, "EUR");

        LocalDateTime startDate4 =  LocalDateTime.of(2020, Month.JUNE, 15, 16, 0, 0);
        LocalDateTime endDate4 =    LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59);
        Price price4 = new Price(BRAND_ID, startDate4, endDate4, 4, PRODUCT_ID, 1, 38.95, "EUR");

        pricesRepository.save(price1);
        pricesRepository.save(price2);
        pricesRepository.save(price3);
        pricesRepository.save(price4);
    }
}
