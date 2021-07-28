package com.derek.springbootdata.repository;

import java.util.List;

import com.derek.springbootdata.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long>  {
    
    List<Product> findByName(String name);

}
