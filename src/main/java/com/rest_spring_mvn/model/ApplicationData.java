package com.rest_spring_mvn.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ApplicationData
{
    String token;
    String effectiveDate;
    String expireDate;
    String userName;
    String thaiFirstName;
    String thaiLastName;
    String engFirstName;
    String engLastName;
    String mobilePhone;
    String responseCode;
    String responseDesc;
    List systemList;
    List entityList;
    Object dumpPermission;
    List roles;
    List objects;

}
