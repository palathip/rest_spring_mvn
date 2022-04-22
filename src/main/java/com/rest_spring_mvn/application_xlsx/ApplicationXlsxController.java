package com.rest_spring_mvn.application_xlsx;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/v1/application-xlsx-header")
public class ApplicationXlsxController {
    @PostMapping("excel")
    public String excelReader(@RequestParam("File") MultipartFile excel) {

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);



            for(int i=0; i<sheet.getPhysicalNumberOfRows();i++) {
                XSSFRow row = sheet.getRow(i);
                for(int j=0;j<row.getPhysicalNumberOfCells();j++) {
                    System.out.print(row.getCell(j) +" ");
                }
                System.out.println("");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "Success";
    }
}
