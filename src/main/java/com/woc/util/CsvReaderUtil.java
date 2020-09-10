package com.woc.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvReaderUtil {

    public static List<String[]> readCsv(String filePath, boolean skipHeaderRow){
        try (FileReader fileReader = new FileReader(filePath)) {
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(fileReader);

            if(skipHeaderRow){
                csvReaderBuilder.withSkipLines(1);
            }

            CSVReader csvReader = csvReaderBuilder.build();

            List<String[]> rows = csvReader.readAll();

            return rows;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Issue in reading CSV file");
    }
}
