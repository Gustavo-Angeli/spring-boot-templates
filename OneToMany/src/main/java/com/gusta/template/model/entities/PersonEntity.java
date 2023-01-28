package com.gusta.template.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double money;
    private Boolean activated;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_phone_numbers",
            joinColumns={@JoinColumn(name = "person_id")},
            inverseJoinColumns={@JoinColumn(name = "numbers_id")})
    private List<PhoneNumberEntity> numbers;
}