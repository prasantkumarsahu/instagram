package com.prasant.instagram.controllers;

import com.prasant.instagram.dto.UserSignInInput;
import com.prasant.instagram.dto.UserSignUpInOutput;
import com.prasant.instagram.dto.UserSignUpInput;
import com.prasant.instagram.dto.UserUpdateInput;
import com.prasant.instagram.models.User;
import com.prasant.instagram.models.UserAuthenticationToken;
import com.prasant.instagram.services.UserAuthenticationTokenService;
import com.prasant.instagram.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationTokenService authenticationTokenService;

    @PostMapping("signup/")
    public ResponseEntity<UserSignUpInOutput> signUp(@Valid @RequestBody UserSignUpInput userSignUpInput){
        UserSignUpInOutput response = userService.checkAndRegister(userSignUpInput);
        HttpStatus status;
        if (response.getToken() == null)
            status = HttpStatus.BAD_REQUEST;
        else
            status = HttpStatus.CREATED;

        return new ResponseEntity<>(response, status);
    }

    @PostMapping("signin/")
    public ResponseEntity<UserSignUpInOutput> signIn(@Valid @RequestBody UserSignInInput userSignInInput){
        boolean isPresent = userService.isAuthenticate(userSignInInput.getEmail());
        UserSignUpInOutput response;
        HttpStatus status;
        if (!isPresent) {
            status = HttpStatus.NOT_ACCEPTABLE;
            response = new UserSignUpInOutput("User is not exits, sign up instead", null);
        }
        else{
            response = userService.loginUser(userSignInInput);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(response, status);
    }

    @PutMapping("update/")
    public ResponseEntity<String> updatedUser(@RequestParam Long id, @RequestParam String token, @RequestBody UserUpdateInput updateInput){
        User savedUser = userService.getUser(id);
        if (savedUser == null){
            return new ResponseEntity<>("User does not exist!", HttpStatus.BAD_REQUEST);
        }

        UserAuthenticationToken authToken = authenticationTokenService.getAuthTokenByUser(savedUser);
        if (!token.equals(authToken.getToken())){
            return new ResponseEntity<>("User token is invalid!", HttpStatus.BAD_REQUEST);
        }

        userService.updateUser(id, updateInput);
        return new ResponseEntity<>("User updated successfully!", HttpStatus.ACCEPTED);
    }
}
