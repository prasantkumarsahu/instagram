package com.prasant.instagram.services;

import com.prasant.instagram.dto.PostInput;
import com.prasant.instagram.dto.PostOutput;
import com.prasant.instagram.models.Post;
import com.prasant.instagram.models.User;
import com.prasant.instagram.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private UserService userService;

    public void createPost(Long id, PostInput postInput) {
        User savedUser = userService.getUser(id);

        Post post = new Post(postInput.getPostData(), savedUser);
        postRepository.save(post);
    }

    public List<PostOutput> getAllPosts(Long id) {
        User savedUser = userService.getUser(id);
        List<Post> postList = postRepository.findByUser(savedUser);
        List<PostOutput> outputList = new ArrayList<>();

        postList.forEach(
                post -> outputList.add(new PostOutput(post.getPostData(), post.getCreatedDate(), post.getUpdatedDate()))
        );

        return outputList;
    }
}
