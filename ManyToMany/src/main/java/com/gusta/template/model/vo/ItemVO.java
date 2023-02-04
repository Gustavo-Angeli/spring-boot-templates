package com.gusta.template.model.vo;

import com.gusta.template.model.entities.*;
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
