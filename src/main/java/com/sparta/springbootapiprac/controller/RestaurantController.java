package com.sparta.springbootapiprac.controller;

import com.sparta.springbootapiprac.dto.RestaurantDto;
import com.sparta.springbootapiprac.model.Restaurant;
import com.sparta.springbootapiprac.repository.RestaurantRepository;
import com.sparta.springbootapiprac.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    // 음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant register(@RequestBody RestaurantDto restaurantDto) {

        return restaurantService.register(restaurantDto);
    }



    // 음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> inquire() {
        return restaurantRepository.findAll();
    }



}
