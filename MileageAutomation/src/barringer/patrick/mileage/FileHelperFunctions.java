package barringer.patrick.mileage;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Patrick Barringer on 11/17/2015.
 */
public class FileHelperFunctions {

    public static String getPath(){
        Scanner kbReader = new Scanner(System.in);
        String path = "";

        while(path.equals("")){
            System.out.print("What is the name of your mileage file? ");
            path = "data/"+kbReader.next();
            if(!FileHelperFunctions.fileExists(path)){
                path = "";
                System.out.println("That is not a valid file in the data directory");
            }
        }
        return path;
    }

    public static XSSFWorkbook getWorkbook(String relativePath){
        FileInputStream fileStream = getFileInputStreamFromFile(new File(relativePath));
        return makeWorkbookFromFileStream(fileStream);
    }

    public static XSSFWorkbook getWorkbook(File file){
        FileInputStream fileStream = getFileInputStreamFromFile(file);
        return makeWorkbookFromFileStream(fileStream);
    }

    private static FileInputStream getFileInputStreamFromFile(File mileageSourceFile){
        try{
            return new FileInputStream(mileageSourceFile);
        } catch(FileNotFoundException e){
            throw new RuntimeException("The input path to the Grades DB was not valid");
        }
    }

    private static XSSFWorkbook makeWorkbookFromFileStream(FileInputStream fis){
        try{
            return new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not create workbook from the input Grades DB");
        }
    }

    public static boolean fileExists(String filename){
        FileInputStream fs;
        try{
            fs = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            return false;
        }
        try{
            fs.close();
        } catch (IOException e) {
            return true;
        }
        return true;
    }
}
