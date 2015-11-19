package barringer.patrick.mileage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Patrick Barringer on 11/17/2015.
 */
public class MileageCalculator {
    private static final String TRIPS_SHEET_NAME = "REIMBURSEMENT SHEET";
    private static final String TABLE_SHEET_NAME = "MILEAGE TABLE";

    private final XSSFSheet tripsSheet;

    private final MileageTable mileageTable;
    private List<Trip> trips;

    public MileageCalculator(XSSFWorkbook workbook){
        workbook.setForceFormulaRecalculation(true);
        XSSFSheet table = workbook.getSheet(TABLE_SHEET_NAME);
        mileageTable = new MileageTable(table);
        tripsSheet = workbook.getSheet(TRIPS_SHEET_NAME);
        createTrips();
    }

    private void createTrips(){
        DataFormatter df = new DataFormatter();
        trips = new ArrayList<Trip>();
        Iterator<Row> rows = tripsSheet.iterator();
        int rowNumber = 0;
        while(rows.hasNext()) { //skip all header rows
            Row row = rows.next();
            Cell firstCell = row.getCell(0);
            String firstCellContents = df.formatCellValue(firstCell);
            rowNumber++;
            if(firstCellContents != null && firstCellContents.equalsIgnoreCase("date")){
                break;
            }
        }

        while(rows.hasNext()){
            Row currentRow = rows.next();
            if(currentRow.getCell(0).getCellType()!=Cell.CELL_TYPE_BLANK) {
                trips.add(new Trip(df.formatCellValue(currentRow.getCell(2)), rowNumber));
            }
            rowNumber++;
        }
    }

    public void addMileageToWorkbook(){
        for(Trip trip : trips){
            double totalMileage = trip.getTotalDistance(mileageTable);
            double deductionForHomeMileage = trip.getDeductionForHomeMileage(mileageTable);

            if(totalMileage==-1){
                continue;
            }
            Row currentRow = tripsSheet.getRow(trip.getRow());
            CellStyle cellStyle = currentRow.getCell(2).getCellStyle();
            Cell totalMileageCell = currentRow.createCell(9);
            totalMileageCell.setCellStyle(cellStyle);
            Cell deductionForHomeMileageCell = currentRow.createCell(10);
            deductionForHomeMileageCell.setCellStyle(cellStyle);

            totalMileageCell.setCellValue(totalMileage);
            deductionForHomeMileageCell.setCellValue(deductionForHomeMileage);
        }
    }
}
