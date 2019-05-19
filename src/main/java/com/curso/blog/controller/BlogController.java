package com.curso.blog.controller;

import com.curso.blog.dto.BlogCreateRequest;
import com.curso.blog.model.Blog;
import com.curso.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // localhost/blog?blogId=123&name=Hola
    @GetMapping("/find")
    public ResponseEntity findBlog(@RequestParam(required = false) Long blogId,
                                   @RequestParam(required = false) String name) {

        try {

            Blog blog = blogService.findBlog(blogId, name);

            // RestTemplate -> ResponseEntity
            //return ResponseEntity.status(HttpStatus.OK).body(blog);
            //return new ResponseEntity<>(blog, HttpStatus.OK);
            return ResponseEntity.ok(blog);

        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity createBlog(@RequestBody BlogCreateRequest blog) {
        return ResponseEntity.ok(blogService.createBlog(blog));
    }

}
