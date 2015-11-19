package barringer.patrick.mileage;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Patrick Barringer on 11/17/2015.
 */
public class FileHelperFunctions {
    public static File getFile(){
        JFrame chooserParent = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel 2007 or later", "xlsx");
        fileChooser.setFileFilter(filter);
        int fileChooserState = fileChooser.showOpenDialog(chooserParent);
        if(fileChooserState == JFileChooser.APPROVE_OPTION){
            chooserParent.dispose();
            return fileChooser.getSelectedFile();
        }
        throw new RuntimeException("You had to select a file for this program to function.");
    }

    public static XSSFWorkbook getWorkbook(File file){
        FileInputStream fileStream = getFileInputStreamFromFile(file);
        return makeWorkbookFromFileStream(fileStream);
    }

    private static FileInputStream getFileInputStreamFromFile(File mileageSourceFile){
        try{
            return new FileInputStream(mileageSourceFile);
        } catch(FileNotFoundException e){
            throw new RuntimeException("The input path to the File was not valid");
        }
    }

    private static XSSFWorkbook makeWorkbookFromFileStream(FileInputStream fis){
        try{
            return new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not create workbook from the input File");
        }
    }

    public static void writeFile(XSSFWorkbook workbook, File file){
        try{
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
