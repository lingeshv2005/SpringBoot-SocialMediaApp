package com.example.blogpost.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogpost.Service.HistoryService;

@RestController
@RequestMapping("/users/{username}")
public class HistoryController {

    @Autowired
    HistoryService historyService;
    
    @GetMapping("/liked")
    public List<Map<String,String>> getLikedHistory(@PathVariable String username){
        return historyService.getLikedHistory(username);
    }
}
