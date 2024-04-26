package com.qa.opencart.pages;

import com.qa.opencart.Utils.Element_Utility;
import com.qa.opencart.constants.OpenCartConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private WebDriver driver;
    private Element_Utility eutil;

   public  RegistrationPage(WebDriver driver){
        this.driver=driver;
        eutil = new Element_Utility(driver);
    }

    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmpassword = By.id("input-confirm");

    private By agreeCheckBox = By.name("agree");
    private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

    private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
    private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");

    private By registerSuccessMesg = By.cssSelector("div#content h1");

    private By logoutLink = By.linkText("Logout");
    private By registerLink = By.linkText("Register");


    public boolean registerUser(String firstName , String lastName, String email, String telephone, String password,String Subscribe){

        eutil.WaitvisibilityOfElementLocated( OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,this.firstName).sendKeys(firstName);
        eutil.doSendKeys(this.lastName,lastName);
        eutil.doSendKeys(this.email,email);
        eutil.doSendKeys(this.telephone,telephone);
        eutil.doSendKeys(this.password,password);
        eutil.doSendKeys(this.confirmpassword,password);

        if(Subscribe.equalsIgnoreCase("yes")){ // chking conditions for the  user if subscribe or not
            eutil.doClick(subscribeYes);
        }else{
            eutil.doClick(subscribeNo);
        }

        eutil.doClick(agreeCheckBox); // then cliking on agreechkbox
        eutil.doClick(continueButton); // then continue

       String succsmsg = eutil.WaitvisibilityOfElementLocated(OpenCartConstants.DEFAULT_MEDIUM_TIME_OUT,registerSuccessMesg).getText();
        // this is the succsmsg from the new page after creation of the accnt. so to verify the accnt craeted suceefully

        System.out.println("user register sucess msg" +registerSuccessMesg);

        if(succsmsg.contains(OpenCartConstants.USER_REGISTER_SUCCESS_MSG)){ // validating any new page account created locator and return the value to the method so that it will be easy for validating to the Test Classes.

            eutil.doClick(logoutLink);
            eutil.doClick(registerLink);

            return true; // before returning true , click on logout so that another user can be created.
        }
            return false;

    }




}
