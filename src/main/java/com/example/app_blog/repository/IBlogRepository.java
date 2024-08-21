package com.example.app_blog.repository;

import com.example.app_blog.model.Blog;
import com.example.app_blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findAllByTitleContaining(Pageable pageable, String title);

//    Page<Blog> findAllByCategory(Category category, Pageable pageable);

    Iterable <Blog> findAllByCategory(Category category);

}
