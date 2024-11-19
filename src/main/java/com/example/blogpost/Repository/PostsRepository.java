package com.example.blogpost.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.blogpost.Model.Posts;

public interface PostsRepository extends MongoRepository<Posts,String>{
        List<Posts> findAllByUserId(String userId);
        Posts findByUserIdAndPostId(String userId, String postId);

}
