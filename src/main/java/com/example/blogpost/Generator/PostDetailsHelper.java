package com.example.blogpost.Generator;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class PostDetailsHelper {
    

    @Autowired
    private MongoTemplate mongoTemplate;


    public Integer getNextImpressionSequence(String seqName,String userId,String postId){//like//report//repost//comment//view
        Query query=new Query();
        query.addCriteria(Criteria.where("userId").is(userId).and("postId").is(postId));

        Update update=new Update();
        update.inc(seqName+"count", 1);

        PostDetails counter=mongoTemplate.findAndModify(
            query,
            update,
            FindAndModifyOptions.options().returnNew(true).upsert(true),
            PostDetails.class);
        
        return counter!=null? Integer.valueOf(counter.getCount()):1;
    }

    public Map<String,Map<String,String>> addImpression(String postId,String myUserId,String key,String userId,String username){
        Query query=new Query();
        query.addCriteria(Criteria.where("userId").is(myUserId).and("postId").is(postId));

        Update update=new Update();
        update.set("postIds." + key + "." + userId, username);

        PostDetails postDetails=mongoTemplate.findAndModify(
            query,
            update,
            FindAndModifyOptions.options().returnNew(true).upsert(true),
            PostDetails.class);

        return postDetails!=null? Map.copyOf(postDetails.getPostIds()):Map.of(key,Map.of(username,username));

    }

    public Map<String,Map<String,String>> addContentImpressions(String postId,String myUserId,String key,String userId,String content){
        Query query=new Query();
        query.addCriteria(Criteria.where("userId").is(myUserId).and("postId").is(postId));

        Update update=new Update();
        update.set("postIds."+key+"." + userId, content);

        PostDetails postDetails=mongoTemplate.findAndModify(
            query,
            update,
            FindAndModifyOptions.options().returnNew(true).upsert(true),
            PostDetails.class);

        return postDetails!=null? Map.copyOf(postDetails.getPostIds()):Map.of(key,Map.of(userId,content));

    }

}
