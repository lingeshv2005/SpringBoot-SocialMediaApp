package com.example.blogpost.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogpost.Generator.Sequence;
import com.example.blogpost.Repository.SequenceRepository;

@Service
public class SequenceService {
    
    @Autowired
    private SequenceRepository sequenceRepository;

    public List<String> getFollowingByUsername(String username) {
        Sequence sequence = sequenceRepository.findByUserId(username);
        return sequence != null ? sequence.getFollowing() : null;
    }

    public List<String> getFollowerByUsername(String username) {
        Sequence sequence = sequenceRepository.findByUserId(username);
        return sequence != null ? sequence.getFollower() : null;
    }



}
