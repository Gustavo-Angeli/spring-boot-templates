package com.gusta.template.model.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonVO {
    private Long id;
    private String name;
    private Double money;
    private Boolean activated;
    private InfoVO info;
}