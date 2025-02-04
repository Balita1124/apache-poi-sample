package com.balita.excel;

import com.balita.excel.utils.DataQuality;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestDataQuality {

    @Test
    void testSanitizeNumberToStringFromExcel_NormalNumber() {
        String number = "1234567890";
        String sanitizedNumber = DataQuality.sanitizeNumberToStringFromExcel(number);
        assertEquals("1234567890", sanitizedNumber);
    }

    @Test
    void testSanitizeNumberToStringFromExcel_Decimal() {
        String number = "1234567890.0";
        String sanitizedNumber = DataQuality.sanitizeNumberToStringFromExcel(number);
        assertEquals("1234567890", sanitizedNumber);
    }

    @Test
    void testSanitizeNumberToStringFromExcel_Scientific() {
        String number = "3.456e11";
        String sanitizedNumber = DataQuality.sanitizeNumberToStringFromExcel(number);
        assertEquals("345600000000", sanitizedNumber);
    }

    @Test
    void testSanitizeNumberToStringFromExcel_Text() {
        String number = "004500ERD";
        String sanitizedNumber = DataQuality.sanitizeNumberToStringFromExcel(number);
        assertEquals("004500ERD", sanitizedNumber);
    }

    @Test
    void testSanitizeNumberToStringFromExcel_TextNumberLeadingZero() {
        String number = "000345600000000";
        String sanitizedNumber = DataQuality.sanitizeNumberToStringFromExcel(number);
        assertEquals("000345600000000", sanitizedNumber);
    }

    @Test
    void testSanitizeNumberToStringFromExcel_multipleNumber() {
        String number = "00034,5600000000";
        String sanitizedNumber = DataQuality.sanitizeNumberToStringFromExcel(number);
        assertEquals("00034,5600000000", sanitizedNumber);
    }

    @Test
    void testSanitizeNumberToStringFromExcel_multipleNumberSeparated_by_point() {
        String number = "00034.5600000000";
        String sanitizedNumber = DataQuality.sanitizeNumberToStringFromExcel(number);
        assertEquals("00034.5600000000", sanitizedNumber);
    }
}
