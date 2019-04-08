package com.test.products.category;

import java.io.Serializable;

public class DesiredProduct implements Serializable {

    private String productId;

    private String title;

    private String nowPrice;

    private String priceLabel;

    private ColorSwatches[] colorSwatches;

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

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(String priceLabel) {
        this.priceLabel = priceLabel;
    }

    public ColorSwatches[] getColorSwatches() {
        return colorSwatches;
    }

    public void setColorSwatches(ColorSwatches[] colorSwatches) {
        this.colorSwatches = colorSwatches;
    }

}
