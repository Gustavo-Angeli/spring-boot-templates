package com.gusta.template.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cart_item",
            joinColumns={@JoinColumn(name = "cart_id")},
            inverseJoinColumns={@JoinColumn(name = "item_id")})
    private List<ItemEntity> items;
    private Integer totalAmount;
    private Double totalPrice;

    public void setTotalAmountAndTotalPrice() {
        if (totalPrice != 0 || totalAmount != 0) {
            totalPrice = 0D;
            totalAmount = 0;
        }
        items.forEach(itemEntity -> totalPrice += (itemEntity.getAmount() * itemEntity.getProduct().getPrice()));
        items.forEach(itemEntity -> totalAmount += (itemEntity.getAmount()));
    }
}