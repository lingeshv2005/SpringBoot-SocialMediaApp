package com.example.blogpost.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.blogpost.Generator.PostDetailsHelper;
import com.example.blogpost.Generator.SequenceGenerator;
import com.example.blogpost.Model.Posts;
import com.example.blogpost.Model.Users;
import com.example.blogpost.Repository.PostsRepository;
import com.example.blogpost.Repository.UsersRepository;

@Service
public class PostsService {

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    PostDetailsHelper postDetailsHelper;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UsersRepository usersRepository;

    public Posts createPost(Posts post,String userId) {
        post.setPostId(sequenceGenerator.getNextPostSequence("posts_sequence",userId));
        post.setUserId(userId);

        return postsRepository.save(post);    
    }

    public List<Posts> getAllPosts(String userId){
        Users user=usersRepository.findByUsername(userId);
        if(user==null){return null;}
        
        return postsRepository.findAllByUserId(userId);
    }

    public Posts addLike(String userId,String postId,String likedBy){
        Users me=usersRepository.findByUsername(userId);
        if(me==null){return null;}

        Users likedUser=usersRepository.findByUsername(likedBy);
        if(likedUser==null){return null;}

        Posts post=postsRepository.findByUserIdAndPostId(userId,postId);
        if(post==null){return null;}
        
        postDetailsHelper.getNextImpressionSequence("like", userId, postId);
        postDetailsHelper.addImpression(postId,userId,"like",likedBy,likedUser.getUsername());

        return postsRepository.save(post);
    }    

    public Posts addView(String userId,String postId,String viewedBy){
        Users me=usersRepository.findByUsername(userId);
        if(me==null){return null;}

        Users viewedUser=usersRepository.findByUsername(viewedBy);
        if(viewedUser==null){return null;}

        Posts post=postsRepository.findByUserIdAndPostId(userId,postId);
        if(post==null){return null;}

        postDetailsHelper.getNextImpressionSequence("view", userId, postId);
        postDetailsHelper.addImpression(postId,userId,"view",viewedBy,viewedUser.getUsername());

        return postsRepository.save(post);
    }    

    public Posts addComment(String userId,String postId,String commentedBy,String content){
        Users me=usersRepository.findByUsername(userId);
        if(me==null){return null;}

        Users commentedUser=usersRepository.findByUsername(commentedBy);
        if(commentedUser==null){return null;}

        Posts post=postsRepository.findByUserIdAndPostId(userId,postId);
        if(post==null){return null;}

        postDetailsHelper.getNextImpressionSequence("comment", userId, postId);
        postDetailsHelper.addContentImpressions(postId,userId,"comment",commentedBy,content);

        return postsRepository.save(post);
    }    


    public Posts addRepost(String userId,String postId,String repostedBy,String content){
        Users me=usersRepository.findByUsername(userId);
        if(me==null){return null;}

        Users repostedUser=usersRepository.findByUsername(repostedBy);
        if(repostedUser==null){return null;}

        Posts post=postsRepository.findByUserIdAndPostId(userId,postId);
        if(post==null){return null;}

        postDetailsHelper.getNextImpressionSequence("repost", userId, postId);
        postDetailsHelper.addContentImpressions(postId,userId,"repost",repostedBy,content);

        return postsRepository.save(post);
    }    

    public Posts getByPostId(String userId,String postId){
        return postsRepository.findByUserIdAndPostId(userId,postId);
    }

}


