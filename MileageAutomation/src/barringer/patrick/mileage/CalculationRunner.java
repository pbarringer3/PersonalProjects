package barringer.patrick.mileage;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;

/**
 * Created by Patrick Barringer on 11/18/2015.
 */
public class CalculationRunner {
    public static void main(String[] args){
        File chosenFile = FileHelperFunctions.getFile();
        XSSFWorkbook workbook = FileHelperFunctions.getWorkbook(chosenFile);
        MileageCalculator mileageCalculator = new MileageCalculator(workbook);
        mileageCalculator.addMileageToWorkbook();
        FileHelperFunctions.writeFile(workbook, chosenFile);
    }
}
