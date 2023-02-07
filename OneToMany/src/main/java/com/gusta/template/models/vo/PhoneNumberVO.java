package com.gusta.template.models.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhoneNumberVO {
    private Long id;
    private String phoneNumber;
}
