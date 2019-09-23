package by.samtsov.travelagency.dal;


import by.samtsov.travelagency.service.reader.FileReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;


public class FileReaderTest {

/*
    @Test(expectedExceptions = NoSuchFileException.class)
    public void testReadFile() throws Exception {
        FileReader fileReader = new FileReader();
        fileReader.readFile("typo in name.txt");
        Assert.assertThrows(FileNotFoundException.class, () -> fileReader.readFile("input.txt"));
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void testReadFile2() throws Exception {
        FileReader fileReader = new FileReader();
        fileReader.readFile("input.txt");
        Assert.assertThrows(FileNotFoundException.class, () -> fileReader.readFile("input.txt"));
    }*/
}
