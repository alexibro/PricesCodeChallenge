package com.kairosds.pricescodechallenge;

import com.kairosds.pricescodechallenge.prices.Price;
import com.kairosds.pricescodechallenge.prices.persistance.PriceRepository;
import com.kairosds.pricescodechallenge.prices.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceServiceTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PriceRepository priceRepository;

    @Autowired
    private PriceService priceService;

    private static final long PRODUCT_ID = 35455;
    private static final long BRAND_ID = 1;

    private static final String DATE_1 = "2020-06-14-10.00.00";
    private static final String DATE_2 = "2020-06-14-16.00.00";

    @BeforeEach
    public void initialize() {
        LocalDateTime startDate1 =  LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0);
        LocalDateTime endDate1 =    LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59);
        Price price1 = new Price(BRAND_ID, startDate1, endDate1, 1, PRODUCT_ID, 0, 35.50, "EUR");

        LocalDateTime startDate2 =  LocalDateTime.of(2020, Month.JUNE, 14, 15, 0, 0);
        LocalDateTime endDate2 =    LocalDateTime.of(2020, Month.JUNE, 14, 18, 30, 0);
        Price price2 = new Price(BRAND_ID, startDate2, endDate2, 2, PRODUCT_ID, 1, 25.45, "EUR");

        List<Price> prices = new ArrayList<>();
        prices.add(price1);
        prices.add(price2);

        given(priceRepository.findAll()).willReturn(prices);
    }

    @Test
    public void shouldGetPriceWithPriceList1() {
        Optional<Price> price = priceService.getPrice(PRODUCT_ID, DATE_1, BRAND_ID);

        assertTrue(price.isPresent());
        assertEquals(1, price.get().getPriceList());
    }

    @Test
    public void shouldGetPriceWithPriceList2() {
        Optional<Price> price = priceService.getPrice(PRODUCT_ID, DATE_2, BRAND_ID);

        assertTrue(price.isPresent());
        assertEquals(2, price.get().getPriceList());
    }

    @Test
    public void shouldNotFindPrice() {
        Optional<Price> price = priceService.getPrice(-1, DATE_1, BRAND_ID);

        assertFalse(price.isPresent());
    }

}
