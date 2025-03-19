package com.example.duanjava6.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.duanjava6.Entity.Category;



@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
}
