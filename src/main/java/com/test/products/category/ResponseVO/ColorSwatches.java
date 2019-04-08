package com.test.products.category.ResponseVO;

import java.io.Serializable;

public class ColorSwatches implements Serializable {

    private String color;

    private String basicColor;

    private String skuId;

    public ColorSwatches(){}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBasicColor() {
        return basicColor;
    }

    public void setBasicColor(String basicColor) {
        this.basicColor = basicColor;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
