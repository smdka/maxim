package ru.golovachev.riderservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RiderDto {
    @NotBlank(message = "First name can't be null")
    String firstName;

    String lastName;

    @Email
    @NotBlank(message = "Invalid email")
    String email;

    @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "Invalid phone number") //E.164 standard for phone numbers
    String phoneNumber;
}
