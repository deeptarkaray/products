package com.test.products.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/getProducts")
    public ResponseEntity<List<DesiredProduct>> getProducts(@RequestParam(required = false, defaultValue = "") String labelType) {
        List<DesiredProduct> products = productService.getProducts(labelType);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
