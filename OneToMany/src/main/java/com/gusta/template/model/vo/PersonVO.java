package com.gusta.template.model.vo;

import com.gusta.template.model.entities.*;
import lombok.*;

import java.util.*;

@Data
@Builder
public class PersonVO {
    private Long id;
    private String name;
    private Double money;
    private Boolean activated;
    private List<PhoneNumberEntity> numbers;
}