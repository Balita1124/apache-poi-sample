package com.balita.excel.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;

@Data
@Getter
@Setter
@NoArgsConstructor
public class OpeningBalance {
    private String accountName;
    private String accountNumber;
    private String accountType;
    private String description;
    private String currency;
    private String departmentCostCenter;
    private String premiumReceivable;
    private String unearnedPremiumReserve;
    private String claimsReserve;
    private String outstandingClaims;
    private String incurredButNotReportedReserve;
    private String reinsuranceRecoverable;
    private String reinsurancePremiumPayable;
    private String policyholderLiability;
    private String deferredAcquisitionCosts;
    private String cashBalance;
    private String investments;
    private String fixedAssets;
    private String accountsPayable;
    private String accountsReceivable;
    private String deferredRevenue;
    private String otherLiabilities;
    private String reinsuranceCeded;
    private String reinsuranceAccepted;
    private String reinsuranceContractID;
    private String grossWrittenPremium;
    private String netWrittenPremium;
    private String lossAdjustmentExpenseReserve;
    private String subrogationRecoverable;

    public LinkedHashMap<String, String> HEADERS(){
        LinkedHashMap<String, String> headers = new LinkedHashMap<>();
        headers.put("accountName", "Account Name");
        headers.put("accountNumber", "Account Number");
        headers.put("accountType", "Account Type");
        headers.put("description", "Description");
        headers.put("currency", "Currency");
        headers.put("departmentCostCenter", "Department/Cost Center");
        headers.put("premiumReceivable", "Premium Receivable");
        headers.put("unearnedPremiumReserve", "Unearned Premium Reserve");
        headers.put("claimsReserve", "Claims Reserve");
        headers.put("outstandingClaims", "Outstanding Claims");
        headers.put("incurredButNotReportedReserve", "Incurred But Not Reported (IBNR) Reserve");
        headers.put("reinsuranceRecoverable", "Reinsurance Recoverable");
        headers.put("reinsurancePremiumPayable", "Reinsurance Premium Payable");
        headers.put("policyholderLiability", "Policyholder Liability");
        headers.put("deferredAcquisitionCosts", "Deferred Acquisition Costs");
        headers.put("cashBalance", "Cash Balance");
        headers.put("investments", "Investments");
        headers.put("fixedAssets", "Fixed Assets");
        headers.put("accountsPayable", "Accounts Payable");
        headers.put("accountsReceivable", "Accounts Receivable");
        headers.put("deferredRevenue", "Deferred Revenue");
        headers.put("otherLiabilities", "Other Liabilities");
        headers.put("reinsuranceCeded", "Reinsurance Ceded");
        headers.put("reinsuranceAccepted", "Reinsurance Accepted");
        headers.put("reinsuranceContractID", "Reinsurance Contract ID");
        headers.put("grossWrittenPremium", "Gross Written Premium");
        headers.put("netWrittenPremium", "Net Written Premium");
        headers.put("lossAdjustmentExpenseReserve", "Loss Adjustment Expense Reserve");
        headers.put("subrogationRecoverable", "Subrogation Recoverable");
        return headers;
    }

}
