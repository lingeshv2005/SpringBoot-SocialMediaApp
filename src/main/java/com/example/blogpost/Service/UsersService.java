package com.example.blogpost.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogpost.Generator.SequenceGenerator;
import com.example.blogpost.Model.Users;
import com.example.blogpost.Repository.UsersRepository;

@Service
public class UsersService {
    
    @Autowired(required = true)
    private UsersRepository usersRepository;

    @Autowired(required = true)
    private SequenceGenerator sequenceGenerator;

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Users createUser(Users user) {
        sequenceGenerator.getNextUserSequenceId("users_sequence");

        return usersRepository.save(user);    
    }

    public Users addSocialLinks(Map<String,String> socialLinks,String userId){

        Users user=usersRepository.findByUsername(userId);
        if(user==null){
            return null;
        }

        for(Map.Entry<String,String> entry:socialLinks.entrySet()){
            user.setSocialLinks(entry.getKey(), entry.getValue());
        }

        usersRepository.save(user);
        return user;
    }

    public Users addFollowing(String userId,String myuserId){

        Users user=usersRepository.findByUsername(userId);
        System.out.println(user);
        if(user==null){return null;}

        System.out.println(user);

        Users myuser=usersRepository.findByUsername(myuserId);
        if(myuser==null){return null;}
        
        sequenceGenerator.getNextFollowSequence(myuserId,userId);

        return myuser;
    }

    public Users getByUsername(String username){
        return usersRepository.findByUsername(username);
    }


}
