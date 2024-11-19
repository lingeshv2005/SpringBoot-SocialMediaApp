package com.example.blogpost.Model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("history")
public class History {
    @Id
    private String _id;

    @Indexed
    private String username;

    private List<Map<String,String>> viewHistory;
    private List<Map<String,String>> likedHistory;
    private List<Map<String,String>> savedHistory;


    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getUserId() {
        return username;
    }
    public void setUserId(String username) {
        this.username = username;
    }
    public List<Map<String,String>> getViewHistory() {
        return viewHistory;
    }
    public void setViewHistory(List<Map<String,String>> viewHistory) {
        this.viewHistory = viewHistory;
    }
    public List<Map<String,String>> getLikedHistory() {
        return likedHistory;
    }
    public void setLikedHistory(List<Map<String,String>> likedHistory) {
        this.likedHistory = likedHistory;
    }
    public List<Map<String,String>> getSavedHistory() {
        return savedHistory;
    }
    public void setSavedHistory(List<Map<String,String>> savedHistory) {
        this.savedHistory = savedHistory;
    }

    


}
