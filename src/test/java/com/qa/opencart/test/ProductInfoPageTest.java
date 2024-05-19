package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductInfoPageTest extends BaseTest {

    @BeforeClass
    public void productInfoPageSetUp() {
        acntPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
    }

    @Test
    public void productImageCountTest() {
        searchPage = acntPage.performSearch("MacBook");
        productInfoPage = searchPage.selectProduct("MacBook Pro");
        int actualImagesCount = productInfoPage.getProductImageCount();
        Assert.assertEquals(actualImagesCount, 4);
    }


    @Test
    public void productInfoTest() {
        searchPage = acntPage.performSearch("MacBook");
        productInfoPage = searchPage.selectProduct("MacBook Pro");
        Map<String, String> actualProductInfoMap = productInfoPage.getProductInfo();
        System.out.println(actualProductInfoMap);
        softssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
        softssert.assertEquals(actualProductInfoMap.get("Product Code"), "Product 18");
        softssert.assertEquals(actualProductInfoMap.get("productname"), "MacBook Pro");
        softssert.assertEquals(actualProductInfoMap.get("Productprice"), "$2,000.00");
    }

    @Test
    public void addToCartTest() {
        searchPage = acntPage.performSearch("MacBook");
        productInfoPage = searchPage.selectProduct("MacBook Pro");
        productInfoPage.enterQuantityT(2); 
        String addtoCartMsg = productInfoPage.addProductToCart();
       // Success: You have added MacBook Pro to your shopping cart!

        softssert.assertTrue(addtoCartMsg.contains("Success"));
        softssert.assertTrue(addtoCartMsg.contains("MacBook Pro"));

        softssert.assertEquals(addtoCartMsg,"Success: You have added MacBook Pro to your shopping cart!");
        softssert.assertAll();
    }

}
