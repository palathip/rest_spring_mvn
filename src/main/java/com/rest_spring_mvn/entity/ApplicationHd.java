package com.rest_spring_mvn.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "TEMP_TM_IMPAPP_HEADER")
public class ApplicationHd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String batchCode;

    Date batchDate;

    String applicationNo;

    String policyClass;

    @Column(precision = 15, scale = 2)
    Double sumInsured;

    @Column(precision = 15, scale = 2)
    Double netPremium;

    @Column(precision = 15, scale = 2)
    Double grossPremium;

    Date effectiveDate;

    Date expiredDate;

    String fleetFlag;

    String insuredTitleName;

    String insuredTitleNameEn;

    String insuredName;

    String insuredNameEn;

    String insuredLastname;

    String insuredLastnameEn;

    String moobarn;

    String roomNumber;

    String homeNumber;

    String moo;

    String soi;

    String road;

    String tumbol;

    String amphur;

    String province;

    String postCode;

    String telephoneExtension;

    String telephone;

    String accountKeyDwh;

    String occupation;

    String modeOfPayment;

    String paymentChannel;

    String creditCardType;

    String creditCardNo;

    String cardIssuedName;

    String expiryDate;

    String customerType;

    String cardType;

    String cardNo;

    String detailNo;

    String status;

    String gender;

    String marriageStatus;

    @Column(precision = 15, scale = 2)
    Double customerKeyDwh;

    String channelFlag;

    String insurerCode;

    String policyYear;

    String baseCreditCard;

    Date sellingDate;

    String campaignCode;
}
