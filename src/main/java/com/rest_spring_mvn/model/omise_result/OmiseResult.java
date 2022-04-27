package com.rest_spring_mvn.model.omise_result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OmiseResult {
    String object;
    String id;
    String location;
    Double amount;
    Double net;
    Double fee;
    Double fee_vat;
    Double interest;
    Double interest_vat;
    Double funding_amount;
    Double refunded_amount;
    OmiseTransactionFee transaction_fees;
    OmisePlatformFee platform_fee;
    String currency;
    String funding_currency;
    String ip;
    OmiseRefund refunds;
    String link;
    OmiseMetaData metadata;
    OmiseCard card;
    String source;
    String schedule;
    String customer;
    String dispute;
    String transaction;
    String failure_code;
    String failure_message;
    String status;
    String authorize_uri;
    String return_uri;
    Date created_at;
    Date paid_at;
    Date expires_at;
    Date expired_at;
    Date reversed_at;
    Boolean zero_interest_installments;
    String branch;
    String terminal;
    String device;
    Boolean authorized;
    Boolean capturable;
    Boolean capture;
    Boolean disputable;
    Boolean livemode;
    Boolean refundable;
    Boolean reversed;
    Boolean reversible;
    Boolean voided;
    Boolean paid;
    Boolean expired;
}
