package by.samtsov.task03.service.fileio;

import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

    public String singleLineRead(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fin = new FileInputStream(path);
        int i;
        while ((i = fin.read()) != -1) {
            stringBuilder.append((char) i);
        }
        return stringBuilder.toString();
    }

}
