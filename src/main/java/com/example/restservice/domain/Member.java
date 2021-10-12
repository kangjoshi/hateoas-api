package com.example.restservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {

    private Integer memberId;
    private String name;
    private Integer age;

}
