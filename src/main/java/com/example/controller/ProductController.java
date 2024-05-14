package com.example.controller;

import com.example.entity.Product;
import com.example.model.PostProduct;
import com.example.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/product")
@Api(tags = "Product Management")
@Slf4j

public class ProductController {

    @Autowired
    private ProductService productService;
    @ApiOperation(value = "Get all the products")
    @GetMapping("/allproducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @ApiOperation(value = "Get product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if (product != null) {
             log.info("product information is retrieved successfully");
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            log.error("product information could not be retrieved");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @ApiOperation(value = "create the new product")
    @PostMapping("/postproduct")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid PostProduct postProduct) {
        Product product = new Product();
        product.setProductName(postProduct.getProductName());
        product.setProductPrice(postProduct.getProductPrice());
       Product savedProduct = productService.saveProduct(product);
        log.info("product saved successfully");
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Delete the existing product")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        log.info("product deleted successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Update an existing product")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody PostProduct postProduct) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            existingProduct.setProductName(postProduct.getProductName());
            existingProduct.setProductPrice(postProduct.getProductPrice());
            productService.saveProduct(existingProduct);
            log.info("product changed successfully");
            return new ResponseEntity<>(existingProduct, HttpStatus.OK);
        } else {
            log.error("product could not be modified");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}