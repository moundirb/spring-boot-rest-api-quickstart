package com.example.springfirst.ui.controller;

import com.example.springfirst.ui.model.request.UserDetails;
import com.example.springfirst.ui.model.response.UserRest;
import com.example.springfirst.ui.model.update.UpdateUserDetails;
import com.example.springfirst.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;



@RestController

@RequestMapping("/users")
public class userController {

    @Autowired
    UserService userService;


    // get Request users detaoms from URI********************************************
    @GetMapping
    public String getUsers( @RequestParam(value = "page",defaultValue = "1") int page,
                          @RequestParam(value = "limit",defaultValue = "50") int limit,
                          @RequestParam(defaultValue = "desc",value = "sort",required = false)  String sort )

    {

        return "get user was called with page " + page+ " and limit " +limit+ " and the sort " +sort;

    }

//get Request for one user with hardcoded data and taking userId as path variable *******************************

    @GetMapping("/{userId}")
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        UserRest user = userService.getUser(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
//post Request****************************************************************************
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetails userDetails){ // converting the request body to userDetails object so that method can use this object contents

        UserRest returnValue =  userService.createUser(userDetails);

        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);


    }

// put Request***********************************************************************
    @PutMapping(value = "/{userId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetails updateUserDetails){

        return userService.updateUser(userId,updateUserDetails);

    }
// delete Request***********************************************************************

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<Void> deleteUser( @PathVariable String id)
    {
        userService.deleteUser(id);
        return  ResponseEntity.noContent().build();
    }

}
