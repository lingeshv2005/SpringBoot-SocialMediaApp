package com.example.blogpost.Generator;


import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class SequenceGenerator {
    
    private final MongoTemplate mongoTemplate;

    public SequenceGenerator(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }

    public String getNextUserSequenceId(String seqName){
        Query query=new Query();
        query.addCriteria(Criteria.where("user").is(seqName));

        Update update=new Update();
        update.inc("count", 1);

        Sequence counter=mongoTemplate.findAndModify(
            query,
            update,
            FindAndModifyOptions.options().returnNew(true).upsert(true),
            Sequence.class);
        
        return counter!=null? Integer.valueOf(counter.getCount()).toString():"1"; 
    }



    public void getNextFollowSequence(String myuserId,String following){//following,//follower
        Query query=new Query();
        query.addCriteria(Criteria.where("user").is("follow").and("userId").is(myuserId));

        Update update=new Update();
        update.inc("followingCount", 1);
        update.push("following", following);

        mongoTemplate.findAndModify(
            query,
            update,
            FindAndModifyOptions.options().returnNew(true).upsert(true),
            Sequence.class);


        Query query1=new Query();
        query1.addCriteria(Criteria.where("user").is("follow").and("userId").is(following));

        Update update1=new Update();
        update1.inc("followerCount", 1);
        update1.push("follower", myuserId);

        mongoTemplate.findAndModify(
            query1,
            update1,
            FindAndModifyOptions.options().returnNew(true).upsert(true),
            Sequence.class);

    }




    public String getNextPostSequence(String seqName,String userId){
            Query query=new Query();
            query.addCriteria(Criteria.where("post").is(seqName).and("userId").is(userId));
    
            Update update=new Update();
            update.inc("count", 1);
    
            Sequence counter=mongoTemplate.findAndModify(
                query,
                update,
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                Sequence.class);
            
            return counter!=null? Integer.valueOf(counter.getCount()).toString():"1";
        }
    
    
}





















