package com.example.demo.json;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.apache.commons.io.FileUtils.writeByteArrayToFile;

public class FileUtil {

    public static void createFile(byte[] bytes, String pathname) {
        Resource resource = new FileSystemResource(pathname);
        try {
            writeByteArrayToFile(resource.getFile(), bytes);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
