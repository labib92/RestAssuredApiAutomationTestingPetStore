package api.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {

    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private XSSFCellStyle style;
    private String path;

    public XLUtility(String path){
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fileInputStream.close();
        return  rowCount;
    }

    public int getCellCount(String sheetName, int rowNumber) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNumber);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fileInputStream.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNumber, int column) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNumber);
        cell = row.getCell(column);

        DataFormatter formatter = new DataFormatter();
        String data;

        try {
            data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String
        }catch (Exception e){
            data ="";
        }
        workbook.close();
        fileInputStream.close();

        return  data;
    }

    public void setCellData(String sheetName, int rowNumber, int column, String data) throws IOException {
        File xlFile = new File(path);

        if(!xlFile.exists()){ //if file not exists then create new file
            workbook = new XSSFWorkbook();
            fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);
        }

        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);

        if(workbook.getSheetIndex(sheetName) == - 1){ //If sheet not exists then create new sheet
            workbook.createSheet(sheetName);
        }
        sheet = workbook.getSheet(sheetName);

        if(sheet.getRow(rowNumber) == null){ //if row don't exist then create new row
            sheet.createRow(rowNumber);
        }
        row = sheet.getRow(rowNumber);
        cell = row.createCell(column);
        cell.setCellValue(data);
        fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);
        workbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    public void fillGreenColor(String sheetName, int rowNumber, int column) throws IOException {

        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNumber);
        cell = row.getCell(column);

        style = workbook.createCellStyle();

        style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fileOutputStream);
        workbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    public void fillRedColor(String sheetName, int rowNumber, int column) throws IOException {

        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNumber);
        cell = row.getCell(column);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fileOutputStream);
        workbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
