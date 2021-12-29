package com.segunfrancis.payoneerpaymentmethods.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class MockResponseFileReader {

    public String content;

    public MockResponseFileReader(String path) {
        InputStreamReader reader = new InputStreamReader(
                Objects.requireNonNull(this.getClass().getClassLoader()).getResourceAsStream(path)
        );
        try {
            StringBuilder sb = new StringBuilder();
            for (int ch; (ch = reader.read()) != -1; ) {
                sb.append((char) ch);
            }
            content = sb.toString();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
