package by.samtsov.travelagency.services;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReadWriter {

    /**
     * Read file with Stream API
     */
    public List<String> readFile2(String path) throws IOException {
        File file = new File(path);
        return Files.lines(Paths.get(file.getAbsolutePath())).flatMap(s -> Stream.of(s.split(",")))
                .collect(Collectors.toList());
    }




    // todo
/*
    public class Program {

        public void reader(String[] args) {

            try(FileReader reader = new FileReader("notes3.txt"))
            {
                // читаем посимвольно
                int c;
                while((c=reader.read())!=-1){

                    System.out.print((char)c);
                }
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
    }




*/


    public List<String> readFile(String path) {
        try {
            File inputFile = new File("input.txt");
            File outputFile = new File("output.txt");

            FileInputStream fis = new FileInputStream(inputFile);
            FileOutputStream fos = new FileOutputStream(outputFile);
            int c;
 //todo del
            https://javarush.ru/groups/posts/2203-stream-api

            /*while ((c = fis.read()) != -1) {
                fos.write(c);
            }

            List list= new ArrayList();
            fos=list.stream();
*/
            while ((c = fis.read()) != -1) {
                fos.write(c);
            }

            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }

        return null;
    }
}
