package com.example.blogpost.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.blogpost.Model.History;

public interface HistoryRepository extends MongoRepository<History,String>{
    History findByUsername(String username);

    @Query(value = "{ 'userId': ?0 }", fields = "{ 'likedHistory': 1, '_id': 0 }")
    Optional<History> findLikedHistoryByusername(String username);
}
