package by.samtsov.task03.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
    public String[] read(String path) throws IOException {
        try (Stream stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            return (String[]) stream.toArray(String[]::new);
        }
    }
}
