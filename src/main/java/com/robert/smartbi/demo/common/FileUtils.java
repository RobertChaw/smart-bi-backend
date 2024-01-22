package com.robert.smartbi.demo.common;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;

public class FileUtils {
    public static String transformExcel(File excelFile) {
        StringBuilder result = new StringBuilder();

        ExcelReaderBuilder excelReaderBuilder = EasyExcel.read(excelFile);
        ExcelReaderSheetBuilder excelReaderSheetBuilder = excelReaderBuilder.sheet();
        List<Map<Integer, String>> excelData = excelReaderSheetBuilder.doReadSync();

        for (Map<Integer, String> rowData : excelData) {
            for (String cellData : rowData.values()) {
                result.append(cellData).append("\t");
            }
            result.append("\n");
        }
        
        return result.toString();
    }
}
