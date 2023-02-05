package com.gusta.template.model.vo;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InfoVO {
    private Long id;
    private String cpf;
    private String dateOfBirth;
}
