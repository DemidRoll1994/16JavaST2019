package by.samtsov.task03.service.fileio;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class FileServiceTest {

    static final String DEFAULT_OUTPUT_PATH ="src/test/data/output.txt";

    @Test
    public void testReadFile() throws IOException {
        FileService fileService = new FileService();
        String result = fileService.singleLineRead("src/test/data/typo.txt");
        String expected = "";
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testReadFile2() throws IOException {
        FileService fileService = new FileService();
        String result = fileService.singleLineRead("src/test/data/emptyFile.txt");
        String expected = "";
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testReadFile3() throws IOException {
        FileService fileService = new FileService();
        String result = fileService.singleLineRead("src/test/data/input.txt");
        String expected = "Lores ipsum is Lores ipsum.\r\nit's true!";
        Assert.assertEquals(result, expected);
    }

    @Test
    public void testReadFile4() throws IOException {
        FileService fileService = new FileService();
        String result = fileService.singleLineRead("src/test/data/lab3-text.txt");
        String expected = "\tIt has survived - not only (five) centuries, but" +
                " also the leap into 13<<2 electronic typesetting, remaining " +
                "30>>>3 essentially ~6&9|(3&4) unchanged. It was popularised " +
                "in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of" +
                " Letraset sheets containing Lorem Ipsum passages, and more r" +
                "ecently with desktop publishing software like Aldus PageMake" +
                "r including versions of Lorem Ipsum.\t\r\n" +
                "\tIt is a long established fact that a reader will be distra" +
                "cted by the readable content of a page when looking at its l" +
                "ayout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|" +
                "78 Ipsum is that it has a more-or-less normal distribution o" +
                "f letters, as opposed to using (Content here), content here'" +
                ", making it look like readable English.\r\n" +
                "\tIt is a (111^5|1&2<<(2|5>>2&71))|1200 established fact tha" +
                "t a reader will be of a page when looking at its layout.\r\n" +
                "\tBye.\r\n";
        Assert.assertEquals(result, expected);
    }


    @Test
    public void testWriteFile() throws IOException {
        FileService fileService = new FileService();
        String result = fileService.singleLineWrite("src/test/typo.txt", "");
        String expected = null;
        Assert.assertEquals(result, expected);
    }


    @Test
    public void testWriteFile2() throws IOException {
        FileService fileService = new FileService();
        String path = DEFAULT_OUTPUT_PATH;
        String text = "Lores ipsum is Lores ipsum.\r\nit's true!";
        String result = fileService.singleLineWrite(path, text);
        Assert.assertEquals(text, result);
    }


    @Test
    public void testReadAndWriteFile4() throws IOException {
        FileService fileService = new FileService();
        String path = DEFAULT_OUTPUT_PATH;
        String inputText = "    It has survived - not only (five) centuries, but" +
                " also the leap into 13<<2 electronic typesetting, remaining " +
                "30>>>3 essentially ~6&9|(3&4) unchanged. It was popularised " +
                "in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of" +
                " Letraset sheets containing Lorem Ipsum passages, and more r" +
                "ecently with desktop publishing software like Aldus PageMake" +
                "r including versions of Lorem Ipsum.    \r\n" +
                "    It is a long established fact that a reader will be distra" +
                "cted by the readable content of a page when looking at its l" +
                "ayout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|" +
                "78 Ipsum is that it has a more-or-less normal distribution o" +
                "f letters, as opposed to using (Content here), content here'" +
                ", making it look like readable English.\r\n" +
                "    It is a (111^5|1&2<<(2|5>>2&71))|1200 established fact tha" +
                "t a reader will be of a page when looking at its layout.\r\n" +
                "    Bye.\r\n";
        fileService.singleLineWrite(path, inputText);
        String expected = fileService.singleLineRead(path);
        Assert.assertEquals(inputText, expected);
    }


    @DataProvider(name = "inputDataAndResult")
    public Object[][] createData() {
        return new Object[][]{
                {DEFAULT_OUTPUT_PATH, true},
                {"src/test/data/input.txt", true},
                {"src/test/data/output", false},
                {"src/test/data", false},
        };
    }

    @Test(dataProvider = "inputDataAndResult")
    public void testIsFileExist(String path, boolean isExist) {
        FileService fileService = new FileService();
        Assert.assertTrue(!fileService.isFileExist(path) ^ isExist);
    }

}
