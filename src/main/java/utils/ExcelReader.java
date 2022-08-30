//package utils;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ExcelReader {
//
//    private ExcelReader() {
//        throw new IllegalStateException("This is utility class.");
//    }
//
//    public static Map<Integer, List<String>> getTableArray(String filePath, String sheetName) {
////        DataFormatter formatter = new DataFormatter();
////        try (FileInputStream file = new FileInputStream((filePath));
////             Workbook workbook = new XSSFWorkbook(file)) { //get my workbook
////            Sheet worksheet = workbook.getSheet(sheetName);// get my sheet from workbook
////            Row row = worksheet.getRow(0);   //get my Row which start from 0
////
////            int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
////            int ColNum = row.getLastCellNum(); // get last ColNum
////
////            Object[][] data = new Object[RowNum - 1][ColNum]; // pass my  count data in array
////
////            for (int i = 0; i < RowNum - 1; i++) //Loop work for Rows
////            {
////                Row row1 = worksheet.getRow(i + 1);
////                for (int j = 0; j < ColNum; j++) //Loop work for colNum
////                {
////                    if (row1 == null)
////                        data[i][j] = "";
////                    else {
////                        Cell cell = row.getCell(j);
////                        if (cell == null)
////                            data[i][j] = ""; //if it get Null value it pass no data
////                        else {
////                            String value = formatter.formatCellValue(cell);
////                            data[i][j] = value; //This formatter get my all values as string i.e integer, float all type data value
////                        }
////                    }
////                }
////            }
////            return data;
////        } catch (FileNotFoundException e) {
////            throw new RuntimeException("Configuration file was not found at " + filePath);
////        } catch (IOException e) {
////            throw new RuntimeException("An error occurred while reading from file: " + e);
////        }
////
////    }
//
//
//        try (FileInputStream file = new FileInputStream((filePath));
//             Workbook workbook = new XSSFWorkbook(file)) {
//            Sheet sheet = workbook.getSheet(sheetName);
//            Map<Integer, List<String>> data = new HashMap<>();
//            int i = 0;
//            for (Row row : sheet) {
//                data.put(i, new ArrayList<>());
//                for (Cell cell : row) {
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            data.get(i).add(cell.getRichStringCellValue().getString());
//                            break;
//                        case NUMERIC:
//                            if (DateUtil.isCellDateFormatted(cell))
//                                data.get(i).add(cell.getDateCellValue() + "");
//                            else
//                                data.get(i).add(cell.getNumericCellValue() + "");
//                            break;
//                        case FORMULA:
//                            data.get(i).add(cell.getCellFormula() + "");
//                            break;
//                        case BOOLEAN:
//                            data.get(i).add(cell.getBooleanCellValue() + "");
//                            break;
//                        default:
//                            data.get(i).add(" ");
//                    }
//                }
//                i++;
//            }
//            return data;
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException("Configuration file was not found at " + filePath);
//        } catch (IOException e) {
//            throw new RuntimeException("An error occurred while reading from file: " + e);
//        }
//    }
//}
