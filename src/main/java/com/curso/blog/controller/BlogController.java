package com.curso.blog.controller;

import com.curso.blog.dto.BlogCreateRequest;
import com.curso.blog.dto.BlogUpdateRequest;
import com.curso.blog.model.Blog;
import com.curso.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity findBlogs() {
        return ResponseEntity.ok(blogService.findAllBlogs());
    }

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
    public ResponseEntity createBlog(@Valid @RequestBody BlogCreateRequest blog) {
        return ResponseEntity.ok(blogService.createBlog(blog));
    }

    @PutMapping("/{blogId}")
    public ResponseEntity updateBlog(@PathVariable/*("blogId")*/ Long blogId,
                                     @Valid @RequestBody BlogUpdateRequest blogUpdate) throws Exception {
        return ResponseEntity.ok(blogService.updateBlog(blogId, blogUpdate));
    }

    @PatchMapping("/{blogId}")
    public ResponseEntity partiallyUpdateBlog(@PathVariable/*("blogId")*/ Long blogId,
                                              @Valid @RequestBody BlogUpdateRequest blogUpdate) throws Exception {
        return ResponseEntity.ok(blogService.partiallyUpdateBlog(blogId, blogUpdate));
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity deleteBlog(@PathVariable Long blogId) throws Exception {
        blogService.deleteBlog(blogId);
        //return ResponseEntity.ok("Blog deleted");
        return ResponseEntity.ok().build();
    }

}
