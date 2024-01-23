package com.rest_spring_mvn.model.baybillingdetail;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BillingDetail {
    private String bayMerchantNo;
    private String statementPref;
    private String cardNo;
    private String expiryDate;
    private Long amount;
    private String applicationNo;
    private Long period;
    private String policyYear;
    private String description;
    private String diget;

}
