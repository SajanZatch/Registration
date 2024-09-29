package com.api.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistrationDto {

    //@Size annotation can only be used in String variable
    @Size(min = 2,message = "minimum should be 2 letters")
    private String name;

    @Email
    private String email;

    @Size(max = 10,min = 10,message = "Atleast 10 digits")
    private String mobile;

}