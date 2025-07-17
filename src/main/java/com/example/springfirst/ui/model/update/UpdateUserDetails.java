package com.example.springfirst.ui.model.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserDetails {
    @NotNull(message = "Firstname can not be null")
    @Size(min = 2, message =" First name cannot be less than two characters")
    private String firstname;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, message =" Last name cannot be less than two characters")
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
