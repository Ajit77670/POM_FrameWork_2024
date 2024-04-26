package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearcPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AccountPageTest extends BaseTest {

    @BeforeClass
    public void accntPageSetup(){
       acntPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
    }


    @Test
    public void AccntPageTitleTest(){
       String title = acntPage.ActionPageTitle();
        Assert.assertEquals(title, OpenCartConstants.ACCNT_PAGE_TITLE_VALUE);
    }

    @Test
    public void AccnTpageURLTest(){
       String url = acntPage.ActionPageURL();
       Assert.assertTrue(url.contains(OpenCartConstants.ACCNT_PAGE_URL_FRACTION_VALUE));
    }

    @Test
    public void AccntSearchFiledExistTest(){
        boolean flag =acntPage.isSearchFiledExist();
        Assert.assertTrue(flag);
    }

    @Test
    public void AccntLogoutFiledExistTest(){
        boolean flag =acntPage.isLogOutFiledExist();
        Assert.assertTrue(flag);
    }

    @Test
    public void AccntHeaderListCountTest(){
       List<String> actHeaderlist = acntPage.AccntPageHeadersList();
       Assert.assertEquals(actHeaderlist.size(),OpenCartConstants.ACCNTPAGE_HEADERS_COUNT);
    }


    @Test
    public void AccntHeaderListValueTest(){
        List<String> actHeaderlist = acntPage.AccntPageHeadersList();
        System.out.println("AccntPage Actual HeaderList" +actHeaderlist);
        System.out.println("AccntPage Expected HeaderList" +OpenCartConstants.EXPECTED_ACCNT_PAGE_HEADER_VALUE);
        Assert.assertEquals(actHeaderlist,OpenCartConstants.EXPECTED_ACCNT_PAGE_HEADER_VALUE);
    }

    @DataProvider
    public Object[][] ProductCountTestData() {
        return new Object[][]{

                {"Macbook"},
                {"imac"},
                {"Apple"},
                {"Samsung"}
        };

    }
    @Test(dataProvider = "ProductCountTestData")
    public void searchProductCountTest(String searchKey){
    searchPage =acntPage.performSearch(searchKey);
      Assert.assertTrue(searchPage.getSearchProductCount()>0);
    }


    @DataProvider
    public Object[][] SelectProductTestData() {
        return new Object[][]{

                {"Macbook","MacBook Pro"},
                {"imac","iMac"},
                {"Apple","Apple Cinema 30\""},
                {"Samsung","Samsung Galaxy Tab 10.1"}
        };

    }

    @Test(dataProvider = "SelectProductTestData")
    public void selectProductTest(String searchKey , String selectProduct){
            searchPage =acntPage.performSearch(searchKey);// First we search the product on acntPage which returns the searchPage

            if(searchPage.getSearchProductCount()>0){ // Now chking condition if the products available on page i.e > 0 then select product
            productInfoPage = searchPage.selectProduct(selectProduct); //selecting the product from the serach list which return the new ProductInfoPage i.e the page for selected product
         String actualHeader = productInfoPage.getProductHeader(); // calling one of the method from productInfoPage
         Assert.assertEquals(actualHeader,selectProduct); // finally verifying with help of Assert class
        }

    }

}
