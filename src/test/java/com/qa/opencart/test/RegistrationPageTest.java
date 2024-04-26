package com.qa.opencart.test;

import com.qa.opencart.Utils.ExcelUtil;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.OpenCartConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;


public class RegistrationPageTest extends BaseTest {

    @BeforeClass
    public void regPageSetUp(){
        registrationPage = loginPage.navigateToRegisterPage();
    }


    public String getrandomEmail(){

        Random random = new Random(); // To generate any random value , always use random class.
       // String email = "automation" +random.nextInt(1000)+ "@gmail.com"; // one way to create the random value,
        String email ="automation"+System.currentTimeMillis() +"@gmail.com"; // we are considering this bcuz , the currntTime will always be unique, the above one if 1000 capacity got generated then there will be chance to create the duplicate value.
        return email;
    }

    @DataProvider
    public Object[][] getRegTestData(){

        Object regData[][]= ExcelUtil.getTestData(OpenCartConstants.REGISTER_SHEET_NAME);
        return  regData;
    };

    @Test(dataProvider ="getRegTestData")
    public void userRegTest(String firstName, String lastName,
                            String telephone, String password,String subscribe
    )
    {
        Assert.assertTrue(registrationPage.registerUser(firstName, lastName,
                getrandomEmail() ,telephone,password,subscribe));
    }
}
