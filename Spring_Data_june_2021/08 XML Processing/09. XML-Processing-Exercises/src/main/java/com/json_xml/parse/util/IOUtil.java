package com.json_xml.parse.util;

import java.io.IOException;
import java.util.List;

public interface IOUtil {
    List<String> readFile(String path) throws IOException;

    void writeFile(String content, String path) throws IOException;

    void print(String... args);

    String read() throws IOException;
}
