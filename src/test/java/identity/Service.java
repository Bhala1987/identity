package identity;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Bhala on 29-10-2017.
 */
@Component
public class Service {

    private final static Logger LOGGER = LoggerFactory.getLogger(Service.class);

    protected static File[] ListFiles(String DirectoryName) throws IOException {
        //project directory
        String workingDirectory = System.getProperty("user.dir");
        String dir = workingDirectory + File.separator + DirectoryName;

        LOGGER.info("File directory is " + dir + "\n");
        //List files in the directory

        File file = new File(dir);

        List<String> filterWildcards = new ArrayList<>();
        filterWildcards.add("*.xls");
        filterWildcards.add("*.xlsx");
        filterWildcards.add("*.csv");

        LOGGER.info("Files filtered by extension " + filterWildcards + "\n");
        FileFilter fileFilter = new WildcardFileFilter(filterWildcards);
        File[] files = file.listFiles(fileFilter);

        if (file.exists()) {
            LOGGER.info("Files exists and directory is found!\n");
            for (File eachFile : files) {
                LOGGER.info("Filename is : " + eachFile.getName() + "\n");
                LOGGER.info("File mime type of " + eachFile.getName() + " is : " + Files.probeContentType(eachFile.toPath()) + "\n");

                double bytes = eachFile.length();
                double kilobytes = (bytes / 1024);
                LOGGER.info("File size of " + eachFile.getName() + " is : " + kilobytes + " KB" + "\n");
                LOGGER.info("File extension for " + eachFile.getName() + " is : " + FilenameUtils.getExtension(eachFile.getName()) + "\n");
            }
        } else {
            LOGGER.info("Directory doesn't exits");
        }

        return files;
    }

    protected static void readExcel(File excelFile) {
        try {

            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();

                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        LOGGER.info(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        LOGGER.info(currentCell.getNumericCellValue() + "--");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    protected static int getRowsFromExcel(File excelFile) {
        int numRows = 0;
        try {
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);

            numRows = datatypeSheet.getPhysicalNumberOfRows();

            return numRows;
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return numRows;
    }

    protected static String readExcelString(File excelFile, int rowNum, int column) {
        String value = null;
        try {

            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);

            value = datatypeSheet.getRow(rowNum).getCell(column).getStringCellValue();
            if (value != null) {
                return value;
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return value;
    }

    protected List<String> readCSVvalues(File csvFile) throws IOException {
        List<String> lines = Files.readAllLines(csvFile.toPath(),
                StandardCharsets.UTF_8);
        for (String line : lines) {
            String[] array = line.split(",");
            LOGGER.info("The values in CSV are " + array[0] + "\n");
        }
        return lines;
    }

}
