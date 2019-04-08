package com.test.products.category;

import java.util.List;

public interface ProductService {

    List<DesiredProduct> getProducts(String labelType);
}
