package com.test.products.category.ResponseVO;


import com.test.products.category.ResponseVO.Product;

import java.io.Serializable;
import java.util.List;

public class Products implements Serializable {

    private List<Product> products;

    public Products(){}

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
