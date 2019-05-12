package com.curso.blog.service;

import com.curso.blog.model.Blog;
import com.curso.blog.repository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog findBlog(Long blogId) throws Exception {

        // find one blog
        //Blog blog =  blogRepository.getOne(blogId);

        Optional<Blog> optionalBlog =  blogRepository.findById(blogId);

        if (optionalBlog.isPresent()) {
            return optionalBlog.get();
        } else {
            throw new Exception("El blog con el id: " + blogId + " no existe");
        }
    }

    public Blog findBlog(Long blogId, String name) throws Exception {

        Optional<Blog> optionalBlog =  blogRepository.findBlogJpql(blogId, name);

        if (optionalBlog.isPresent()) {
            return optionalBlog.get();
        } else {
            throw new Exception("El blog con el id: " + blogId + " no existe");
        }
    }

}
