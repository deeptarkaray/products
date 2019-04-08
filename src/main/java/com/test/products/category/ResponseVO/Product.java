package com.test.products.category.ResponseVO;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;

public class Product implements Serializable, Comparable<Product> {

    private String productId;

    private String type;

    private String title;

    private ColorSwatches[] colorSwatches;

    private Price price;

    public Product(){}

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ColorSwatches[] getColorSwatches() {
        return colorSwatches;
    }

    public void setColorSwatches(ColorSwatches[] colorSwatches) {
        this.colorSwatches = colorSwatches;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public int compareTo(Product o2) {
        if (this.getPrice() != null && o2.getPrice() != null) {
            Double product1WasPrice = !StringUtils.isEmpty(this.getPrice().getWas()) ? NumberUtils.parseNumber(this.getPrice().getWas(), Double.class) : 0.0;
            Double product1NowPrice = !StringUtils.isEmpty(this.getPrice().getNow()) ? NumberUtils.parseNumber(this.getPrice().getNow(), Double.class) : 0.0;
            Double product2WasPrice = !StringUtils.isEmpty(o2.getPrice().getWas()) ? NumberUtils.parseNumber(o2.getPrice().getWas(), Double.class) : 0.0;
            Double product2NowPrice = !StringUtils.isEmpty(o2.getPrice().getNow()) ? NumberUtils.parseNumber(o2.getPrice().getNow(), Double.class) : 0.0;
            if ((product1WasPrice - product1NowPrice) > (product2WasPrice - product2NowPrice)) {
                return 1;
            }
            else if ((product1WasPrice - product1NowPrice) < (product2WasPrice - product2NowPrice)) {
                return -1;
            }
            else {
                return 0;
            }
        }
        else {
            if (this.getPrice() != null && o2.getPrice() == null) {
                return 1;
            }
            else if (this.getPrice() == null && o2.getPrice() != null) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
}
