package java.by.samtsov.task03.service.fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileService {
    /**
     * read all text in file symbol java.by symbol and return string value of text.
     *
     * @param path - path to file
     * @return - All text in one line
     * @throws IOException - if file doesn't exist or blocked
     */
    public String singleLineRead(final String path) throws IOException {
        if (!isFileExist(path)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fin = new FileInputStream(path);
        int i;
        while ((i = fin.read()) != -1) {
            stringBuilder.append((char) i);
        }
        return stringBuilder.toString();
    }

    /**
     * write String value in file in single line.
     *
     * @param path - path to file
     * @param text - writing files
     * @return text value if writing successful, or return null if file doesn't
     * exist
     * @throws IOException - if file doesn't exist or blocked
     */

    public String singleLineWrite(final String path, final String text)
            throws IOException {
        if (!isFileExist(path)) {
            return null;
        }
        try (PrintWriter out = new PrintWriter(path)) {
            out.print(text);
        }
        return text;
    }

    /**
     * method check file for existing.
     *
     * @param filePath - path to checked file
     * @return false if files doesn't exist selected path is not a file
     */
    public boolean isFileExist(final String filePath) {
        if (filePath == null) {
            return false;
        }
        File f = new File(filePath);
        return f.exists() && !f.isDirectory();
    }

}
