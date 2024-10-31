package com.balita.excel;

import com.balita.excel.model.OpeningBalance;
import com.github.pjfanning.xlsx.StreamingReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelReader {

    public void readSampleOpeningBalance() throws InvalidFormatException, JAXBException, IOException {
        IOUtils.setByteArrayMaxOverride(250_000_000);
        String excelFilePath = "opening_balance.xlsx";
        List<OpeningBalance> openingBalances = new ArrayList<>();
        System.out.println("Reading Excel file...");
        OPCPackage opcPackage = null;
        Workbook workbook = null;
        try {
            opcPackage = OPCPackage.open(excelFilePath, PackageAccess.READ);
            workbook = new SXSSFWorkbook(new XSSFWorkbook(opcPackage), 100, true, true);

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("Row count: " + rowCount);
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                mapRowToOpeningBalance(row, openingBalances);

                if (openingBalances.size() == 5000) {
                    convertToXmlAndPrint(openingBalances);
                    openingBalances.clear();
                }
            }

            if (!openingBalances.isEmpty()) {
                convertToXmlAndPrint(openingBalances);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (opcPackage != null)
                opcPackage.close();
            if(workbook != null)
                workbook.close();
        }
    }

    public void readSampleOpeningBalance2() throws JAXBException, IOException {
        String excelFilePath = "opening_balance.xlsx";
        List<OpeningBalance> openingBalances = new ArrayList<>();
        System.out.println("Reading Excel file..." + new Date());

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = StreamingReader.builder()
                     .rowCacheSize(100)  // number of rows to keep in memory (defaults to 10)
                     .bufferSize(4096)   // buffer size to use when reading InputStream to file (defaults to 1024)
                     .open(fis)) {
            System.out.println("End Reading Excel file..." + new Date());
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = 0;
            for (Row row : sheet) {
                rowCount++;
                if (rowCount == 1) continue; // Skip header row

                mapRowToOpeningBalance(row, openingBalances);

                if (openingBalances.size() == 5000) {
                    convertToXmlAndPrint(openingBalances);
                    openingBalances.clear();
                }
            }

            if (!openingBalances.isEmpty()) {
                convertToXmlAndPrint(openingBalances);
            }
            System.out.println("End Handling xml..." + new Date());
        }
    }

    private void mapRowToOpeningBalance(Row row, List<OpeningBalance> openingBalances) {
        OpeningBalance ob = new OpeningBalance();
        ob.setAccountName(getCellValue(row.getCell(0)));
        ob.setAccountNumber(getCellValue(row.getCell(1)));
        ob.setAccountType(getCellValue(row.getCell(2)));
        ob.setDescription(getCellValue(row.getCell(3)));
        ob.setCurrency(getCellValue(row.getCell(4)));
        ob.setDepartmentCostCenter(getCellValue(row.getCell(5)));
        ob.setPremiumReceivable(getCellValue(row.getCell(6)));
        ob.setUnearnedPremiumReserve(getCellValue(row.getCell(7)));
        ob.setClaimsReserve(getCellValue(row.getCell(8)));
        ob.setOutstandingClaims(getCellValue(row.getCell(9)));
        ob.setIncurredButNotReportedReserve(getCellValue(row.getCell(10)));
        ob.setReinsuranceRecoverable(getCellValue(row.getCell(11)));
        ob.setReinsurancePremiumPayable(getCellValue(row.getCell(12)));
        ob.setPolicyholderLiability(getCellValue(row.getCell(13)));
        ob.setDeferredAcquisitionCosts(getCellValue(row.getCell(14)));
        ob.setCashBalance(getCellValue(row.getCell(15)));
        ob.setInvestments(getCellValue(row.getCell(16)));
        ob.setFixedAssets(getCellValue(row.getCell(17)));
        ob.setAccountsPayable(getCellValue(row.getCell(18)));
        ob.setAccountsReceivable(getCellValue(row.getCell(19)));
        ob.setDeferredRevenue(getCellValue(row.getCell(20)));
        ob.setOtherLiabilities(getCellValue(row.getCell(21)));
        ob.setReinsuranceCeded(getCellValue(row.getCell(22)));
        ob.setReinsuranceAccepted(getCellValue(row.getCell(23)));
        ob.setReinsuranceContractID(getCellValue(row.getCell(24)));
        ob.setGrossWrittenPremium(getCellValue(row.getCell(25)));
        ob.setNetWrittenPremium(getCellValue(row.getCell(26)));
        ob.setLossAdjustmentExpenseReserve(getCellValue(row.getCell(27)));
        ob.setSubrogationRecoverable(getCellValue(row.getCell(28)));
        openingBalances.add(ob);
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private void convertToXmlAndPrint(List<OpeningBalance> openingBalances) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(OpeningBalanceList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        OpeningBalanceList obList = new OpeningBalanceList();
        obList.setOpeningBalances(openingBalances);

        marshaller.marshal(obList, System.out);
    }

    @XmlRootElement
    public static class OpeningBalanceList {
        private List<OpeningBalance> openingBalances;

        public List<OpeningBalance> getOpeningBalances() {
            return openingBalances;
        }

        public void setOpeningBalances(List<OpeningBalance> openingBalances) {
            this.openingBalances = openingBalances;
        }
    }
}