package com.practice.utility;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class TestDataProvider {
    public static final String TESTDATA_SHEET_PATH=System.getProperty("user.dir") + "\\src\\main\\resources\\Data\\TestData.xlsx";
    public Object[][] getDataFromSheet(String sheetName) {

        Workbook book = null;
        try {
            book = WorkbookFactory.create(getTestDataFile());
        } catch (Exception e) {

        }
        Sheet sheet = book.getSheet(sheetName);
        return getTestDataFromExcelFile(sheet);
    }

    private FileInputStream getTestDataFile() throws IOException {
        return new FileInputStream(TESTDATA_SHEET_PATH);
    }

    private Object[][] getTestDataFromExcelFile(Sheet sheet){
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for(int i = 0; i<sheet.getLastRowNum(); i++){
            for(int k= 0; k<sheet.getRow(0).getLastCellNum(); k++){
                data[i][k] = sheet.getRow(i+1).getCell(k).toString();
                System.out.println(data[i][k]);
            }
        }
        return data;

    }

}

