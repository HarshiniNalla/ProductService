package com.example.model;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostProduct {
    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must be less than or equal to 255 characters")
        private String productName;

    @Min(value=1,message="the price should be greater than 2")
      private Integer productPrice;

    public int getProductPrice() {

        return productPrice;
    }



    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName()
    {
        return productName;
    }

    public PostProduct(String productName, int productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public void setProductName(String productName) {

        this.productName = productName;
    }
}
