package com.balita.excel.utils;

import java.math.BigDecimal;

public class DataQuality {
    public static String sanitizeNumberToStringFromExcel(String number) {
        if (number == null || number.isEmpty()) {
            return "";
        }
        int leadingZeros = 0;
        while (number.startsWith("0")) {
            number = number.substring(1);
            leadingZeros++;
        }
        try {
            BigDecimal bd = new BigDecimal(number);
            number = bd.stripTrailingZeros().toPlainString();
            number = "0".repeat(leadingZeros) + number;
        } catch (NumberFormatException e) {
            return number;
        }
        return number;
    }
}