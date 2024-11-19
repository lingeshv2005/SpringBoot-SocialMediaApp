package com.example.blogpost.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.blogpost.Generator.PostDetails;
import com.example.blogpost.Repository.PostDetailsRepository;

@Service
public class PostDetilsService {

    @Autowired
    PostDetailsRepository postDetailsRepository;

    public PostDetails getPostLike(String userId,String postId){
            return postDetailsRepository.findPostDetailsByPostIdAndUserId(postId,userId,"like");
    }

    public PostDetails getPostView(String userId,String postId){
        return postDetailsRepository.findPostDetailsByPostIdAndUserId(postId,userId,"view");
    }

    public PostDetails getPostComment(@PathVariable String userId,@PathVariable String postId){
         return postDetailsRepository.findPostDetailsByPostIdAndUserId(postId,userId,"comment");
    }

    public PostDetails getPostRepost(@PathVariable String userId,@PathVariable String postId){
        return postDetailsRepository.findPostDetailsByPostIdAndUserId(postId,userId,"repost");
   }



}
