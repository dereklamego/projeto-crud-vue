package com.derek.springbootdata.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.derek.springbootdata.ProductCategory;
import com.derek.springbootdata.ProductStatus;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    public String name;

    public double price;

    public String code;

    @Enumerated(EnumType.STRING)
    public ProductStatus status;

    @Enumerated(EnumType.STRING)
    public ProductCategory category;

    public Product(){
        
    }

    public Product(String name, double price, String code, ProductStatus status, ProductCategory category) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.status = status;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    
}
