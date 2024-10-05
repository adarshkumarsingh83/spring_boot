package com.espark.adarsh.bean;

import com.espark.adarsh.annotation.UserValidation;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;


@Data
@Setter
@Getter
@ToString
@UserValidation
public class User {

    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    String name;

    @Email(message = "Invalid email")
    String email;

    @NotBlank(message = "Invalid Phone number: Empty number")
    @NotNull(message = "Invalid Phone number: Number is NULL")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    String mobile;

    @Min(value = 1, message = "Invalid Age: Equals to zero or Less than zero")
    @Max(value = 100, message = "Invalid Age: Exceeds 100 years")
    Integer age;

    @AssertTrue(message = "Working must be true")
    private boolean working;

    @Size(min = 10, max = 200, message
            = "About Me must be between 10 and 200 characters")
    private String aboutMe;


    @Past
    private LocalDate dateOfBirth;

    private List<@NotBlank  String> preference;


}
