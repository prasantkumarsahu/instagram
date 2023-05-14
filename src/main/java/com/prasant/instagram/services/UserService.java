package com.prasant.instagram.services;

import com.prasant.instagram.dto.UserSignInInput;
import com.prasant.instagram.dto.UserSignUpInOutput;
import com.prasant.instagram.dto.UserSignUpInput;
import com.prasant.instagram.dto.UserUpdateInput;
import com.prasant.instagram.models.User;
import com.prasant.instagram.models.UserAuthenticationToken;
import com.prasant.instagram.repositories.IUserRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserAuthenticationTokenService authenticationTokenService;

    public UserSignUpInOutput checkAndRegister(UserSignUpInput userSignUpInput) {
        boolean isPresent = userRepository.existsByEmail(userSignUpInput.getEmail());

        if (isPresent){
            return new UserSignUpInOutput("User already existed. Sign in instead!", null);
        }

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(userSignUpInput.getPassword());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        User user = new User(userSignUpInput.getFirstName(), userSignUpInput.getLastName(), userSignUpInput.getAge(), userSignUpInput.getEmail(), encryptedPassword, userSignUpInput.getPhoneNumber());
        userRepository.save(user);

        UserAuthenticationToken token = new UserAuthenticationToken(user);
        authenticationTokenService.saveToken(token);

        return new UserSignUpInOutput("User sign up successful!", token.getToken());
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        byte[] digested = md5.digest();
        return DatatypeConverter.printHexBinary(digested);
    }

    public boolean isAuthenticate(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserSignUpInOutput loginUser(UserSignInInput userSignInInput) {

        User user = userRepository.findByEmail(userSignInInput.getEmail());

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(userSignInInput.getPassword());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if(encryptedPassword.equals(user.getPassword())) {
            UserAuthenticationToken authToken = authenticationTokenService.getAuthTokenByUser(user);
            return new UserSignUpInOutput("User sign in successful!", authToken.getToken());
        }

        return new UserSignUpInOutput("User details invalid, retry!", null);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateUser(Long id, UserUpdateInput updateInput) {
        User savedUser = userRepository.findById(id).orElse(new User());
        savedUser.setFirstName(updateInput.getFirstName());
        savedUser.setLastName(updateInput.getLastName());
        savedUser.setAge(updateInput.getAge());
        savedUser.setEmail(updateInput.getEmail());
        savedUser.setPhoneNumber(updateInput.getPhoneNumber());

        userRepository.save(savedUser);
    }
}
