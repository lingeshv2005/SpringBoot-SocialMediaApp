package com.example.blogpost.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.blogpost.Model.Users;

public interface UsersRepository extends MongoRepository<Users,String>{
   Users findByUsername(String userId);
}
