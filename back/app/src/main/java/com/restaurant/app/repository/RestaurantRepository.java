package com.restaurant.app.repository;

import com.restaurant.app.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findRestaurantByBusId(String busId);
    List<Restaurant> findRestaurantByRestaurantNameContainingIgnoreCase(String restaurantName);

    Restaurant findRestaurantByRestaurantIndex(Long restaurantIndex);

    List<Restaurant> findRestaurantBySiCode(String siCode);


    List<Restaurant> findRestaurantBySiCodeAndGuCode(String siCode, String guCode);
    List<Restaurant> findRestaurantBySiCodeAndGuCodeAndDongCode(String siCode, String guCode, String dongCode);


    List<Restaurant> findRestaurantByRestaurantCategoryContainingIgnoreCase(String restaurantCategory);
    List<Restaurant> findRestaurantByRestaurantCategoryContainingIgnoreCaseOrRestaurantNameContainingIgnoreCase(String restaurantCategory, String  restaurantName);

    List<Restaurant> findAll();



}
