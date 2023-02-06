package com.gusta.template.models.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductVO {
    private Long id;
    private String name;
    private Double price;
}
