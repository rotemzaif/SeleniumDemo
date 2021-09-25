package utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Utils class as an assistance class which includes methods to read/write data from files
 * */
public class Utils {

    private static FileInputStream fis;
    private static HSSFSheet ExcelWSheet;
    private static HSSFWorkbook ExcelWBook;
    private static HSSFCell Cell;
    private static HSSFRow Row;
    /**
     *
     * this method reads a property from a config.properties file and return its value
     * @param - a String key property name
     * @return - property value
     * */
    public static String readProperty(String key){
        String value = "";
        try {
            fis = new FileInputStream(".\\src\\main\\resources\\data\\configuration.properties");
            Properties prop = new Properties();
            prop.load(fis); // load a properties file
            value = prop.getProperty(key); // get the property value
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getDownLoadDir(){
        String userHomePath = System.getProperty("user.home");
        String downloadPath = userHomePath + "\\Downloads";
        return downloadPath;
    }

    public static boolean isFileExist(String fileName){
        String filePath =  getDownLoadDir() + "\\" + fileName;
        File file = new File(filePath);
        return file.exists();
    }

    public static boolean deleteFile(String fileName){
        String filePath =  getDownLoadDir() + "\\" + fileName;
        File file = new File(filePath);
        return file.delete();
    }

    public static String getFieldValue(String filePath, String sheetName, String fieldName){
        String cellValue = "";
        try {
            int fieldIndex = 0;
            fis = new FileInputStream(filePath);
            // Access the excel file sheet
            ExcelWBook = new HSSFWorkbook(fis);
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
            int startRow = 0;
            int startCol = 0;
            int totRows = ExcelWSheet.getLastRowNum();
            Row = ExcelWSheet.getRow(0);
            int totCols = Row.getLastCellNum();
            for (int i = startRow; i < 1; i++) {
                for (int j = startCol; j < totCols; j++) {
                    if(getCellValue(i,j).equals(fieldName)){
                        fieldIndex = j;
                        break;
                    }
                }
            }
            cellValue = getCellValue(startRow+1,fieldIndex);
        } catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return cellValue;
    }

    public static String getCellValue(int rowNum, int colNUm){
        Cell = ExcelWSheet.getRow(rowNum).getCell(colNUm);
        Cell.setCellType(CellType.STRING);
        CellType cellType = Cell.getCellType();
        if(cellType == CellType.BLANK)
            return "";
        else return Cell.getStringCellValue();
    }
}
