package com.qa.opencart.pages;

import com.qa.opencart.Utils.Element_Utility;
import com.qa.opencart.constants.OpenCartConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage {

    private WebDriver driver;
    private Element_Utility eutil;


    //1.Page constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
        eutil = new Element_Utility(driver);
    }

    //2.Private Locators
    private By emailadd = By.id("input-email");
    private By pwd = By.id("input-password");
    private By loginBtn = By.xpath("//input[@value='Login']");
    private By forgetnlink = By.linkText("Forgotten Password");
    private By logoImage = By.xpath("//img[@title='naveenopencart']");
    private By footersList = By.cssSelector("footer div ul li");
    private By topnavList = By.cssSelector("ul.nav.navbar-nav>li");
    private By registerLink = By.linkText("Register");


    //3.Page Actions/Methods

    // Login Page Tile Method
    public String getLoginPageTitle(){
        String title =eutil.waitForTitleContainsAndFetch(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,OpenCartConstants.LOGIN_PAGE_TITLE_VALUE);
        System.out.println("The loinPage title is" +title);
        return title;
    }

    // Current URL page method
    public String LoginUrl(){

        String currUrl = driver.getCurrentUrl();
        eutil.waitForURLContains(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,OpenCartConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
        System.out.println("Login Page url is " +currUrl);
        return currUrl;
    }

    // FooterLink Exist method
    public boolean isFooterLinkExist(){
        return  eutil.WaitvisibilityOfElementLocated(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,forgetnlink).isDisplayed();
    }

    // Footer List Method
    public void footerListgetText(){
        List<WebElement> list =   eutil.WaitvisibilityOfAllElementsLocatedBy(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,footersList);
        int fotterListCount=list.size();
        System.out.println("The footer list count is " +fotterListCount);
        for(WebElement ele : list){
            String text =ele.getText();
            System.out.println(text);
        }
    }

    //Top NavList Method
    public void topNavListgetText(){
        List<WebElement> list =   eutil.WaitvisibilityOfAllElementsLocatedBy(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,topnavList);
        int topNavListCount=list.size();
        System.out.println("The footer list count is " +topNavListCount);
        for(WebElement ele : list){
            String text =ele.getText();
            System.out.println(text);
        }
    }


    //Login Method
    public AccountPage doLogin(String usrname, String pwdValue){
        System.out.println("App credentials are :" +usrname +":" +pwdValue);
        eutil.WaitvisibilityOfElementLocated(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,emailadd).sendKeys(usrname);
        eutil.doSendKeys(pwd,pwdValue);
        eutil.doClick(loginBtn);
        return new AccountPage(driver);
    }

    public RegistrationPage navigateToRegisterPage(){
        eutil.doClick(registerLink);
        return new RegistrationPage(driver);
    }
}
