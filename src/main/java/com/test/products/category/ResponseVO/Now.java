package com.test.products.category.ResponseVO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = NowDeserializer.class)
public class Now {

    private String value;


    public Now(String nowValue){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
