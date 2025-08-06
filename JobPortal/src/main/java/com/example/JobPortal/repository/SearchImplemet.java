package com.example.JobPortal.repository;

import com.example.JobPortal.Model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchImplemet implements search{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter convertor;

    @Override
    public List<Post> findByText(String text) {
        final List<Post> post = new ArrayList<>();



        MongoDatabase database = client.getDatabase("JobProject");
        MongoCollection<Document> collection = database.getCollection("jobpost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("index", "default").append("text",
                new Document("query", text).append("path",
                Arrays.asList("techs", "desc", "profile")))),
                new Document("$sort",
                new Document("exp", 1L)),
                new Document("$limit", 5L)));

        result.forEach(document -> post.add(convertor.read(Post.class,document)));

        return post;
    }
}
