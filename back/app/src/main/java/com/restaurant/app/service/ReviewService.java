package com.restaurant.app.service;

import com.restaurant.app.DTO.ReviewDTO;
import com.restaurant.app.model.Restaurant;
import com.restaurant.app.model.Review;
import com.restaurant.app.model.User;
import com.restaurant.app.repository.RestaurantRepository;
import com.restaurant.app.repository.ReviewRepository;
import com.restaurant.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private final ReviewRepository reviewRepository;

    private  final RestaurantRepository restaurantRepository;

    private final UserRepository userRepository;

    @Transactional
    public List<Review> save(User authedUser, ReviewDTO reviewDTO, String busId) {
        Restaurant restaurant = restaurantRepository.findRestaurantByBusId(busId);


        Review review = Review.builder()
                .user(authedUser)
                .restaurant(restaurant)
                .reviewTitle(reviewDTO.getReviewTitle())
                .reviewContent(reviewDTO.getReviewContent())
                .reviewImage(reviewDTO.getReviewImage())
                .revisit(reviewDTO.getRevisit())
                .tag(reviewDTO.getTag())
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        review.setUser(authedUser);

        Review savedReview = reviewRepository.save(review);
        System.out.println("savedReview" + savedReview);

        return reviewRepository.findReviewByRestaurantBusId(busId);
    }

    @Transactional
    public List<Review> findByEmail( User authedUser,String email){
        List<Review> review = reviewRepository.findOptionsByUserEmail(email);
        List<Review> reviews = reviewRepository.findReviewByUser(authedUser);
        return review;
    }

    public  List<Review> findReview (ReviewDTO reviewDTO){
        List<Review> reviewList = reviewRepository.findReviewByReviewTitleContainingIgnoreCaseOrReviewContentContainingIgnoreCase(reviewDTO.getReviewTitle(),reviewDTO.getReviewContent());

        if (reviewList.size() == 0) {
            throw new RuntimeException("리뷰가 존재하지 않습니다.");
        }

        return reviewList;
    }

    public void update(User authedUser , ReviewDTO updateReviewDTO, String busId ) {
        // 기존 review 로드

        Review currReview = reviewRepository.findReviewByReviewIndex(updateReviewDTO.getReviewIndex());
//

        currReview.setReviewContent(updateReviewDTO.getReviewContent());
        currReview.setReviewTitle(updateReviewDTO.getReviewTitle());
        currReview.setReviewImage(updateReviewDTO.getReviewImage());
        currReview.setRevisit(updateReviewDTO.getRevisit());
        currReview.setModifiedDate(updateReviewDTO.getModifiedDate());


        reviewRepository.save(currReview);
    }



    public Long delete(User authedUser, Long reviewIndex) {
        Review currReview = reviewRepository.findReviewByReviewIndex(reviewIndex);


        if (authedUser.getUserIndex() != currReview.getUser().getUserIndex()) {
            throw new RuntimeException("deleteReview denied. invalid userIndex");
        }

        return reviewRepository.deleteByReviewIndex(currReview.getReviewIndex());

    }


}