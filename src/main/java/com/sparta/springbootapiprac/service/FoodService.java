package com.sparta.springbootapiprac.service;

import com.sparta.springbootapiprac.dto.FoodDto;
import com.sparta.springbootapiprac.model.Food;
import com.sparta.springbootapiprac.model.Restaurant;
import com.sparta.springbootapiprac.repository.FoodRepository;
import com.sparta.springbootapiprac.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;


    // 음식점 ID 및 음식 정보를 입력받아 음식 등록록
    @Transactional
    public void register(List<FoodDto> foodDtos, Long restaurantId ) {

        // 리스트 값은 보통 for-each문을 돌려서 값을 가져온다.
        for (FoodDto foodDto : foodDtos) {

            String foodName = foodDto.getName();
            int foodPrice = foodDto.getPrice();


            // 가격 조건 검사
            foodPriceCheck(foodPrice);


            // 음식점 id를 가지고 온다.
            Optional<Restaurant> findByRestaurantId = restaurantRepository.findById(restaurantId);
            // Optional <> get 하여 값 가져오기.
            // 가져왔을 때 예외처리하기.
            Restaurant restaurant = findByRestaurantId.orElseThrow(
                    () -> new NullPointerException("검색하신 음식점이 없습니다.")
            );

            // 특정 음식점에서 같은 이름의 음식을 등록할 수 없도록 한다.
            restaurantNameCheck(restaurant, foodName);


            // 음식때는 빌드를 사용해봄.
            Food food = Food.builder()
                    .name(foodName)
                    .price(foodPrice)
                    .restaurant(restaurant)
                    .build();

            foodRepository.save(food);
        }

    }

    // 특정 음식점에서 같은 이름의 음식을 등록할 수 없도록 한다.
    private void restaurantNameCheck(Restaurant restaurant, String foodName) {
        Optional<Food> findByFoodName = foodRepository.findFoodByRestaurantAndName(restaurant, foodName);
        //음식을 찾아야 한다
        if (findByFoodName.isPresent()) {
            throw new IllegalStateException("이미 존재하는 음식 입니다.");
        }
    }


    // 가격 조건 검사
    public void foodPriceCheck(int price) {
        // 2. 음식가격(price) - 100 ~ 1,000,000사이 금액, - 단위는 100원 단위 허용.
        if (!(price>=100 && price<=1000000)) {
            throw new IllegalStateException("음식 가격 허용값 에러");
        }

        if (price % 100 > 0) {
            throw new IllegalStateException("음식 가격 단위 에러");
        }

    }

    // 메뉴판 조회(하나의 음식점에 등록된 모든 음식 정보 조회 기능)
    @Transactional
    public List<Food> findRestaurantAllFood(Long restaurantId) {

        // 음식점 id를 가지고 온다.
        Optional<Restaurant> findByRestaurantId = restaurantRepository.findById(restaurantId);
        // Optional <> get 하여 값 가져오기.
        // 가져왔을 때 예외처리하기.
        Restaurant restaurant = findByRestaurantId.orElseThrow(
                () -> new NullPointerException("검색하신 음식점이 없습니다.")
        );

        return foodRepository.findAllByRestaurant(restaurant);

    }

}
