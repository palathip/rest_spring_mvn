package com.rest_spring_mvn.file_reader;

import com.rest_spring_mvn.model.reader.SqlReaderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Iterator;


@Service
@Slf4j
@RequiredArgsConstructor
public class ReaderService {

    public static SqlReaderResult reader(MultipartFile file,Integer numberOfSheet) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        if (numberOfSheet == null || numberOfSheet < 0) {
            numberOfSheet = workbook.getNumberOfSheets();
        }
        StringBuilder preCommand ;
        for (int i = 0; i < numberOfSheet; i++) {
            // Getting the Sheet at index i
            Sheet sheet = workbook.getSheetAt(i);
            log.info("=> " + sheet.getSheetName());
            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();
            // 1. You can obtain a rowIterator and columnIterator and iterate over them
            log.info("Iterating over Rows and Columns using Iterator");

            Iterator<Row> rowIterator = sheet.rowIterator();
            int indexValue = 0;
            preCommand = new StringBuilder();
            String command = "INSERT INTO " + sheet.getSheetName() + "(";
            preCommand.append(command);
            while (rowIterator.hasNext()) {
                if (indexValue == 1) preCommand.replace(preCommand.length()-1,preCommand.length(),") VALUES (");
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                StringBuilder postCommand = new StringBuilder(preCommand.toString());
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);

                    if (indexValue == 0 && !cellValue.isEmpty()) preCommand.append(cellValue).append(",");

                    if (indexValue > 0) {
                        try {
                            Double.parseDouble(cellValue);
                            postCommand.append(cellValue).append(",");

                        } catch (NumberFormatException nfe) {
                            postCommand.append("'").append(cellValue).append("'").append(",");
                        }

                    }

//                    log.info(cellValue + "\t");
                }
                indexValue += 1;

                postCommand.replace(postCommand.length()-1,postCommand.length(),");");
                System.out.println(postCommand);
            }
        }
        SqlReaderResult res = new SqlReaderResult();
        res.setStatus("SUCCESS");
        return res;
    }
}
