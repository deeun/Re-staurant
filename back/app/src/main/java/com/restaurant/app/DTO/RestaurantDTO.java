package com.restaurant.app.DTO;

import com.restaurant.app.model.Restaurant;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RestaurantDTO {

    private Long restaurantIndex;  //레스토랑 아이디
    private String busId;
//    private String largeCategory;   // 대분류
//    private String midCategory;   // 소분류

    private String restaurantCategory;
    private String restaurantName;
    private Float x;
    private Float y;
    private String description;
    private String fullAddress;
    private String fullRoadAddress;
    private String siCode;
    private String guCode;
    private String dongCode;
    private String tellNumber;
    private String businessHourInfo;
    private String snsUrl;

    private Float avgRating;

    private Long authorCount;
    private List<MenusDTO> menusList;
    private List<OptionsDTO> optionsList;
    private List<ReviewDTO> reviewList;

    private List<VotedKeywordsDTO> keywordList;

    private List<RestaurantLikeDTO> restaurantLikeList;

    public RestaurantDTO(Restaurant restaurant) {
        this.restaurantIndex = restaurant.getRestaurantIndex();
        this.busId = restaurant.getBusId();
        this.restaurantCategory = restaurant.getRestaurantCategory();
        this.restaurantName = restaurant.getRestaurantName();
        this.description = restaurant.getDescription();
        this.restaurantName = restaurant.getRestaurantName();
        this.x = restaurant.getX();
        this.y = restaurant.getY();
        this.fullAddress = restaurant.getFullAddress();
        this.fullRoadAddress = restaurant.getFullRoadAddress();
        this.siCode = restaurant.getSiCode();
        this.dongCode = restaurant.getDongCode();
        this.guCode = restaurant.getGuCode();
        this.tellNumber = restaurant.getTellNumber();
        this.businessHourInfo = restaurant.getBusinessHourInfo();
        this.avgRating = restaurant.getAvgRating();
        this.authorCount = restaurant.getAuthorCount();
        this.snsUrl = restaurant.getSnsUrl();

        this.menusList = restaurant.menusList(restaurant.getMenusList());
        this.optionsList = restaurant.optionsList(restaurant.getOptionsList());
        this.reviewList = restaurant.reviewList(restaurant.getReviewList());
        this.keywordList = restaurant.keywordsList(restaurant.getKeywordsList());
        this.restaurantLikeList = restaurant.restaurantLikeList(restaurant.getRestaurantLikeList());

    }


}
