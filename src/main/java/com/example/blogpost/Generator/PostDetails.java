package com.example.blogpost.Generator;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "postDetails")
public class PostDetails {
    


    @Id
    private String _id;
    public String getId() {return _id;}
    public void setId(String id) {this._id = id;}

    private int count;
    public int getCount(){return count;}
    public void setCount(int count){this.count=count;}

    private String userId;
    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}
    
    private String postId;
    public String getPostId() {return postId;}
    public void setPostId(String postId) {this.postId = postId;}

    private Map<String,Map<String,String>> postIds;
    public Map<String, Map<String,String>> getPostIds() {return postIds;}
    public void setPostIds(String userId,String username,String content) {this.postIds.put(userId,Map.of("content",content));}
    
}
