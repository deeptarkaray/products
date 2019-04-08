package com.test.products.category;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.util.List;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private RestTemplate restTemplate;

    private String productJsonString;

    private String labelType;

    @Before
    public void setup() throws Exception {
        productJsonString = getProductJsonString();
        labelType = "";
    }

    @Test
    public void testForProducts() throws Exception {
        when(restTemplate.getForObject(any(String.class), any())).thenReturn(productJsonString);
        List<DesiredProduct> desiredProductList = productService.getProducts(labelType);
        assertNotNull(desiredProductList);

    }

    @Test
    public void testForProductsReturningNull() throws Exception {
        when(restTemplate.getForObject(any(String.class), any())).thenReturn(null);
        List<DesiredProduct> desiredProductList = productService.getProducts(labelType);
        assertNull(desiredProductList);

    }

    @Test
    public void testForProductsContent() throws Exception {
        when(restTemplate.getForObject(any(String.class), any())).thenReturn(productJsonString);
        List<DesiredProduct> desiredProductList = productService.getProducts(labelType);
        assertTrue(desiredProductList.size() == 2);
    }


    @Test
    public void testForProductsWithSorting() throws Exception {
        when(restTemplate.getForObject(any(String.class), any())).thenReturn(productJsonString);
        List<DesiredProduct> desiredProductList = productService.getProducts(labelType);
        assertTrue(desiredProductList.get(0).getProductId().equals("Product2"));

    }

    private String getProductJsonString() {
        String jsonString = "{\n" +
                "    \"products\": [\n" +
                "        {\n" +
                "            \"productId\": \"Product1\",\n" +
                "            \"type\": \"product\",\n" +
                "            \"title\": \"hush Marble Panel Maxi Dress, Multi\",\n" +
                "            \"price\": {\n" +
                "                \"was\": \"0.00\",\n" +
                "                \"then1\": \"\",\n" +
                "                \"then2\": \"\",\n" +
                "                \"now\": \"50.00\",\n" +
                "                \"uom\": \"\",\n" +
                "                \"currency\": \"GBP\"\n" +
                "            }\n" +

                "        },\n" +
                "        {\n" +
                "            \"productId\": \"Product2\",\n" +
                "            \"type\": \"product\",\n" +
                "            \"title\": \"hush Tasha Vest Dress\",\n" +
                "            \"price\": {\n" +
                "                \"was\": \"0.00\",\n" +
                "                \"then1\": \"\",\n" +
                "                \"then2\": \"\",\n" +
                "                \"now\": {\n" +
                "                    \"from\": \"55.00\",\n" +
                "                    \"to\": \"100.00\"\n" +
                "                },\n" +
                "                \"uom\": \"\",\n" +
                "                \"currency\": \"GBP\"\n" +
                "            }\n" +
                "        }\n" +
                "]\n" +
                "}";

        return jsonString;
    }
}