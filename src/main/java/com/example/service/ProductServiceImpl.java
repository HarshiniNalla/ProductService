package com.example.service;

import com.example.entity.Product;

import java.util.List;


public interface ProductServiceImpl {

    public List<Product> getAllProducts();
    public Product getProductById(Integer id);
    public Product saveProduct(Product product);

}
