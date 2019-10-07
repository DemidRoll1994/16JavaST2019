package by.samtsov.task02multithreadmatrix.service.reader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {


    /**
     * Read file with Stream API from
     *
     * @param path - the path to the file
     */
    public String[] read(String path) throws IOException {
        try (Stream stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            return (String[]) stream.toArray(String[]::new);
        }
    }

}
