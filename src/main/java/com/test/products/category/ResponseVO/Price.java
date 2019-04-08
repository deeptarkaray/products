package com.test.products.category.ResponseVO;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

public class Price implements Serializable {

    private String was;

    private String then1;

    private String then2;

    @JsonDeserialize(using = NowDeserializer.class)
    private String now;

    private String uom;

    private String currency;

    public Price(){}

    public String getWas() {
        return was;
    }

    public void setWas(String was) {
        this.was = was;
    }

    public String getThen1() {
        return then1;
    }

    public void setThen1(String then1) {
        this.then1 = then1;
    }

    public String getThen2() {
        return then2;
    }

    public void setThen2(String then2) {
        this.then2 = then2;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
