package com.prasant.instagram.services;

import com.prasant.instagram.models.User;
import com.prasant.instagram.models.UserAuthenticationToken;
import com.prasant.instagram.repositories.IUserAuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationTokenService {
    @Autowired
    IUserAuthenticationTokenRepository userAuthenticationTokenRepository;

    public void saveToken(UserAuthenticationToken token) {
        userAuthenticationTokenRepository.save(token);
    }

    public UserAuthenticationToken getAuthTokenByUser(User user) {
        return userAuthenticationTokenRepository.findByUser(user);
    }
}
