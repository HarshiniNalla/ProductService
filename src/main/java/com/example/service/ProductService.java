package com.example.service;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceImpl {


    @Autowired
    private ProductRepository productRepo;
     public List<Product> getAllProducts()
     {
         return productRepo.findAll();
     }
     public Product getProductById(Integer id)
     {
         return productRepo.findById(id).orElse(null);
     }
     public Product saveProduct(Product product)
     {
         return productRepo.save(product);

     }
     public void deleteProduct(Integer  id)
     {
         productRepo.deleteById(id);
     }
}
