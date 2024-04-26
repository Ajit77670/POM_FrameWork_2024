package com.qa.opencart.Utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {



    private static final String TEST_DATA_SHEET_PATH ="C:\\Users\\Ajith Kumar\\POM_OpenCartFrameWork_2024\\src\\test\\resources\\TestData\\TestData.xlsx";

    private static Workbook book;
    private static Sheet sheet;


    public static Object[][] getTestData(String SheetName){

        System.out.println("reading data from the sheet" +SheetName);

        Object data[][]=null;

        try {
            FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
            try {
                book = WorkbookFactory.create(ip); // WorkbookFactory returns the WorkBook.
                sheet= book.getSheet(SheetName); // getSheet will returns the Sheet.

                data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

                for(int i=0 ; i<sheet.getLastRowNum();i++){

                    for(int j=0 ; j<sheet.getRow(0).getLastCellNum();j++){

                     data[i][j] =sheet.getRow(i+1).getCell(j).toString();  // taken i+1 for row bcuz we are  counting from row 1 as row 0 is header which we dont want to read, and converting the whole statement to String bcuz this is an non-java file i.e we are reading the data from the excel so hence converting into the string.


                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            } catch (FileNotFoundException e) {
                 e.printStackTrace();
        }

        return data;
    }


}
