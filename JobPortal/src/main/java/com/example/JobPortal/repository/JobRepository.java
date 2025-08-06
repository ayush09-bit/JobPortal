package com.example.JobPortal.repository;

import com.example.JobPortal.Model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends MongoRepository<Post,String> {
}
