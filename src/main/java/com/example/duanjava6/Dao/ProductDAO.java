package com.example.duanjava6.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.duanjava6.Entity.Product;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryCategoryId(Integer categoryId);
    List<Product> findByNameContaining(String name);
}
