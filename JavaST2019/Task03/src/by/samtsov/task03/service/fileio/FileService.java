package by.samtsov.task03.service.fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileService {

    public String singleLineRead(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fin = new FileInputStream(path);
        int i;
        while ((i = fin.read()) != -1) {
            stringBuilder.append((char) i);
        }
        return stringBuilder.toString();
    }


    public void singleLineWrite(String path, String text) throws IOException {

        try (PrintWriter out = new PrintWriter(path)) {
            out.print(text);
        }

        System.out.println(text); //todo
    }

    public boolean isFileExist(String filePath){
        File f = new File(filePath);
        return f.exists() && !f.isDirectory();
    }

}
