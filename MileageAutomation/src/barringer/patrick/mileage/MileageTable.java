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
        initializeLocalVariables();
        populateTable();
    }

    private void initializeLocalVariables(){
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
    }

    private void addToLocations(Row currentRow, int rowCounter) {
        locations[rowCounter] = currentRow.getCell(0).getStringCellValue();
    }

    private void addToDistances(Row currentRow, int rowCounter) {
        Iterator<Cell> cellIterator = currentRow.iterator();
        cellIterator.next(); //skip the locations column
        int columnCounter = 0;
        while (cellIterator.hasNext()){
            Cell currentCell = cellIterator.next();
            distances[rowCounter][columnCounter] = currentCell.getNumericCellValue();
            columnCounter++;
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
        throw new RuntimeException("A destination was entered that is not in the table");
    }

    private void printDistances(){
        for (double[] distanceRow : distances) {
            for (double distanceColumn : distanceRow) {
                System.out.print(distanceColumn + "\t");
            }
            System.out.println();
        }
    }
}
