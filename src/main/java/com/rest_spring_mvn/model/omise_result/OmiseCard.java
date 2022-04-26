package com.rest_spring_mvn.model.omise_result;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OmiseCard {
        String object;
        String id;
        Boolean livemode;
        String location;
        Boolean deleted;
        String street1;
        String street2;
        String city;
        String state;
        String phone_number;
        String postal_code;
        String country;
        String financing;
        String bank;
        String brand;
        String fingerprint;
        String first_digits;
        String last_digits;
        String name;
        int expiration_month;
        int expiration_year;
        Boolean security_code_check;
        String tokenization_method;
        Date created_at;
}
