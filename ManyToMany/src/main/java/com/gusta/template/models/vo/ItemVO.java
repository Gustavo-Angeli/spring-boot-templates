package com.gusta.template.models.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemVO {
    private Long id;
    private ProductVO product;
    private Integer amount;
}
