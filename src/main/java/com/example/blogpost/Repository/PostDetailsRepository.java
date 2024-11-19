package com.example.blogpost.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.blogpost.Generator.PostDetails;

public interface PostDetailsRepository extends MongoRepository<PostDetails,String>{

    @Query(value = "{ 'postId': ?0, 'userId': ?1 }", fields = "{ 'postIds.?2': 1, '_id': 0 }")
    PostDetails findPostDetailsByPostIdAndUserId(String postId,String userId,String seq);
}
