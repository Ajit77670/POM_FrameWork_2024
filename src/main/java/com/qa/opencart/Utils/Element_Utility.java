package com.qa.opencart.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.qa.opencart.factory.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.*;

import java.util.*;
public class Element_Utility {

    private WebDriver driver;  // instance for WebDriver
    private JavaScriptUtil jUtil;


    //Constructor for Elemnt_Util
    public Element_Utility(WebDriver driver) {
        this.driver = driver;
        jUtil = new JavaScriptUtil(driver);
    }

    //-----------------WebElement Functions()-------------------------

    //Function() to find the WebElement
    public WebElement getElement(By locator) {
       WebElement element = driver.findElement(locator);
       if(Boolean.parseBoolean(DriverFactory.highlight)){
           jUtil.flash(element);
       }
       return element;
    }

    //Function to check if the  particular WebElement present in the WebPage or not.
    public boolean doISdisplayed(By locator) {
        return getElement(locator).isDisplayed();
    }

    //Function for fetching the Text on webPage
    public String doGetText(By locator) {
        return getElement(locator).getText();

    }

    // Function() to click on WebElement
    public void doClick(By locator) {
        getElement(locator).click();
    }

    //Function() for sendkeys, passed the getelement method into this.
    public void doSendKeys(By locator, String value) {
        WebElement element =getElement(locator);
        element.clear();
        element.sendKeys(value);
    }


    //Function to get the Attribue associated with locator
    public String getElementAttribute(By locator, String attribute) {
        return getElement(locator).getAttribute(attribute);
    }
    //---------------Action Class Functions---------------------------------

    public void ActionSendKeys(By locator, String value) {
        Actions act = new Actions(driver);
        act.sendKeys(getElement(locator), value).build().perform();

    }

    public void doActionClick(By locator) {
        Actions act = new Actions(driver);
        act.click(getElement(locator)).build().perform();
    }


    //--------------List<WebElements> Functions -----------------------------

    // Function for finding the Elements
    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }


    // Function for Total Element Count.
    public int getTotalElementsCount(By locator) {
        return getElements(locator).size();
    }


    // Function for get the Attribute associated with locator : applicable for findElemets i.e List<WebElement>
    public void getElementAttributes(By locator, String attribue) {

        List<WebElement> list = getElements(locator);
        for (WebElement ele : list) {

            String attValue = ele.getAttribute(attribue);
            System.out.println(attValue);
        }
    }


    //Function() to get the Elements Text List
    public List<String> getElemntsTextList(By locator) {

        List<String> al = new ArrayList<>();
        List<WebElement> list = getElements(locator);
        for (WebElement ele : list) {
            String text = ele.getText();
            al.add(text);  // adding the text to the new ArrayList
        }
        return al;   // We return new Array so that we can do some validation instead just printing on console.
    }


    //**************************Select based drop down utils************************


    //Function to select element in DropDown by Index
    public void doSelectDropdown_ByIndex(By locator, int index) {

        Select sc = new Select(getElement(locator));
        sc.selectByIndex(0);
    }


    //Function to select element in DropDown by Value
    public void doSelectDropdown_Byvalue(By locator, String value) {

        Select sc = new Select(getElement(locator));
        sc.selectByValue(value);

    }

    // Function to select Dropdown by Visible Text
    public void doSelectDropdown_ByVisibleText(By locator, String value) {

        Select sc = new Select(getElement(locator));
        sc.selectByVisibleText(value);
    }

//--------------Function/Method Wrapping over the getOption -------------------------

    // Function which return the getOption itself.
    public List<WebElement> doSelect_By_Optons(By locator) {
        Select sc = new Select(getElement(locator));
        return sc.getOptions();
    }


    // Function which returns the size of dropdown
    public int getDropdownSize(By locator) {
        return doSelect_By_Optons(locator).size();
    }


    // Function which will return the GetTextOtion
    public List<String> getDropdownText(By locator) {

        List<WebElement> list = doSelect_By_Optons(locator);

        List<String> newlist = new ArrayList<>();
        for (WebElement ele : list) {
            String text = ele.getText();
            newlist.add(text);
        }
        return newlist;
    }


    // Function to select the dropdown by using getOption

    public void selectDropDown_getOption_by_value(By locator, String value) {

        List<WebElement> list = doSelect_By_Optons(locator);
        for (WebElement ele : list) {

            String text = ele.getText();
            if (text.equals(value)) {

                ele.click();
                break;
            }
        }

    }

    //--------------------Generic Methods for WebDriverWait w.r.t Alerts-------------------------------

    public Alert WaitForAlertPresence(int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.alertIsPresent());
    }


    public String getAlertText(int timeOut) {
        return WaitForAlertPresence(timeOut).getText();
    }

    public void getAlertAccept(int timeOut) {
        WaitForAlertPresence(timeOut).accept();
    }

    public void getAlertDismiss(int timeOut) {
        WaitForAlertPresence(timeOut).dismiss();
    }


//----------------------Generic Methods WebDriverWait w.r.t Frames-----------------------
//Return WebDriver

    // switch by nameORid
    public void WaitframeToBeAvailableAndSwitchToIt_Name_or_ID(int duration, String nameORid) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameORid));
    }

    // switchTo by frame
    public void WaitframeToBeAvailableAndSwitchToIt_Name_or_ID(int duration, WebElement framelement) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelement));
    }

    // switchTo by Index
    public void WaitframeToBeAvailableAndSwitchToIt_Name_or_ID(int duration, int index) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
    }

    // switchTo by Locator
    public void WaitframeToBeAvailableAndSwitchToIt_Name_or_ID(int duration, By locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }


//-----------------Generic Method of WebDriverWait w.r.t PageTitle-------------------
//Return : boolean

    public boolean WaitPageTitle(String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.titleIs(title));
    }


    public String WaitPageTitleContainsAndFetch(String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains(title));
        return driver.getTitle();
    }

    public String WaitPageTitleAndFetch(String titleValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs(titleValue));
        return driver.getTitle();
    }

//------------------------WebDriverWait w.r.t Element---------------------------
//Return :WebElement

    /**
     * An expectation for checking that an element is present on the DOM of a page.
     * This does not necessarily mean that the element is visible.
     *
     * @param durationTime
     * @param locator
     * @return WebElemnt
     */
    public WebElement WaitpresenceOfElementLocated(int durationTime, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationTime));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    /**
     * An expectation for checking that an element is present on the DOM of a page and visible.
     * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
     *
     * @param durationTime
     * @param locator
     * @return
     */
    public WebElement WaitvisibilityOfElementLocated(int durationTime, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    //--------------------Generic methods of WEbDriverWait w.r.t WebElements-----------------------------
//Return : List<WebElement>

    public List<WebElement> WaitpresenceOfAllElementsLocatedBy(int duration, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> WaitvisibilityOfAllElementsLocatedBy(int duration, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }


//-----------------------Generic Method for WebDriverWait w.r.t click---------------------------
// Return : WebElement

    public void WaitelementToBeClickable(int duration, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click(); 
    }


    public void ClicWhenReady(int duration, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void WaitdoClickwithAction(int duration, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Actions act = new Actions(driver);
        act.click(ele).build().perform();
    }

    //---------------------WebDriverWait w.r.t. Title---------------------------------
    public String waitForTitleContainsAndFetch(int timeOut, String titleFractionValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.titleContains(titleFractionValue));
        return driver.getTitle();
    }

    public String waitForTitleIsAndFetch(int timeOut, String titleValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.titleIs(titleValue));
        return driver.getTitle();
    }

    // ----------------------WebDriverWait w.r.t URl-----------------------------
    public String waitForURLContainsAndFetch(int timeOut, String urlFractionValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.urlContains(urlFractionValue));
        return driver.getCurrentUrl();
    }

    public String waitForURLIsAndFetch(int timeOut, String urlValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.urlToBe(urlValue));
        return driver.getCurrentUrl();
    }

    public boolean waitForURLContains(int timeOut, String urlFractionValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.urlContains(urlFractionValue));

    }

}
