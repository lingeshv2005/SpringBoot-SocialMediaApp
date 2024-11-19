package com.example.blogpost.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.blogpost.Generator.Sequence;

public interface SequenceRepository extends MongoRepository<Sequence,String>{
    Sequence findByUserId(String username);
    
        
}
