package com.rest_spring_mvn.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ApplicationAuth {
    String username;
    String password;
    String zone;
    String system;
}
