package com.sparta.springbootapiprac.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // PK

    @Column(nullable = false)
    private String name;
    // 음식 이름

    @Column(nullable = false)
    private int price;
    // 음식가격


    //음식점 ID가 필요할 듯?
    @ManyToOne(optional = false) // 음식점(n) : 음식(1) 방식으로 //false로 설정하면 연관된 엔티티가 항상 있어야한다.
    // 연관관계 조회가 가능함 Restaurant에서 RestaurantId 조회가 가능
    @JoinColumn(name = "RestaurantId",nullable = false) //nullable = false 컬럼값이 null 값이 안되게 하기 위해.
    private Restaurant restaurant;



}
