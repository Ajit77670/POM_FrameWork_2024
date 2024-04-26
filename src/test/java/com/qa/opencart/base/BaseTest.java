package com.qa.opencart.base;

import com.qa.opencart.Utils.ExcelUtil;
import com.qa.opencart.factory.DriverFactory;

import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {

    DriverFactory df;
    WebDriver driver;
    protected Properties prop;
    protected LoginPage loginPage ;
    protected AccountPage acntPage;
    protected SearcPage searchPage;
    protected ProductInfoPage productInfoPage;
    protected SoftAssert softssert;
    protected RegistrationPage registrationPage;


    @BeforeTest
    public void setUp(){
        df = new DriverFactory();
        prop= df.initProp(); // The reason we initialize the prop before initDriver ,bcuz the browser we are actually reading from properties file
        driver= df.initDriver(prop); // prop refrence have so many properties , we pass the prop refrence to initDriver so that it can choose whichever propertis it want to use.
        loginPage = new LoginPage(driver);  // Logged into the application.
        softssert = new SoftAssert();
    }




    @AfterTest
    public void tearDown()
    {driver.quit();
    }
}
