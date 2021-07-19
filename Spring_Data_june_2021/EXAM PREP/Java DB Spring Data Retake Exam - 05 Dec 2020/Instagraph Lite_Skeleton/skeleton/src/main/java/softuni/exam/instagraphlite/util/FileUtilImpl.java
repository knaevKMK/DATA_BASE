package softuni.exam.instagraphlite.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtilImpl implements FileUtil {
    @Override
    public String content(String path, String separator) throws IOException {
        return String.join(separator, Files.readAllLines(Path.of(path)));
    }
}
