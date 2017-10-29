package identity.stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import identity.PageRepository;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

;

/**
 * Created by Bhala.
 */
public class identity extends PageRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(identity.class);


    File[] files;
    String url;

    @Given("^I have got xls or csv file from the directory$")
    public void iHaveGotXlsOrCsvFileFromTheDirectory() throws Throwable {

        files = ListFiles("src/test/resources/directory");
        LOGGER.info("The list of files found are " + files + "\n");
    }


    @When("^I launch the \"([^\"]*)\" URL$")
    public void iLaunchTheURL(String url) throws Throwable {
        this.url = url;
        ukGovVehicleInfoPage.openPage(url);
    }

    @Then("^the make and colour should match by the vehicle registration number$")
    public void theMakeAndColourShouldMatchByTheVehicleRegistrationNumber() throws Throwable {
        for (File file : files) {
            if (FilenameUtils.getExtension(file.getName()).contains("xls")) {
                readExcel(file);
                int rows = getRowsFromExcel(file);
                for (int i = 1; i < rows; i++) {
                    readExcelString(file, i, 0);
                    iLaunchTheURL(url);
                    ukGovVehicleInfoPage.enterRegistrationNumber(readExcelString(file, i, 0));
                    ukGovVehicleInfoPage.clickContinue();
                    LOGGER.info("----Asserting the registration number from excel with the UK gov website----\n");
                    assertThat("Registration number is not correct.", ukGovVehicleInfoPage.getRegistrationNumber().equalsIgnoreCase(readExcelString(file, i, 0)));
                    LOGGER.info("----Asserting the make of the vehicle from excel with the UK gov website----\n");
                    assertThat("Make is not correct.", ukGovVehicleInfoPage.getMake().equalsIgnoreCase(readExcelString(file, i, 1)));
                    LOGGER.info("----Asserting the colour of the vehicle from excel with the UK gov website----\n");
                    assertThat("Colour is not correct.", ukGovVehicleInfoPage.getColour().equalsIgnoreCase(readExcelString(file, i, 2)));
                }
            }

            if (FilenameUtils.getExtension(file.getName()).contains("csv")) {
                List<String> csvValues = readCSVvalues(file);
                csvValues.remove(0);
                for (String line : csvValues) {
                    String[] array = line.split(",");

                    iLaunchTheURL(url);

                    ukGovVehicleInfoPage.enterRegistrationNumber(array[0]);
                    ukGovVehicleInfoPage.clickContinue();
                    LOGGER.info("----Asserting the registration number from CSV with the UK gov website----\n");
                    assertThat("Registration number is not correct.", ukGovVehicleInfoPage.getRegistrationNumber().equalsIgnoreCase(array[0]));
                    LOGGER.info("----Asserting the make of the vehicle from CSV with the UK gov website----\n");
                    assertThat("Make is not correct.", ukGovVehicleInfoPage.getMake().equalsIgnoreCase(array[1]));
                    LOGGER.info("----Asserting the colour of the vehicle from CSV with the UK gov website----\n");
                    assertThat("Colour is not correct.", ukGovVehicleInfoPage.getColour().equalsIgnoreCase(array[2]));

                }
            }
        }
    }
}