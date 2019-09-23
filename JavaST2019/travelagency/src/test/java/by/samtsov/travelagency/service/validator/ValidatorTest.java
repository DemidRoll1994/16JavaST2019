package by.samtsov.travelagency.service.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ValidatorTest {
    @DataProvider(name = "inputDataAndResult")
    public Object[][] createData() {
        return new Object[][]{
                {new String[]{"Sightseeing", "250", "4", "Old Paris", "BUs",
                        "AI", "France"}, true},
                {new String[]{"Relax", "1500", "10", "Madrid", "PLaIN",
                        "AI", "Russia", "false"}, true},
                {new String[]{"cruise", "200", "4", "Poland weekend", "BUs",
                        "BB", "Russia", "false"}, true},
                {new String[]{"cruise", "-10", "0", null, "BUs",
                        "BB", "Russia", "false"}, false},
                {new String[]{"cruse", "200", "4", "Poland weekend", "BUs",
                        "BB", "Russia", "false"}, false},
                {new String[]{"HealthCare", "200", "4", "Poland weekend", "BUs",
                        "BB", "Spa", "false", "false", "false"}, true},
                {new String[]{"shopping", "200", "4", "Poland weekend", "BUs",
                        "BB", "Byn", "false"}, true},
                {new String[]{"HealthCare", "200", "4", "Poland weekend", "BUs",
                        "BB", "gastro", "false"}, true},
                {new String[]{}, false},
                {new String[]{"HealthCare", "200", "4", "BUs"}, false}
        };
    }

    @Test(dataProvider = "inputDataAndResult")
    public void isValidTest(String[] line, boolean isValid) {
        Assert.assertEquals(new Validator().isValid(Arrays.asList(line)), isValid);
    }

}
