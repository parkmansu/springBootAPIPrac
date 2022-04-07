package com.sparta.springbootapiprac.model;

import lombok.*;


import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Restaurant {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RestaurantId")
    private Long id;
    // PK

    @Column(nullable = false)
    private String name;
    // 음식점 이름

    @Column(nullable = false)
    private int minOrderPrice;
    // 음식점 최소 주문 가격

    @Column(nullable = false)
    private int deliveryFee;
    // 음식점 기본 배달비

    public Restaurant(String name, int minOrderPrice, int deliveryFee) {
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;

    }

}