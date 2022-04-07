package com.sparta.springbootapiprac.repository;

import com.sparta.springbootapiprac.model.Food;
import com.sparta.springbootapiprac.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long> {
    List<Food> findAllByRestaurant(Restaurant restaurant);

    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName );

}
