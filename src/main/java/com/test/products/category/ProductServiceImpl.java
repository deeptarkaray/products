package com.test.products.category;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.products.category.ResponseVO.ColorSwatches;
import com.test.products.category.ResponseVO.Price;
import com.test.products.category.ResponseVO.Product;
import com.test.products.category.ResponseVO.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.product.url}")
    private String url;


    @Override
    public List<DesiredProduct> getProducts(String labelType) {
        List<DesiredProduct> desiredProducts= null;
        try {
            String productString= restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Products listproduct = objectMapper.readValue(productString, new TypeReference<Products>(){});
            desiredProducts = getDesiredProducts(desiredProducts, listproduct, labelType);

        }
        catch(Exception ex){
            System.out.println("ioException" + ex.getMessage());
        }
        return desiredProducts;
    }

    private List<DesiredProduct> getDesiredProducts(List<DesiredProduct> desiredProducts, Products sourceProductList, String labelType) {
        if(sourceProductList != null && !sourceProductList.getProducts().isEmpty()) {
            List<Product> products = sourceProductList.getProducts();
            Collections.sort(products);
            desiredProducts = new ArrayList<>();
            for (Product product : products) {
                DesiredProduct desiredProduct = new DesiredProduct();
                desiredProduct.setProductId(product.getProductId());
                desiredProduct.setTitle(product.getTitle());
                desiredProduct.setColorSwatches(getColorSwatchesFromProduct(product.getColorSwatches()));
                if (product.getPrice() != null) {
                    desiredProduct.setNowPrice(getNowPrice(product.getPrice().getNow()));
                    desiredProduct.setPriceLabel(getPriceLabel(labelType, product.getPrice()));
                }

                desiredProducts.add(desiredProduct);
            }
        }
        return desiredProducts;
    }

    private com.test.products.category.ColorSwatches[] getColorSwatchesFromProduct(ColorSwatches[] colorSwatches) {
        List<com.test.products.category.ColorSwatches> desiredColorSwatchesList = new ArrayList<>();
        if (colorSwatches != null && colorSwatches.length > 0) {
            for(ColorSwatches colorSwatch : colorSwatches) {
                com.test.products.category.ColorSwatches desiredColorSwatches = new com.test.products.category.ColorSwatches();
                if (!StringUtils.isEmpty(colorSwatch.getBasicColor())) {
                    Color color = getColor(colorSwatch.getBasicColor().toUpperCase());
                    if (color != null) {
                        String hexColor = String.format("#%06X", (0xFFFFFF & color.getRGB()));
                        desiredColorSwatches.setRgbColor(hexColor.substring(1));
                    }
                }
                desiredColorSwatches.setColor(colorSwatch.getColor());
                desiredColorSwatches.setSkuId(colorSwatch.getSkuId());
                desiredColorSwatchesList.add(desiredColorSwatches);
            }
        }

        return desiredColorSwatchesList != null ? desiredColorSwatchesList.toArray(new com.test.products.category.ColorSwatches[desiredColorSwatchesList.size()]) : null;
    }

    private String getPriceLabel(String labelType, Price price) {
        String priceLabel = "";
        switch(labelType) {
            case "ShowWasNow" :
                priceLabel = "Was " + formatPrice(price.getWas()) + ", now " + formatPrice(price.getNow());
                break;

            case "ShowWasThenNow" :
                priceLabel = "Was " + formatPrice(price.getWas());
                if (price.getThen2() != null && !("").equals(price.getThen2())) {
                    priceLabel = priceLabel + ",then " + formatPrice(price.getThen2());
                }
                else if (price.getThen1() != null && !("").equals(price.getThen1())) {
                    priceLabel = priceLabel + ",then " + formatPrice(price.getThen1());
                }
                priceLabel = priceLabel + ", now " + formatPrice(price.getNow());
                break;

            default:
                priceLabel = "Was " + formatPrice(price.getWas()) + ", now " + formatPrice(price.getNow());
                break;

        }
        return priceLabel;
    }

    private Color getColor(final String color) {
        try {
            final Field f = Color.class.getField(color);

            return (Color) f.get(null);
        }
        catch(IllegalAccessException | NoSuchFieldException ex) {
            System.out.println("Exception" + ex.getMessage());
            return null;
        }
    }

    private String getNowPrice(String now) {
        return formatPrice(now);
    }

    private String formatPrice(String priceString) {
        String price = "£0.00";
        if (!StringUtils.isEmpty(priceString)) {
            Double amount = NumberUtils.parseNumber(priceString, Double.class);
            if (amount < 10) {
                price = "£" + amount;
            }
            else {
                price = "£" + amount.intValue();
            }
        }
        return price;
    }
}
