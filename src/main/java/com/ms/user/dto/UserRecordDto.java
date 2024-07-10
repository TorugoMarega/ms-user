package com.ms.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDto(
        @NotBlank @NotNull String name,
        @NotBlank @NotNull @Email String email,
        @NotBlank @NotNull String cdCpf
        ) {
}
