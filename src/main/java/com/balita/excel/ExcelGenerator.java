package com.balita.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ExcelGenerator {

    public static void generateSampleOpeningBalance() {
        String[] columns = {"Account Name", "Account Number", "Account Type", "Description", "Currency",
                "Department/Cost Center", "Premium Receivable", "Unearned Premium Reserve",
                "Claims Reserve", "Outstanding Claims", "Incurred But Not Reported (IBNR) Reserve",
                "Reinsurance Recoverable", "Reinsurance Premium Payable", "Policyholder Liability",
                "Deferred Acquisition Costs", "Cash Balance", "Investments", "Fixed Assets",
                "Accounts Payable", "Accounts Receivable", "Deferred Revenue", "Other Liabilities",
                "Reinsurance Ceded", "Reinsurance Accepted", "Reinsurance Contract ID",
                "Gross Written Premium", "Net Written Premium", "Loss Adjustment Expense Reserve",
                "Subrogation Recoverable"};

        long maxHeapSize = Runtime.getRuntime().maxMemory();
        long maxHeapSizeInMB = maxHeapSize / (1024 * 1024);
        System.out.println("Max Heap Size (Xmx): " + maxHeapSizeInMB + " MB");

        System.out.println("Starting Excel file generation..." + (new Date()));
        Workbook workbook = new SXSSFWorkbook(100);
        Sheet sheet = workbook.createSheet("Opening Balance");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a CellStyle with blue background
        CellStyle blueBackgroundCellStyle = workbook.createCellStyle();
        blueBackgroundCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        blueBackgroundCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Other rows and cells with sample data
        for (int i = 1; i <= 350000; i++) {
            Row row = sheet.createRow(i);

            for (int j = 0; j < columns.length; j++) {
                Cell cell = row.createCell(j);
                if (columns[j].equals("Gross Written Premium") || columns[j].equals("Net Written Premium") ||
                        columns[j].equals("Loss Adjustment Expense Reserve") || columns[j].equals("Subrogation Recoverable")) {
                    cell.setCellValue(Math.random() * 1000000); // Example numeric value
                } else {
                    cell.setCellValue("Sample Data " + (i + 1));
                }
                cell.setCellStyle(blueBackgroundCellStyle);
            }
        }
        System.out.println("Data generation completed." + (new Date()));
        // Resize all columns to fit the content size
//        for (int i = 0; i < columns.length; i++) {
//            sheet.autoSizeColumn(i);
//        }
//        System.out.println("Resized all columns to fit the content size." + (new Date()));
        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream("opening_balance.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
        }

        // Closing the workbook
        try {
            workbook.close();
        } catch (IOException e) {
        }
        System.out.println("Excel file generated successfully." + (new Date()));
    }
}