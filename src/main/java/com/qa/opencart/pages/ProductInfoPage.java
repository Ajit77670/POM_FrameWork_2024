package com.qa.opencart.pages;

import com.qa.opencart.Utils.Element_Utility;
import com.qa.opencart.constants.OpenCartConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ProductInfoPage {

    private WebDriver driver;
    private Element_Utility eutil;

    private  Map<String,String> productInfoMap ;

    //Private By locators
   private By ProductHeader = By.tagName("h1");
   private By ProductImages = By.cssSelector("ul.thumbnails img");
   private By ProductMetaData = By.xpath("(//ul[@class='list-unstyled'])[position()=8]/li");
   private By ProductPriceData = By.xpath("(//ul[@class='list-unstyled'])[position()=9]/li");
   private By ProductQuantity = By.id("input-quantity");
   private By AddToCart = By.id("button-cart");
   private By SucessMsgforAddToCart = By.cssSelector("div.alert.alert-success.alert-dismissible");

    //Constructor
    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        eutil = new Element_Utility(driver);
    }


    public String getProductHeader(){
        String ProductHeaderValue = eutil.doGetText(ProductHeader);
        System.out.println("product header is " +ProductHeaderValue);
          return ProductHeaderValue;
    }


    public int getProductImageCount(){
    int imgCount = eutil.WaitvisibilityOfAllElementsLocatedBy(OpenCartConstants.DEFAULT_LONG_TIME_OUT,ProductImages).size();
    System.out.println("product image count is " +imgCount);
        return imgCount;
    }


    public  Map<String, String> getProductInfo() {
       // productInfoMap = new HashMap<String, String>();  // No order maintain in HashMap
        productInfoMap = new LinkedHashMap<>();            // LinkedHashMap maintains the order
       // productInfoMap = new TreeMap<>();                // Gives sorted result
        productInfoMap.put("productname", getProductHeader());
        getProductmetadata();
        getProductPriceData();
        return productInfoMap;

    }

        //fetching product MetdaData
        private void getProductmetadata(){

            //------This is ProductInfo Data available on WebPage to which we have split using sting method.
            //        Brand: Apple
            //        Product Code: Product 18
            //        Reward Points: 800
            //        Availability: In Stock
            List<WebElement> metalist =eutil.WaitvisibilityOfAllElementsLocatedBy(10,ProductMetaData);
            for(WebElement ele :metalist){
                String metadataList =  ele.getText();
                String split[]=metadataList.split(":");
                String key =split[0].trim();
                String value =split[1].trim();
                productInfoMap.put(key,value);
            }
        }

        //fetching product price
        private void getProductPriceData(){
            //------This is ProductPrice  Data available on WebPage to which we have split using sting method.
            //        $2,000.00
            //        Ex Tax: $2,000.00

            List<WebElement> priceList =eutil.WaitvisibilityOfAllElementsLocatedBy(10,ProductPriceData);

            String price =priceList.get(0).getText();
            String eTax =priceList.get(1).getText();
            String extaxvalue =eTax.split(":")[1].trim();

            productInfoMap.put("productprice",price);
            productInfoMap.put("exTax",extaxvalue);
        }


        //Method for passing the product quantity.
        public void enterQuantityT(int qty){
        System.out.println("Product Quantity is "+qty);
        eutil.doSendKeys(ProductQuantity,String.valueOf(qty)); // String.valueOf() , will convert from int to String.
        }

    //Method for passing the product quantity.
    public String addProductToCart(){
        eutil.doClick(AddToCart);
        String msg = eutil.WaitvisibilityOfElementLocated(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,SucessMsgforAddToCart).getText();
        msg = msg.substring(0,msg.length()-1).replace("\n","").toString(); // There is some special character and new line in DOM with text so we need to use String inbuilt method to remove it.
        System.out.println("Cart Sucess msg "+msg);
        return msg;

    }

        }







