package hiberspring.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtilImpl implements FileUtil {
    @Override
    public List<String> readFile(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }
}
