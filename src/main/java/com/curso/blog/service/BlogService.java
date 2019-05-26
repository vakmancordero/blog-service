package com.curso.blog.service;

import com.curso.blog.dto.BlogCreateRequest;
import com.curso.blog.dto.BlogUpdateRequest;
import com.curso.blog.model.Blog;
import com.curso.blog.repository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> findAllBlogs() {
        return blogRepository.findAll();
    }

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

    public Blog createBlog(BlogCreateRequest blog) {

        Blog blogEntity = new Blog();
        blogEntity.setName(blog.getName());
        blogEntity.setDescription(blog.getDescription());
        blogEntity.setCreatedBy(blog.getCreatedBy());

        return blogRepository.save(blogEntity);
    }

    public Blog updateBlog(Long blogId, BlogUpdateRequest blogUpdate) throws Exception {

        Optional<Blog> optionalBlog = blogRepository.findById(blogId);

        if (!optionalBlog.isPresent()) {
            throw new Exception(String.format("El blog con id: %d no encontrado", blogId));
        }

        Blog blog = optionalBlog.get();
        blog.setName(blogUpdate.getName());
        blog.setDescription(blogUpdate.getDescription());

        return blogRepository.save(blog);

        //return blog;
    }

    public Blog partiallyUpdateBlog(Long blogId, BlogUpdateRequest blogUpdate) throws Exception {

        Optional<Blog> optionalBlog = blogRepository.findById(blogId);

        if (!optionalBlog.isPresent()) {
            throw new Exception(String.format("El blog con id: %d no encontrado", blogId));
        }

        Blog blog = optionalBlog.get();
        blog.setName(blogUpdate.getName());

        if (!StringUtils.isEmpty(blogUpdate.getDescription())) {
            blog.setDescription(blogUpdate.getDescription());
        }

        return blogRepository.save(blog);
    }

    public void deleteBlog(Long blogId) throws Exception {

        boolean blogExists = blogRepository.existsById(blogId);

        if (!blogExists)
            throw new Exception(String.format("El blog con id: %d no encontrado", blogId));

        blogRepository.deleteById(blogId);
    }

}
