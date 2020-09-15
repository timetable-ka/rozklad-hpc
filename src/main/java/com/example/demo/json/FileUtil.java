package com.example.demo.json;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.writeByteArrayToFile;

public class FileUtil {

    public static void createFile(byte[] bytes, String pathname) {
        try {
            writeByteArrayToFile(new File(pathname), bytes);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
