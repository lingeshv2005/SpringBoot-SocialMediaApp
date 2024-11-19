package com.example.blogpost.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogpost.Model.History;
import com.example.blogpost.Repository.HistoryRepository;

@Service
public class HistoryService {
    
    @Autowired
    HistoryRepository historyRepository;

    public History createHistoryLike(String username,String postId,String userId){
        History history=historyRepository.findByUsername(username);
        if(history==null){
            history=new History();
            history.setUserId(username);
            history.setLikedHistory(new ArrayList<>());
        }
        Map<String, String> newEntry = new HashMap<>();
        newEntry.put(postId, userId);

        List<Map<String,String>> likedList=history.getLikedHistory();
        if(likedList==null){
            likedList=new ArrayList<>();
        }
        
        likedList.add(newEntry);
        history.setLikedHistory(likedList);

        return historyRepository.save(history);
    }

    public History createHistoryView(String username,String postId,String userId){
        History history=historyRepository.findByUsername(username);
        if(history==null){
            history=new History();
            history.setUserId(username);
            history.setViewHistory(new ArrayList<>());
        }
        Map<String, String> newEntry = new HashMap<>();
        newEntry.put(postId, userId);

        List<Map<String,String>> likedList=history.getViewHistory();
        if(likedList==null){
            likedList=new ArrayList<>();
        }
        
        likedList.add(newEntry);
        history.setViewHistory(likedList);

        return historyRepository.save(history);
    }




    public List<Map<String,String>> getLikedHistory(String username){
        Optional<History> result=historyRepository.findLikedHistoryByusername(username);
        return result.map(History::getLikedHistory).orElse(null);
    }
}
