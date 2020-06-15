package com.kairosds.pricescodechallenge;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PricesIT {

    @Autowired
    protected MockMvc mockMvc;

    private static final long PRODUCT_ID = 35455;
    private static final long BRAND_ID = 1; // ZARA = 1

    private static final String DATE_1 = "2020-06-14-10.00.00";
    private static final String DATE_2 = "2020-06-14-16.00.00";
    private static final String DATE_3 = "2020-06-14-21.00.00";
    private static final String DATE_4 = "2020-06-15-10.00.00";
    private static final String DATE_5 = "2020-06-16-21.00.00";

    @BeforeEach
    public void initTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void itShouldReturnAPriceWithPriceList1() throws Exception {
        long priceList = itShouldReturnAPrice(DATE_1);
        assertEquals(1, priceList);
    }

    @Test
    public void itShouldReturnAPriceWithPriceList2() throws Exception {
        long priceList = itShouldReturnAPrice(DATE_2);
        assertEquals(2, priceList);
    }

    @Test
    public void itShouldReturnAPriceAlsoWithPriceList1() throws Exception {
        long priceList = itShouldReturnAPrice(DATE_3);
        assertEquals(1, priceList);
    }

    @Test
    public void itShouldReturnAPriceWithPriceList3() throws Exception {
        long priceList = itShouldReturnAPrice(DATE_4);
        assertEquals(3, priceList);
    }

    @Test
    public void itShouldReturnAPriceWithPriceList4() throws Exception {
        long priceList = itShouldReturnAPrice(DATE_5);
        assertEquals(4, priceList);
    }


    @Test
    public void itShouldTryToGetAPriceButReturnsNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price/" + -1)
                .queryParam("date", DATE_1)
                .queryParam("brandId", String.valueOf(BRAND_ID))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void itShouldTryToGetAPriceButReturnsBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price/" + PRODUCT_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private long itShouldReturnAPrice(String date) throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/price/" + PRODUCT_ID)
                .queryParam("date", date)
                .queryParam("brandId", String.valueOf(BRAND_ID))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        DocumentContext result = JsonPath.parse(response.getContentAsString());

        int productId = result.read("productId");
        int brandId = result.read("brandId");
        int priceList = result.read("priceList");

        assertEquals(PRODUCT_ID, productId);
        assertEquals(BRAND_ID, brandId);

        return priceList;
    }
}
