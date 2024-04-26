package com.qa.opencart.pages;

import com.qa.opencart.Utils.Element_Utility;
import com.qa.opencart.constants.OpenCartConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountPage {

    private WebDriver driver;
    private Element_Utility eutil;


    //1.Constructor
    public AccountPage(WebDriver driver) {
        this.driver=driver;
        eutil = new Element_Utility(driver);
    }

    //2.Private By Locators
    By headers = By.cssSelector("div#content h2");
    By logoutlink = By.linkText("Logout");
    By searchField = By.name("search");
    By searchIcon = By.cssSelector("div#search button");


    //3.Page Action Methods

    public String ActionPageTitle(){
        String title =  eutil.waitForTitleIsAndFetch(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT, OpenCartConstants.ACCNT_PAGE_TITLE_VALUE);
        System.out.println("Account page title is " +title);
        return title;
    }

    public String ActionPageURL(){
        String url=  eutil.waitForURLContainsAndFetch(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,OpenCartConstants.ACCNT_PAGE_URL_FRACTION_VALUE);
        System.out.println("Account page URL is " +url);
        return url;
    }

    public boolean isSearchFiledExist(){
        return  eutil.WaitvisibilityOfElementLocated(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,searchField).isDisplayed();
    }

    public boolean isLogOutFiledExist(){
        return  eutil.WaitvisibilityOfElementLocated(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,logoutlink).isDisplayed();
    }

    //HeaderList Method for AccountPage
    public List<String> AccntPageHeadersList(){
        List<WebElement> list = eutil.WaitvisibilityOfAllElementsLocatedBy(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,headers);
        System.out.println(list.size());
        List<String> newHeaderValueAlList = new ArrayList<>();
        for(WebElement ele : list){
           String headeTextList =ele.getText();
           newHeaderValueAlList.add(headeTextList);
        }
        return newHeaderValueAlList;
    }

    /**
     * This method will search the product on the AccntPage and then land to a new page which is SearchPage i.e search results
     * @param searchKey
     * @return SearcPage
     */
 public SearcPage performSearch(String searchKey){

        if(isSearchFiledExist()){
            eutil.doSendKeys(searchField, searchKey);
            eutil.doClick(searchIcon);
            return new SearcPage(driver);

        }else{
            System.out.println("Product not found");
        }
        return null;

 }

}

