package com.sparta.springbootapiprac.controller;


import com.sparta.springbootapiprac.dto.FoodDto;
import com.sparta.springbootapiprac.model.Food;
import com.sparta.springbootapiprac.repository.FoodRepository;
import com.sparta.springbootapiprac.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
    private final FoodRepository foodRepository;

    // 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void register(@RequestBody List<FoodDto>foodDtos, @PathVariable Long restaurantId) {

        foodService.register(foodDtos, restaurantId);

        // 여러개를 한번에 들어오는 경우 list 타입을 써야된다. List<FoodDto>foodDtos

    }

    //메뉴판 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> inquire(@PathVariable Long restaurantId) {

        return foodService.findRestaurantAllFood(restaurantId);
    }
}