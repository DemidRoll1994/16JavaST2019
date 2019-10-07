package by.samtsov.task02multithreadmatrix.dal;


import by.samtsov.task02multithreadmatrix.service.reader.FileReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;


public class FileReaderTest {


    @Test(expectedExceptions = NoSuchFileException.class)
    public void testReadFile() throws Exception {
        FileReader fileReader = new FileReader();
        fileReader.read("src/test/resources/data/typo in name.txt");
    }

    @Test
    public void testReadFile2() throws IOException {
        FileReader fileReader = new FileReader();
        String[] result = fileReader.read("src/test/resources/data/emptyFile.txt");
        String[] expected = new String[0];
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testReadFile3() throws Exception {
        FileReader fileReader = new FileReader();
        String[] result = fileReader.read("src/test/resources/data/input.txt");
        String[] expected = {"0,123,1,2,3,4", "1,0,1,2,3,4", "2,123,0,2,3,4"
                , "3,123,1,0,3,4", "4,123,1,2,0,4", "5,123,1,2,3,0"};
        Assert.assertEquals(expected, result);
    }
}
