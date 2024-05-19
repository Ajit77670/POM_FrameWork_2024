package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.OpenCartConstants;
import com.qa.opencart.pages.AccountPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;


@Epic("Epic Design the LoginPageTest")
@Story("101")
public class LoginPageTest extends BaseTest {

    @Description("This is loginPageTitleTest")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority=1)
    public void loginPageTitleTest(){
       String actualtitle = loginPage.getLoginPageTitle();
       Assert.assertEquals(actualtitle, OpenCartConstants.LOGIN_PAGE_TITLE_VALUE);
    }

    @Description("This is loginPageURLTest ")
    @Severity(SeverityLevel.MINOR)
    @Test(priority=2)
    public void loginPageURLTest(){
       String actualUrl = loginPage.LoginUrl();
       Assert.assertTrue(actualUrl.contains(OpenCartConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
    }

    @Description("This is isForgotLinkExist Method Test ")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority=3)
    public void isForgotLinkExist(){
        Assert.assertTrue(loginPage.isFooterLinkExist());
    }


    @Description("This is login Test which lands to the AccountPage")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority=4)
    public void loginTest(){     
        acntPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim() );
        Assert.assertTrue(acntPage.isLogOutFiledExist()); // Verifying any Field of AccntPage if we loged in and landing to ccnt page or not.
    }





}
