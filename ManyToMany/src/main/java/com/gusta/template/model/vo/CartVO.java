package com.gusta.template.model.vo;

import com.gusta.template.model.entities.*;
import lombok.*;

import javax.persistence.*;
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