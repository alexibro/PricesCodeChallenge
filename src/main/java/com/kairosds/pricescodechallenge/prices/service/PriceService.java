package com.kairosds.pricescodechallenge.prices.service;

import com.kairosds.pricescodechallenge.prices.Price;

import java.util.Optional;

public interface PriceService {

    Optional<Price> getPrice(long productId, String date, long brandId);

}
