package com.woc.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class CsvReaderUtilTest {

    @Test
    public void readCsv(){
        String testDataFilePath = "test-data"+ File.separator+"student.csv";
        List<String[]> rows = CsvReaderUtil.readCsv(testDataFilePath, true);
        Assert.assertTrue(!rows.isEmpty(), "Rows in csv should not be empty");
    }
}
