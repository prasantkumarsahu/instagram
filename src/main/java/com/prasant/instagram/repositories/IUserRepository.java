package com.prasant.instagram.repositories;

import com.prasant.instagram.models.User;
import org.springframework.data.repository.ListCrudRepository;

public interface IUserRepository extends ListCrudRepository<User, Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

}
