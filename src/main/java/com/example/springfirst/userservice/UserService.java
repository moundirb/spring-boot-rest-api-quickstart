package com.example.springfirst.userservice;

import com.example.springfirst.ui.model.request.UserDetails;
import com.example.springfirst.ui.model.response.UserRest;
import com.example.springfirst.ui.model.update.UpdateUserDetails;

public interface UserService {
     UserRest createUser(UserDetails userDetails);
     UserRest updateUser(String userId, UpdateUserDetails updateUserDetails);
     void deleteUser(String userId);
     UserRest getUser(String userId);
}
