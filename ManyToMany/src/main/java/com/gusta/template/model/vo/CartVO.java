package com.gusta.template.model.vo;

import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartVO {
    private Long id;
    private List<ItemVO> items;
    private Integer totalAmount;
    private Double totalPrice;
}