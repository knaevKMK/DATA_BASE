package softuni.exam.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class IOUtilImpl implements IOUtil {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public List<String> readFile(String path) throws IOException {

        return  Files.readAllLines(Path.of(path));
    }

    @Override
    public void writeFile(String content, String path) throws IOException {

        Files.write(Path.of(path), Collections.singleton(content));

    }

    @Override
    public void print(String... args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    @Override
    public String read() throws IOException {
        return  bufferedReader.readLine();
    }
}
