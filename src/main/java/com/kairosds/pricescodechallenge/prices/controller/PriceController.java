package com.kairosds.pricescodechallenge.prices.controller;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

@Api
public interface PriceController {

    @ApiOperation(value = "Get product price")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product price (depends on product id, request date and brand id)"),
            @ApiResponse(code = 404, message = "Product price not found"),
            @ApiResponse(code = 400, message = "Bad request")})
    ResponseEntity getPrice(
            @ApiParam(value = "Product id", required = true, example = "35455") long productId,
            @ApiParam(
                    value = "Request date on which the price of the product depends",
                    required = true,
                    format = "yyyy-MM-dd-HH.mm.ss",
                    example = "2020-06-14-10.00.00") String date,
            @ApiParam(value = "Brand id", required = true, example = "1") long brandId);

}
