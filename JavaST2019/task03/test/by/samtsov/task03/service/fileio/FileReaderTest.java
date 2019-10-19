package by.samtsov.task03.service.fileio;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;


public class FileReaderTest {


    @Test(expectedExceptions = FileNotFoundException.class)
    public void testReadFile() throws IOException {
        FileReader fileReader = new FileReader();
        fileReader.singleLineRead("test/data/typo in name.txt");
    }

    @Test
    public void testReadFile2() throws IOException {
        FileReader fileReader = new FileReader();
        String result = fileReader.singleLineRead("test/data/emptyFile.txt");
        String expected = new String();
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testReadFile3() throws Exception {
        FileReader fileReader = new FileReader();
        String result = fileReader.singleLineRead("test/data/input.txt");
        String expected = "Lores ipsum is Lores ipsum.\r\nit's true!";
        Assert.assertEquals(expected, result);
    }
}
