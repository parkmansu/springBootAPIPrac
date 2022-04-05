package com.sparta.springbootapiprac.repository;

import com.sparta.springbootapiprac.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> findAll();

}
