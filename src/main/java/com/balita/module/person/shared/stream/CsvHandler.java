package com.balita.module.person.shared.stream;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CsvHandler {

    public void zipFile(ZipOutputStream zipOutputStream, String fileName, String csvContent) {
        try {
            zipOutputStream.putNextEntry(new ZipEntry(fileName));
            zipOutputStream.write(csvContent.getBytes());
            zipOutputStream.closeEntry();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
