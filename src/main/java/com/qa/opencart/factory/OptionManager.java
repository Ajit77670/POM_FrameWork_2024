package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionManager {

   private Properties prop;
   private ChromeOptions co;
   private FirefoxOptions fo;
   private EdgeOptions eo;

   //Constructor for OptionManger
    OptionManager(Properties prop){
        this.prop=prop;
    }

    // ChromeOptions for Chrome Browser
    public ChromeOptions getChromeOptions(){
        co = new ChromeOptions();

        // for running test on remote side
        if(Boolean.parseBoolean(prop.getProperty("remote"))){
            co.setBrowserVersion(prop.getProperty("browserversion"));
            co.setCapability("browsername", "chrome");
            co.setCapability("enableVNC", true); // for view in Selenoid.
            co.setCapability("name",prop.getProperty("testcasename"));// gives name while running on Grid which test running on which browser.
        }

        if(Boolean.parseBoolean(prop.getProperty("headless").trim())){  // properties files are in key and value format which return String, so in order to make as true or false we parse into boolean.
            System.out.println("=========Running Chrome in HeadLess============");
            co.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito").trim())){  // properties files are in key and value format which return String, so in order to make as true or false we parse into boolean.
            co.addArguments("--incognito");
        }
        return co;
    }

    // FirefoxOptions for Firefox Browser
    public FirefoxOptions getFirefoxOptions(){
        fo = new FirefoxOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless").trim())){  // properties files are in key and value format which return String, so in order to make as true or false we parse into boolean.
            fo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito").trim())){  // properties files are in key and value format which return String, so in order to make as true or false we parse into boolean.
            fo.addArguments("--incognito");
        }
        return fo;
    }

    // EdgeOptions for Edge Browser
    public EdgeOptions getEdgeOptions(){
        eo = new EdgeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless").trim())){  // properties files are in key and value format which return String, so in order to make as true or false we parse into boolean.
            eo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito").trim())){  // properties files are in key and value format which return String, so in order to make as true or false we parse into boolean.
            eo.addArguments("--incognito");
        }
        return eo;
    }


}
