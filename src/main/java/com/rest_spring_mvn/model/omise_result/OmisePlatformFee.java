package com.rest_spring_mvn.model.omise_result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OmisePlatformFee {
    Double fixed;
    Double amount;
    Double percentage;
}
