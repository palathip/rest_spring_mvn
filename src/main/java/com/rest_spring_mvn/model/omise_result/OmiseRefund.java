package com.rest_spring_mvn.model.omise_result;

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
public class OmiseRefund {
    String object;
    List data;
    Double limit;
    Double offset;
    Double total;
    String location;
    String order;
    Date from;
    Date to;
}
