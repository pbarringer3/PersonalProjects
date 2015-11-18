package barringer.patrick.mileage;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Scanner;

/**
 * Created by Patrick Barringer on 11/17/2015.
 */
public class MileageCalculator {
    private static final String MILEAGE_SHEET_NAME = "Mileage";
    private static final String TABLE_SHEET_NAME = "Table";

    private final MileageTable mileageTable;

    public MileageCalculator(XSSFWorkbook workbook){
        XSSFSheet table = workbook.getSheet(TABLE_SHEET_NAME);
        mileageTable = new MileageTable(table);
    }
}
