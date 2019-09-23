package by.samtsov.travelagency.dal;


import by.samtsov.travelagency.service.reader.FileReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;


public class FileReaderTest {



    @Test(expectedExceptions = NoSuchFileException.class)
    public void testReadFile() throws Exception {
        FileReader fileReader = new FileReader();
        fileReader.read("data/typo in name.txt");
    }

    @Test
    public void testReadFile2() throws IOException {
        FileReader fileReader = new FileReader();
        String[] result = fileReader.read("data/emptyFile.txt");
        String[] expected = new String[]{"\uFEFF"};
        Assert.assertEquals(result,expected);
    }

    @Test
    public void testReadFile3() throws Exception {
        FileReader fileReader = new FileReader();
        String[] result = fileReader.read("data/input.txt");
        String[] expected = {"Cruise,100,3,Poland weekend," +
                "tRain,AI,Poland,true",
                "Sightseeing,250,4,Old Paris,BUs,AI,France",
                "Relax,1500,10,Madrid,PLaIN,AI,Russia,false",
                "Sightseeing,200,4,nepal,PLaIN,AI,Nepal",
                "cruise,200,4,Poland weekend,BuS,BB,Russia,false",
                "cruise,300,5,Latvia weekend,shIp,HB,Latvia,true"};
        Assert.assertEquals(expected, result);
    }
}
