package org.codegenerator.utils.OfficePoi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codegenerator.entity.TestCase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZ on 2017/7/19.
 */
public class POIReader {

    /**
     * Read the Excel 2010
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    public static List<TestCase> readXlsx(String path , int startNumber) throws IOException {
        System.out.println(path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<TestCase> list = new ArrayList<TestCase>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    TestCase testcase = new TestCase();
                    XSSFCell name = xssfRow.getCell(0);
                    XSSFCell summary = xssfRow.getCell(2);
                    XSSFCell preconditions = xssfRow.getCell(3);
                    XSSFCell actions = xssfRow.getCell(4);
                    XSSFCell expectedresults = xssfRow.getCell(5);
                    testcase.setInternalid((rowNum + startNumber)+"");
                    testcase.setExternalid((rowNum+ startNumber+ startNumber) + "");

                    testcase.setName(String.valueOf(name).replace("\n","<br/>"));
                    //testcase.setName("云平台管理中心"+rowNum);
                    testcase.setSummary(String.valueOf(summary).replace("\n","<br/>"));
                    testcase.setPreconditions(String.valueOf(preconditions).replace("\n","<br/>"));
                    testcase.setExpectedresults(String.valueOf(expectedresults).replace("\n","<br/>"));
                    testcase.setActions(String.valueOf(actions).replace("\n","<br/>"));
                    list.add(testcase);
                }
            }
        }
        return list;
    }
}
