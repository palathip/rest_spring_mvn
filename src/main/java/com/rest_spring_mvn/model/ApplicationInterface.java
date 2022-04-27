package com.rest_spring_mvn.model;

import com.rest_spring_mvn.model.omise_result.OmiseResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ApplicationInterface {
    String application_no;
    String product_code;
    Double sum_insured;
    Double net_premium;
    Double gross_premium;
    Date effective_date;
    Date expired_date;
    String cardholder_title_name;
    String cardholder_title_name_en;
    String cardholder_name;
    String cardholder_name_en;
    String cardholder_lastname;
    String cardholder_lastname_en;
    String moobarn;
    String room_number;
    String home_number;
    String moo;
    String soi;
    String road;
    String tumbol;
    String amphur;
    String province;
    String post_code;
    String telephone_extension;
    String telephone;
    String fax;
    String occupation;
    String mode_of_payment;
    String payment_channel;
    String customer_type;
    String card_type;
    String citizen_card_id;
    String gender;
    String marriage_status;
    String channel_flag;
    String insurer_code;
    Double policy_year;
    Date selling_date;
    String campingn_code;
    List<InsuredPerson> insured_person;
    OmiseResult payment_detail;
}
