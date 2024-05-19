package com.qa.opencart.pages;

import com.qa.opencart.Utils.Element_Utility;
import com.qa.opencart.constants.OpenCartConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearcPage {

    private WebDriver driver;
    private Element_Utility eutil;

    // private By Locators
    private By productCount = By.cssSelector("div.row div.row div.caption");

    //Constructor
    public SearcPage(WebDriver driver) {
        this.driver=driver;
        eutil = new Element_Utility(driver);
    }

    /**This method returns the total no. of the Products available on the Page
     *
     * @return Product Count
     */
    public int getSearchProductCount(){
        return eutil.WaitvisibilityOfAllElementsLocatedBy(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,productCount).size();
    }


    /**
     * This method select the product and land to the ProductInfoo Page
     * @param productName ,this parameter is passed inside the global By.linkText locator which is applicable to all the products.
     * @return a new Page ProductInfoPage,this page is the new landing page for which we have selected the product
     */
    public ProductInfoPage selectProduct(String productName){
        By product = By.linkText(productName); // The reason we decalare this locator inside , bcuz this is generic locator for all product on the page, if we not make it dynamic then we need to define By locator for all the products Outside the method with hardcoded value i.e in class which would be too big list.
         eutil.WaitvisibilityOfElementLocated(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,product).click();
         return new ProductInfoPage(driver);
    }
}
