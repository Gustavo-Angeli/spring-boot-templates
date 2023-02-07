package com.gusta.template.models.vo;

import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonVO {
    private Long id;
    private String name;
    private List<PhoneNumberVO> numbers;
}