package barringer.patrick.mileage;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by Patrick Barringer on 11/18/2015.
 */
public class CalculationRunner {
    public static void main(String[] args){
        String path = FileHelperFunctions.getPath();
        XSSFWorkbook workbook = FileHelperFunctions.getWorkbook(path);
        MileageCalculator mileageCalculator = new MileageCalculator(workbook);
    }
}
