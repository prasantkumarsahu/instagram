package com.prasant.instagram.repositories;

import com.prasant.instagram.models.User;
import com.prasant.instagram.models.UserAuthenticationToken;
import org.springframework.data.repository.ListCrudRepository;

public interface IUserAuthenticationTokenRepository extends ListCrudRepository<UserAuthenticationToken, Long> {
    UserAuthenticationToken findByUser(User user);
}
