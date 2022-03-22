package com.rest_spring_mvn.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "USER_DATA")
public class ApplicationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String effectiveDate;
    String expireDate;
    String userName;
    String openId;
    String passWord;
    String thaiFirstName;
    String thaiLastName;
    String engFirstName;
    String engLastName;
    String mobilePhone;
}
