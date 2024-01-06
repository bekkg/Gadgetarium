package com.peaksoft.gadgetarium.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String telNumber;
    private String email;
    private String password;
}
