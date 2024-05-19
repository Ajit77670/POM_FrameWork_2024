package com.qa.opencart.base;

import com.qa.opencart.Utils.ExcelUtil;
import com.qa.opencart.factory.DriverFactory;

import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
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


    @Parameters({"browser","browserversion","testcasename"}) // This parameter annotation read the parameter passed in the TestNG.xml file.
    @BeforeTest
    public void setUp(String browserName, String browserversion , String testcasename){ // Two holding Parameter
        df = new DriverFactory();
        prop= df.initProp(); // The reason we initialize the prop before initDriver ,bcuz the browser we are actually reading from properties file

            if(browserName!=null){  // This logic to get the different version of browser from testng.xml file
                prop.setProperty("browser",browserName); // set the key as browser and passing holding parameter browserName to read diff browser from testNG.xml file.
                prop.setProperty("browserversion",browserversion);  // set the key for browserversion
                prop.setProperty("testcasename", testcasename);  // set the key for testcasename
            }

        driver= df.initDriver(prop); // prop reference has so many properties , we pass the prop reference to initDriver so that it can choose whichever properties it want to use.
        loginPage = new LoginPage(driver);  // Logged into the application.
        softssert = new SoftAssert();
    }




    @AfterTest
    public void tearDown()
    {driver.quit();
    }
}
