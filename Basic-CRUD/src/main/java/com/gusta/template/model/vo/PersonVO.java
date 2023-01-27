package com.gusta.template.model.vo;

import lombok.*;

@Getter
@Setter
@Builder
public class PersonVO {

    private Long id;
    private String name;
    private Double money;
    private Boolean activated;

}