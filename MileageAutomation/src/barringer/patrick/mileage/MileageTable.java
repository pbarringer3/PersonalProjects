package barringer.patrick.mileage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Iterator;

/**
 * Created by Patrick Barringer on 11/17/2015.
 */
public class MileageTable {
    private String[] locations;
    private double[][] distances;
    private final XSSFSheet table;

    public MileageTable(XSSFSheet table){
        this.table = table;
        initializeGlobalVariables();
        populateTable();
    }

    private void initializeGlobalVariables(){
        int size = getTableSize();
        locations = new String[size];
        distances = new double[size][size];
    }

    private int getTableSize(){
        Iterator<Row> rowIterator = table.iterator();
        int rowCounter = 0;

        rowIterator.next(); //skip the header row
        while(rowIterator.hasNext()){
            rowIterator.next();
            rowCounter++;
        }

        return rowCounter;
    }

    private void populateTable(){
        Iterator<Row> rowIterator = table.iterator();
        int rowCounter = 0;
        rowIterator.next(); //skip the header row
        while(rowIterator.hasNext()){
            Row currentRow = rowIterator.next();
            addToLocations(currentRow, rowCounter);
            addToDistances(currentRow, rowCounter);
            rowCounter++;
        }
        System.out.println("There are "+rowCounter+" rows in the mileage table");
    }

    private void addToLocations(Row currentRow, int rowCounter) {
        Cell firstCell = currentRow.getCell(0);
        String cellValue = firstCell.getStringCellValue().trim();
        locations[rowCounter] = cellValue;
        System.out.print("");
    }

    private void addToDistances(Row currentRow, int rowCounter) {
        Iterator<Cell> cellIterator = currentRow.iterator();
        cellIterator.next(); //skip the locations column
        int columnCounter = 0;
        while (cellIterator.hasNext()){
            Cell currentCell = cellIterator.next();
            distances[rowCounter][columnCounter] = currentCell.getNumericCellValue();
            columnCounter++;
            System.out.print("");
        }
    }

    public double getDistance(String startingLocation, String endingLocation){
        int startingIndex = getIndexOfLocation(startingLocation);
        int endingIndex = getIndexOfLocation(endingLocation);

        return distances[startingIndex][endingIndex];
    }

    private int getIndexOfLocation(String location){
        for(int i=0; i<locations.length; i++){
            if(locations[i].equals(location)){
                return i;
            }
        }
        FileHelperFunctions.sendMessage("'" + location + "' was not in the mileage table.");
        throw new RuntimeException("A destination was entered that is not in the table");
    }
}
