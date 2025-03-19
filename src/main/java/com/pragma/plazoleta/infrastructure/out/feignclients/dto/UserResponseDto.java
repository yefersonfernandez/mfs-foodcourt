package com.pragma.plazoleta.infrastructure.out.feignclients.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private RoleResponseDto roleResponseDto;
}
