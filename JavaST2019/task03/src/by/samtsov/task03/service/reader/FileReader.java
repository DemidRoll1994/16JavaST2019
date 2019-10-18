package by.samtsov.task03.service.reader;

import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

    public String singleLineRead(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fin = new FileInputStream(path);
        int i = -1;
        while ((i = fin.read()) != -1) {
            stringBuilder.append((char) i);
        }
        return stringBuilder.toString();
    }

}
