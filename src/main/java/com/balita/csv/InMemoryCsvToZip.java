package com.balita.csv;

import com.balita.module.person.service.PersonService;
import com.balita.module.person.shared.stream.CsvHandler;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;

public class InMemoryCsvToZip {

    public static void main(String[] args) {
        try (ByteArrayOutputStream zipOutputStream = new ByteArrayOutputStream();
             ZipOutputStream zipStream = new ZipOutputStream(zipOutputStream);
             FileOutputStream fos = new FileOutputStream("C:\\dev\\output\\persons.zip")) {

            CsvHandler csvHandler = new CsvHandler();
            PersonService personService = new PersonService();

            addCsvToZip(zipStream, csvHandler, personService, 2000, "person2000.csv");
            addCsvToZip(zipStream, csvHandler, personService, 600, "person600.csv");
            addCsvToZip(zipStream, csvHandler, personService, 1200, "person1200.csv");

            zipStream.close();
            fos.write(zipOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addCsvToZip(ZipOutputStream zipStream, CsvHandler csvHandler, PersonService personService, int count, String filename) throws Exception {
        String csvContent = personService.generateCsvContent(count);
        csvHandler.zipFile(zipStream, filename, csvContent);
    }
}