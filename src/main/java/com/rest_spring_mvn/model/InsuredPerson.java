package com.rest_spring_mvn.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class InsuredPerson {
    String insured_title_name;
    String insured_title_name_en;
    String insured_name;
    String insured_name_en;
    String insured_lastname;
    String insured_lastname_en;
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
    String days;
    String months;
    String years;
    Double age;
    String gender;
    String marriage_status;
}
