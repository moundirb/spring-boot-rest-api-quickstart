package com.example.springfirst.ui.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDetails {

    @NotNull(message = "Firstname can not be null")
    @Size(min = 2, message =" First name cannot be less than two characters")
    private String firstname;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, message =" Last name cannot be less than two characters")
    private String lastname;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

    @NotNull (message = "Password cannot be null")
    @Size(min = 8 , max = 16, message = "Password must be equal or greater than 8 characters and less than 16 characters")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
