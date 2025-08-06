package com.example.JobPortal.Controller;


import com.example.JobPortal.repository.JobRepository;
import com.example.JobPortal.Model.Post;
import com.example.JobPortal.repository.SearchImplemet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController
{
    @Autowired
    JobRepository repo;

    @Autowired
    SearchImplemet srepo;

    @ApiIgnore
    @RequestMapping(value="/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }


    @GetMapping("/allPosts")
    @CrossOrigin
    public List<Post> getAllPosts()
    {
        System.out.println("Ayush");
        return repo.findAll();

    }

    @PostMapping("/post")
    @CrossOrigin
    public Post submitpost(@RequestBody Post post)
    {
        return repo.save(post);

    }

    @GetMapping("/posts/{text}")
    @CrossOrigin
    public List<Post> search(@PathVariable String text)
    {
        return srepo.findByText(text);
    }

}