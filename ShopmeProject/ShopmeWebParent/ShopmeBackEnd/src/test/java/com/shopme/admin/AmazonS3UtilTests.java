package com.shopme.admin;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class AmazonS3UtilTests {
    @Test
    public void testListFolder() {
        String folderName = "test-upload/one/";
        List<String> listKeys = AmazonS3Util.listFolder(folderName);
        listKeys.forEach(System.out::println);
    }

    @Test
    public void testUploadFile() throws FileNotFoundException {
        String folderName = "test-upload/one";
        String fileName = "certificate-of-competition.jpg";
        String filePath = "E:\\JAVA\\Project\\Test\\" + fileName;

        InputStream inputStream = new FileInputStream(filePath);

        AmazonS3Util.uploadFile(folderName, fileName, inputStream);

    }

    @Test
    public void testDeleteFile() {
        String fileName = "test-upload/one/certificate-of-competition.jpg";
        AmazonS3Util.deleteFile(fileName);
    }

    @Test
    public void testRemoveFolder() {
        String folderName = "test-upload";
        AmazonS3Util.removeFolder(folderName);
    }
}
