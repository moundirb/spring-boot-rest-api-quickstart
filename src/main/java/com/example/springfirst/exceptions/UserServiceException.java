package com.example.springfirst.exceptions;


import java.io.Serial;

public class UserServiceException extends RuntimeException
{

    @Serial
    private static final long serialVersionUID = 7L;
    public  UserServiceException(String message)
    {

        super(message);
    }
}
