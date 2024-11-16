package com.balita.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


import javax.xml.bind.JAXBException;
import java.io.IOException;

public class ExcelMain {

    public static void main(String[] args) throws InvalidFormatException, JAXBException, IOException {
        long maxHeapSize = Runtime.getRuntime().maxMemory();
        long maxHeapSizeInMB = maxHeapSize / (1024 * 1024);
        System.out.println("Max Heap Size (Xmx): " + maxHeapSizeInMB + " MB");
        ExcelReader reader = new ExcelReader();
        reader.readSampleOpeningBalance2();
//        ExcelGenerator.generateSampleOpeningBalance();
    }
}
