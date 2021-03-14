package com.example.bookshop.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Component
public class FileUtilImpl implements FileUtil {
    @Override
    public String[] readFileContent(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        Set<String> lines = new HashSet<>();
        while ((line = reader.readLine()) != null) {
            if (!"".equals(line.trim())) {
                lines.add(line);
            }
        }
        return lines.toArray(String[]::new);
    }
}
