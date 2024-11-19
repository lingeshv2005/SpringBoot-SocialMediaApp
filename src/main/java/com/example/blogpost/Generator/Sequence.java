package com.example.blogpost.Generator;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
public class Sequence {
    
    @Id
    private String _id;
    public String get_Id() {return _id;}
    public void set_Id(String _id) {this._id = _id;}

    private int count;
    public int getCount(){return count;}
    public void setCount(int count){this.count=count;}

    private String userId;    
    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}

    private String postId;
    public String getPostId() {return postId;}
    public void setPostId(String postId) {this.postId = postId;}
    
    private List<String> following;
    public List<String> getFollowing() {return following;}
    public void setFollowing(List<String> following) {this.following = following;}
    
    private List<String> follower;
    public List<String> getFollower() {return follower;}
    public void setFollower(List<String> follower) {this.follower = follower;}
    


}
