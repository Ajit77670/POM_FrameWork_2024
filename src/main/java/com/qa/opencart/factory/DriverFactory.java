package com.qa.opencart.factory;

import com.qa.opencart.exceptions.FrameworkExceptions;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    public WebDriver driver;
    public Properties prop;
    public OptionManager optionManger;

    public static String highlight;  // creating static String variable highlight bcuz the same we can use in the ElemntUtil class. so that we can called the static variable by its class name without creating the object of the class.

    public static ThreadLocal<WebDriver> tlDdirver = new ThreadLocal<WebDriver>();

    /***
     *  This Method is used to initialize the driver on the basis of browser we provide.
     * @param prop
     * @return WebDriver
     */
    public WebDriver initDriver(Properties prop){

         highlight =prop.getProperty("highlight").trim(); // Fetching the properties value of key-"highlight" and storing in the static variable higlight.
        // Putting highlight outside so that it will be applicable to all browser.

        optionManger = new OptionManager(prop); // Creating Object for OptionManager.

        String browserName =prop.getProperty("browser").toLowerCase().trim(); // Fetching prop key-(browser) value from properties file
         // String browserName System.getProperty("browser"); // This we can use if we want to pass the browser through command line.
        System.out.println("Browser name is " +browserName);

            if(browserName.trim().equalsIgnoreCase("chrome")){
              //  driver = new ChromeDriver(optionManger.getChromeOptions()); // Passing chrome optionManager as constructor parameter.
                tlDdirver.set( new ChromeDriver(optionManger.getChromeOptions()));

            } else if (browserName.trim().equalsIgnoreCase("firefox")) {
               // driver = new FirefoxDriver(optionManger.getFirefoxOptions()); // Passing firefox optionManager as constructor parameter.
                tlDdirver.set( new FirefoxDriver(optionManger.getFirefoxOptions()));

            } else if (browserName.trim().equalsIgnoreCase("safari")) {
               // driver = new SafariDriver();
                tlDdirver.set( new SafariDriver());

            } else if (browserName.trim().equalsIgnoreCase("edge")) {
               // driver = new EdgeDriver(optionManger.getEdgeOptions());// Passing edge optionManager as constructor parameter.
                tlDdirver.set( new EdgeDriver(optionManger.getEdgeOptions()));

            }else {
                System.out.println("choose the Compatible browser");
            }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));  // Fetching prop key-(url) value from properties file
        return getDriver();
        }

    /**Thread local Method
     * get local thread copy of the driver.
      */
    public synchronized static WebDriver getDriver(){
        return tlDdirver.get();
    }



    /**
     * This Properties Method is to read the properties file. Properties is a class in Java used to read the propertis files.
     * @return Properties
     */
    public Properties initProp() {

        Properties prop = new Properties();
        FileInputStream ip =null;

        String envName = System.getProperty("env");  // In Java enviroment variables we are reading through System Class getProperty() Method and setting the enviroment variable through setPropert() method.
        System.out.println("Running test Cases on Envirmoment Variable" + envName);

        try {
            if (envName == null) {

                ip = new FileInputStream("C:\\Users\\Ajith Kumar\\POM_OpenCartFrameWork_2024\\src\\test\\resources\\config\\config.properties"); // with the help of FileInputStream  we can read the Properties file.

            } else {

                switch (envName.toLowerCase().trim()) {

                    case "qa":
                        ip = new FileInputStream("C:\\Users\\Ajith Kumar\\POM_OpenCartFrameWork_2024\\src\\test\\resources\\config\\qa.config.properties");
                        break;

                    case "dev":
                        ip = new FileInputStream("C:\\Users\\Ajith Kumar\\POM_OpenCartFrameWork_2024\\src\\test\\resources\\config\\dev.config.properties");
                        break;

                    case "stage":
                        ip = new FileInputStream("C:\\Users\\Ajith Kumar\\POM_OpenCartFrameWork_2024\\src\\test\\resources\\config\\stage.config.properties");
                        break;

                    default:
                        System.out.println("Wrong env is Passed.....No need to run the test Case");
                       throw new FrameworkExceptions ("wrong env is passed.");
                      //  break;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }



    /**Method for Screenshots
     * Take the Screenshots
     */
    public static String getScreenshot(){

       File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE); // Output.File return a File Type
           String path = System.getProperty("user.dir") +"/screenshot" +System.currentTimeMillis() +".png";

           File destination = new File(path);
        try {
            FileUtil.copyFile(src,destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}
