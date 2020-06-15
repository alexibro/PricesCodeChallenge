package com.kairosds.pricescodechallenge.prices;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@ApiModel(description = "Product price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(
            notes = "Price id",
            example = "1")
    private long priceId;

    @ApiModelProperty(
            notes = "Store group foreign key",
            example = "1")
    private long brandId;

    @ApiModelProperty(
            notes = "Starting date on which the price applies",
            example = "2020-06-14-00.00.00")
    private LocalDateTime startDate;

    @ApiModelProperty(
            notes = "End date on which the price applies",
            example = "2020-12-31-23.59.59")
    private LocalDateTime endDate;

    @ApiModelProperty(
            notes = "Applicable price list id",
            example = "1")
    private long priceList;

    @ApiModelProperty(
            notes = "Product id",
            example = "35455")
    private long productId;

    @ApiModelProperty(
            notes = "Tiebreaker policy. If two rates coincide in a date range, the one with the highest priority " +
                    "(higher numerical value) applies",
            example = "0")
    private int priority;

    @ApiModelProperty(
            notes = "Price",
            example = "35.50")
    private double price;

    @ApiModelProperty(
            notes = "Currency ISO",
            example = "EUR")
    private String curr;

    // It is mandatory to declare this constructor
    public Price() {}

    public Price(long brandId, LocalDateTime startDate, LocalDateTime endDate, long priceList, long productId, int priority, double price, String curr) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

}
