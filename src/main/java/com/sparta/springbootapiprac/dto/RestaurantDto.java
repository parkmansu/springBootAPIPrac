package com.sparta.springbootapiprac.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantDto {

    private String name;
    private int minOrderPrice;
    private int deliveryFee;

}
