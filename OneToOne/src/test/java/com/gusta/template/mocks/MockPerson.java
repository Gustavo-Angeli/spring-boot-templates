package com.gusta.template.mocks;

import com.gusta.template.model.entities.*;
import com.gusta.template.model.vo.*;

public class MockPerson {

    public PersonEntity mockEntity() {
        return mockEntity(0L);
    }

    public PersonVO mockVO() {
        return mockVO(0L);
    }

    public PersonEntity mockEntity(Long number) {
        return PersonEntity.builder()
                .id(number)
                .name("person")
                .money(10D)
                .activated(true)
                .info(InfoEntity.builder()
                        .id(number)
                        .cpf("12345678912")
                        .dateOfBirth("01-01-2000")
                        .build())
                .build();
    }

    public PersonVO mockVO(Long number) {
        return PersonVO.builder()
                .id(number)
                .name("person")
                .money(10D)
                .activated(true)
                .info(InfoVO.builder()
                        .id(number)
                        .cpf("12345678912")
                        .dateOfBirth("01-01-2000")
                        .build())
                .build();
    }

}
