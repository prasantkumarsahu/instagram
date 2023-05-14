package com.prasant.instagram.repositories;

import com.prasant.instagram.models.Post;
import com.prasant.instagram.models.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface IPostRepository extends ListCrudRepository<Post, Integer> {
    List<Post> findByUser(User savedUser);
}
