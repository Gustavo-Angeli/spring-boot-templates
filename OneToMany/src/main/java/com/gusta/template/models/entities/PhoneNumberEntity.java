package com.gusta.template.models.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "phone_numbers")
public class PhoneNumberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
}
