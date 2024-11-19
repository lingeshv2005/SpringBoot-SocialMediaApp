package com.example.blogpost.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogpost.Generator.PostDetails;
import com.example.blogpost.Model.Posts;
import com.example.blogpost.Service.HistoryService;
import com.example.blogpost.Service.PostDetilsService;
import com.example.blogpost.Service.PostsService;

@RestController
@RequestMapping("users/{userId}/posts/")
public class PostsController {
    
    @Autowired(required = true)
    private PostsService postsService;

    @Autowired(required = true)
    private PostDetilsService postDetailsService;

    @Autowired 
    private HistoryService historyService;

    @GetMapping
    public List<Posts> getAllPosts(@PathVariable String userId){
        return postsService.getAllPosts(userId);
    }

    @PostMapping
    public Posts createPosts(@RequestBody Posts post,@PathVariable String userId){
        return postsService.createPost(post,userId);
    }

    @GetMapping("{postId}")
    public Posts getByPostId(@PathVariable String userId,@PathVariable String postId){
        return postsService.getByPostId(userId,postId);
    }

    @PutMapping("{postId}/like/{likedBy}")
    public Posts addLike(@PathVariable String userId,@PathVariable String postId,@PathVariable String likedBy){
        historyService.createHistoryLike(likedBy,postId,userId);
        return postsService.addLike(userId,postId,likedBy);
    }

    @PutMapping("{postId}/view/{viewedBy}")
    public Posts addView(@PathVariable String userId,@PathVariable String postId,@PathVariable String viewedBy){
        historyService.createHistoryView(viewedBy,postId,userId);
        return postsService.addView(userId,postId,viewedBy);
    }

    @PutMapping("{postId}/comment/{commentedBy}")
    public Posts addComment(
        @PathVariable String userId,
        @PathVariable String postId,
        @PathVariable String commentedBy,
        @RequestBody Map<String,String>  content){
        if(content.get("content")==null){
            return null;
        }
        return postsService.addComment(userId,postId,commentedBy,content.get("content"));
    }

    @PutMapping("{postId}/repost/{repostedBy}")
    public Posts addRepost(
        @PathVariable String userId,
        @PathVariable String postId,
        @PathVariable String repostedBy,
        @RequestBody Map<String,String>  content){
            return postsService.addRepost(userId,postId,repostedBy,content.get("content"));
    }

    @GetMapping("{postId}/like")
    public PostDetails getPostLike(@PathVariable String userId,@PathVariable String postId){
         return postDetailsService.getPostLike(userId,postId);
        
    }

    @GetMapping("{postId}/view")
    public PostDetails getPostView(@PathVariable String userId,@PathVariable String postId){
         return postDetailsService.getPostView(userId,postId);
    }

    @GetMapping("{postId}/comment")
    public PostDetails getPostComment(@PathVariable String userId,@PathVariable String postId){
         return postDetailsService.getPostComment(userId,postId);
    }

    @GetMapping("{postId}/repost")
    public PostDetails getPostRepost(@PathVariable String userId,@PathVariable String postId){
         return postDetailsService.getPostRepost(userId,postId);
    }

}

