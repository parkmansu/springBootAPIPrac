package com.sparta.springbootapiprac.service;


import com.sparta.springbootapiprac.dto.RestaurantDto;
import com.sparta.springbootapiprac.model.Restaurant;
import com.sparta.springbootapiprac.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // 1. 음식점 등록
    @Transactional
    public Restaurant register(RestaurantDto restaurantDto) {

        int minOrderPrice = restaurantDto.getMinOrderPrice();
        int deliveryFee = restaurantDto.getDeliveryFee();
        String name = restaurantDto.getName();

        // 조건 검사.
        minOrderPricecheck(minOrderPrice);
        deliveryFeecheck(deliveryFee);


        Restaurant restaurant = new Restaurant(name, minOrderPrice, deliveryFee);

        restaurantRepository.save(restaurant);

        return restaurant;

        }


    public void minOrderPricecheck(int minOrderPrice) {
        // 2. 최소주문가격(minOrderPrice) - 1,000 ~ 100,000사이 금액, - 단위는 100원 단위 허용.
        if (!(minOrderPrice<1000 && minOrderPrice>100000)) {
            throw new IllegalStateException("최수 주문 가격 허용값 에러");
        }

        if (minOrderPrice % 100 != 0) {
            throw new IllegalStateException("최수 주문 가격 단위 에러");
        }

    }

    // 3. 기본배달비(deliveryFee) - 0 ~ 10,000사이 금액, - 단위는 500원 단위 허용.
    public void deliveryFeecheck(int deliveryFee) {

        if (deliveryFee<0 && deliveryFee>10000) {
            throw new IllegalStateException("기본 배달비 금액 허용값 에러");
        }

        if (deliveryFee % 500 != 0) {
            throw new IllegalStateException("기본 배달비 금액 단위 에러");
        }

    }



    // 음식점 조회(등록된 모든 음식점 정보 조회 기능)
    @Transactional
    public List<Restaurant> findAllRestaurantName() {
        return restaurantRepository.findAll();
    }


}
