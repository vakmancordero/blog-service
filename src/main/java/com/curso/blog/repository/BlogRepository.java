package com.curso.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.blog.model.Blog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> findByIdAndName(Long blogId, String name);

    //select * from blog b where b.id = coalesce(:id, b.id) and b.name = coalesce(:name, b.name);
    @Query(value = "select * from blog b where b.id = coalesce(:blogId, b.id) and b.name = coalesce(:name, b.name)",
            nativeQuery = true)
    Optional<Blog> findBlogNative(@Param("blogId") Long blogId, @Param("name") String name);

    @Query(value = "select b from Blog b where b.id = coalesce(:blogId, b.id) and b.name = coalesce(:name, b.name)")
    Optional<Blog> findBlogJpql(@Param("blogId") Long blogId, @Param("name") String name);


}
