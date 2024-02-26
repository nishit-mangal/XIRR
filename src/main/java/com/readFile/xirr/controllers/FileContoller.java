package com.readFile.xirr.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.readFile.xirr.constants.FileConstants;
import com.readFile.xirr.constants.ResponseConstants;
import com.readFile.xirr.dto.ResponseData;
import com.readFile.xirr.dto.XirrResponse;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class FileContoller {
    @PostMapping(path="/upload")
    public ResponseEntity<XirrResponse> uploadFile(@RequestBody(required = true) MultipartFile file){
        System.out.println("Request receive in controller");
        ResponseData responseData = new ResponseData();
        XirrResponse xirrResponse = new XirrResponse();

        try {
            if (file==null || file.isEmpty()) {
                throw new Exception("File not Found");
            }

            // Get the filename and create the directory if it doesn't exist
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            File directory = new File(FileConstants.UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            Long sizeOfFile = file.getSize();
            System.out.println(sizeOfFile);
            // Save the file to the server
            Path filePath = Paths.get(FileConstants.UPLOAD_DIR, fileName);
            Files.write(filePath, file.getBytes());

            List<List<String>> xcelData = readExcelFile(filePath);
            Files.deleteIfExists(filePath);
            xirrResponse.setData(xcelData);
            responseData.setResponseCode(ResponseConstants.RESPONSE_CODE_SUCCESS);
            responseData.setResponseMessage(ResponseConstants.RESPONSE_CODE_SUCCESS_GENERIC_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            responseData.setResponseCode(ResponseConstants.RESPONSE_CODE_ERROR);
            responseData.setResponseMessage(e.getMessage());
        } catch (Exception e) {
            responseData.setResponseCode(ResponseConstants.RESPONSE_CODE_ERROR);
            responseData.setResponseMessage(e.getMessage());
        }
        xirrResponse.setResponseData(responseData);
        return new ResponseEntity<XirrResponse>(xirrResponse, HttpStatus.OK);
    }

    private List<List<String>> readExcelFile(Path filePath) throws IOException, InvalidFormatException {
        List<List<String>> cellValues = new ArrayList<>();

        // Load the XLSX file
        Workbook workbook = WorkbookFactory.create(new File(filePath.toString()));

        // Get the first sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Iterate over rows
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            List<String> rowValues = new ArrayList<>();

            // Iterate over cells
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
//                System.out.println(getCellValueAsString(cell));
                rowValues.add(getCellValueAsString(cell));
            }

            cellValues.add(rowValues);
        }

        workbook.close();

        return cellValues;
    }

    private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return Double.toString(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
