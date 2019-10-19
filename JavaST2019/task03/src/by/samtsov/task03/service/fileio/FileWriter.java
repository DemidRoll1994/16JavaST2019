package by.samtsov.task03.service.fileio;

import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {

    public void singleLineWrite(String path, String text) throws IOException {

        try (PrintWriter out = new PrintWriter(path)) {
            out.print(text);
        }
    }
}
