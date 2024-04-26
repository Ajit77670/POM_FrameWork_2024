package com.qa.opencart.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpenCartConstants {

    public static final int DEFAULT_MEDIUM_TIME_OUT= 10;
    public static final int DEFAULT_SHORT_TIME_OUT= 5;
    public static final int DEFAULT_LONG_TIME_OUT= 20;

    public static final String LOGIN_PAGE_TITLE_VALUE ="Account Login";
    public static final String LOGIN_PAGE_URL_FRACTION_VALUE ="route=account/login";

    public static final String ACCNT_PAGE_TITLE_VALUE ="My Account";
    public static final String ACCNT_PAGE_URL_FRACTION_VALUE ="route=account/account";
    public static final int ACCNTPAGE_HEADERS_COUNT = 4;

    //Collecting the Value of Headers in ArrayList and then passed the same using Opencart constant value in Test
    public static final List<String> EXPECTED_ACCNT_PAGE_HEADER_VALUE= Arrays.asList("My Account" ,"My Orders" ,"My Affiliate Account","Newsletter");

    public static final  String USER_REGISTER_SUCCESS_MSG = "Your Account Has Been Created";

    //**************EXCEL SHEETS NAME **********************************************
    public static final String REGISTER_SHEET_NAME = "register";
}

/* Three types of Data we generally see in Selenium:

    1> Constant Data which we write in Constant.java file using : public final static
    2> Test Data , which we can provide through Excel or Data Provider or DataBase
    3> enviroment Data , like username , password , ip-address these data will maintain in properties file.

 */
