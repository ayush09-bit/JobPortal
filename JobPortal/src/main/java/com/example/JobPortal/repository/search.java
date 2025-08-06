package com.example.JobPortal.repository;

import com.example.JobPortal.Model.Post;

import java.util.List;

public interface search {
    List<Post> findByText(String text);
}
