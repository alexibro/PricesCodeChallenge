package com.kairosds.pricescodechallenge.prices.controller;

import com.kairosds.pricescodechallenge.prices.Price;
import com.kairosds.pricescodechallenge.prices.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/price")
public class PriceControllerImpl implements PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/{productId}")
    public ResponseEntity<Price> getPrice(@PathVariable long productId, @RequestParam String date, @RequestParam long brandId) {
        Optional<Price> price = priceService.getPrice(productId, date, brandId);
        return price.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
