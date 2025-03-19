package com.example.duanjava6.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.duanjava6.Entity.Review;

import java.util.List;

@Repository
public interface ReviewDAO extends JpaRepository<Review, Integer> {
    List<Review> findByProductProductId(Integer productId);
    List<Review> findByUserUserId(Integer userId);
}
