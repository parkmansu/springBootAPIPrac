package com.sparta.springbootapiprac.repository;

import com.sparta.springbootapiprac.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    List<Restaurant> findAll();

}
