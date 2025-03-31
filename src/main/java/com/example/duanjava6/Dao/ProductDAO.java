package com.example.duanjava6.Dao;

import com.example.duanjava6.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryCategoryId(Integer categoryId);
    Page<Product> findByNameContaining(String name, Pageable pageable);
    Page<Product> findByCategoryCategoryId(Integer categoryId, Pageable pageable);
}
