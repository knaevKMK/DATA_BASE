package com.gameHall.user.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IoUtilImpl implements IoUtil {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String[] input() throws IOException {
        return reader.readLine().split(System.lineSeparator());
    }

    @Override
    public void output(String... args) {
        System.out.println(String.join(System.lineSeparator(), args));
    }
}
