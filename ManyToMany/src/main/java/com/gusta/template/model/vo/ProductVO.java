package com.gusta.template.model.vo;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductVO {
    private Long id;
    private String name;
    private Double price;
}
