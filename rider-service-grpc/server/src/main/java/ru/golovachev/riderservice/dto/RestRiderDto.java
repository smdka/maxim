package ru.golovachev.riderservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Value
@Builder
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class RestRiderDto {
    @NotBlank(message = "First name can't be empty")
    String firstName;

    String lastName;

    @Email
    @NotBlank(message = "Email can't be empty")
    String email;

    @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "Invalid phone number") //E.164 standard for phone numbers
    String phoneNumber;
}
