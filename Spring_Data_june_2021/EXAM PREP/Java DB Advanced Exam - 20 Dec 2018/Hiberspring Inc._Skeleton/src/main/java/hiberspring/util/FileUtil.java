package hiberspring.util;

import java.io.IOException;
import java.util.List;

public interface FileUtil {

    List<String> readFile(String filePath) throws IOException;
}
