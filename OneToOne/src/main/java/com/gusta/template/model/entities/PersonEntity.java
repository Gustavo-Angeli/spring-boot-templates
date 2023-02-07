package com.gusta.template.model.entities;

import lombok.*;

import javax.persistence.*;
import java.text.*;
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
    @OneToOne(cascade = CascadeType.ALL)
    private InfoEntity info;
}