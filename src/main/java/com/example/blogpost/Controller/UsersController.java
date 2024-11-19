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

import com.example.blogpost.Model.Users;
import com.example.blogpost.Service.SequenceService;
import com.example.blogpost.Service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
    
    @Autowired(required = true)
    UsersService usersService;

    @Autowired(required = true)
    SequenceService sequenceService;

    @GetMapping
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }

    @PostMapping
    public Users createUsers(@RequestBody Users user){
        return usersService.createUser(user);
    }

    @PutMapping("/{userId}/socialLinks")
    public Users addSocialLinks(@RequestBody Map<String,String> socialLinks,@PathVariable String userId){
        return usersService.addSocialLinks(socialLinks,userId);
    }

    @PutMapping("/{myuserId}/follow/{userId}")
    public Users addFollowing(@PathVariable String userId,@PathVariable String myuserId){
        return usersService.addFollowing(userId,myuserId);
    }

    @GetMapping("/{username}")
    public Users getByUsername(@PathVariable String username){
        return usersService.getByUsername(username);
    }

    @GetMapping("/{username}/following")
    public List<String> getFollowingByUsername(@PathVariable String username){
        return sequenceService.getFollowingByUsername(username);
    }

    @GetMapping("/{username}/follower")
    public List<String> getFollowerByUsername(@PathVariable String username){
        return sequenceService.getFollowerByUsername(username);
    }

    
}
