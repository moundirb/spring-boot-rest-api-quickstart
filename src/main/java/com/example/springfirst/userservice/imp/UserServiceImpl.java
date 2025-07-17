package com.example.springfirst.userservice.imp;

import com.example.springfirst.shared.Utils;
import com.example.springfirst.ui.model.request.UserDetails;
import com.example.springfirst.ui.model.response.UserRest;
import com.example.springfirst.ui.model.update.UpdateUserDetails;
import com.example.springfirst.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private Map<String, UserRest> users = new HashMap<>();

    Utils utils;

    public UserServiceImpl() {

    }
    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }


    @Override
    public UserRest createUser(UserDetails userDetails) {
        UserRest returnValue = new UserRest();
        returnValue.setFirstname(userDetails.getFirstname());
        returnValue.setLastname(userDetails.getLastname());
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setPassword(userDetails.getPassword());

        String userId = utils.generateUserId();
        returnValue.setUserId(userId);
        users.put(returnValue.getUserId(), returnValue);

        //users.put(returnValue.getUserId(), returnValue);
        return returnValue;
    }

    @Override
    public UserRest updateUser(String userId, UpdateUserDetails updateUserDetails) {
        UserRest storedUserDetails = users.get(userId);
        if (storedUserDetails == null) return null;

        storedUserDetails.setFirstname(updateUserDetails.getFirstname());
        storedUserDetails.setLastname(updateUserDetails.getLastname());

        users.put(userId, storedUserDetails);
        return storedUserDetails;
    }

    @Override
    public void deleteUser(String userId) {
        users.remove(userId);
    }

    @Override
    public UserRest getUser(String userId) {
        return users.get(userId);
    }
}
